package phlppnhllngr.pkpassvalidator.validation;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.jcajce.JcaSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.json.JSONObject;

// https://github.com/tomasmcguinness/pkpassvalidator/blob/master/PassValidator.Web/Validation/Validator.cs
public class Validator {

    private static final String G4WWDRCertificateSerialNumber = "13DC77955271E53DC632E8CCFFE521F3CCC5CED2";


    // From wikipedia:
    // "Manifest.json contains a JSON dictionary containing SHA-1 hashes for all files except the manifest itself and the signature.
    // Signature contains a PKCS #7 signature of the manifest file thus effectively signing all files in the bundle."
    public ValidationResult validate(byte[] passContent) throws Exception {
        var result = new ValidationResult();

        String manifestPassTypeIdentifier = null;
        String manifestTeamIdentifier = null;
        byte[] manifestFile = null;
        byte[] signatureFile = null;

        try (var zipInputStream = new ZipInputStream(new ByteArrayInputStream(passContent))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String entryName = entry.getName().toLowerCase();
                if (entryName.equals("manifest.json")) {
                    result.setHasManifest(true);
                    manifestFile = zipInputStream.readAllBytes();
                }
                else if (entryName.equals("pass.json")) {
                    result.setHasPass(true);
                    byte[] passJsonBytes = zipInputStream.readAllBytes();

                    var jsonObject = new JSONObject(new String(passJsonBytes));
                    manifestPassTypeIdentifier = getKeyStringValue(jsonObject, "passTypeIdentifier");
                    result.setHasPassTypeIdentifier(manifestPassTypeIdentifier != null);

                    manifestTeamIdentifier = getKeyStringValue(jsonObject, "teamIdentifier");
                    result.setHasTeamIdentifier(manifestTeamIdentifier != null);

                    result.setHasDescription(getKeyStringValue(jsonObject, "description") != null);

                    if (jsonObject.has("formatVersion")) {
                        int formatVersion = jsonObject.getInt("formatVersion");
                        result.setHasFormatVersion(formatVersion == 1);
                    }

                    String serialNumber = getKeyStringValue(jsonObject, "serialNumber");
                    result.setHasSerialNumber(serialNumber != null);
                    if (serialNumber != null) {
                        result.setHasSerialNumberOfCorrectLength(serialNumber.length() >= 16);
                    }

                    result.setHasOrganizationName(getKeyStringValue(jsonObject, "organizationName") != null);

                    if (jsonObject.has("appLaunchURL")) {
                        result.setHasAppLaunchUrl(true);
                        result.setHasAssociatedStoreIdentifiers(jsonObject.has("associatedStoreIdentifiers"));
                    }

                    if (jsonObject.has("webServiceURL")) {
                        result.setHasWebServiceUrl(true);
                        String webServiceUrl = getKeyStringValue(jsonObject, "webServiceURL");
                        result.setWebServiceUrlIsHttps(webServiceUrl.toLowerCase().startsWith("https://"));
                    }

                    if (jsonObject.has("authenticationToken")) {
                        result.setHasAuthenticationToken(true);
                        String authToken = getKeyStringValue(jsonObject, "authenticationToken");
                        result.setAuthenticationTokenCorrectLength(authToken.length() >= 16);
                    }

                    if (result.hasAuthenticationToken() && !result.hasWebServiceUrl()) {
                        result.setAuthenticationTokenRequiresWebServiceUrl(true);
                    }

                    if (result.hasWebServiceUrl() && !result.hasAuthenticationToken()) {
                        result.setWebServiceUrlRequiresAuthenticationToken(true);
                    }
                }
                else if (entryName.equals("signature")) {
                    result.setHasSignature(true);
                    signatureFile = zipInputStream.readAllBytes();
                }
                else if (entryName.equals("icon.png")) {
                    result.setHasIcon1x(true);
                }
                else if (entryName.equals("icon@2x.png")) {
                    result.setHasIcon2x(true);
                }
                else if (entryName.equals("icon@3x.png")) {
                    result.setHasIcon3x(true);
                }
            }
        }

        // The signature is "detached" which means the signed content (manifestFile) is not embedded within the signature itself;
        // instead, the signature is meant to verify external content.

        if (result.hasManifest()) {
            Security.addProvider(new BouncyCastleProvider());
            var signedCms = new CMSSignedData(new CMSProcessableByteArray(manifestFile), signatureFile);

            checkSignature(signedCms);

            SignerInformation signer = signedCms.getSignerInfos().iterator().next();

            // "There are two certificates attached. One is the PassType certificate. One is the WWDR certificate."
            X509CertificateHolder passKitCertificate = null;

            for (X509CertificateHolder cert : signedCms.getCertificates().getMatches(null)) {
                if (cert.getSerialNumber().toString(16).equalsIgnoreCase(G4WWDRCertificateSerialNumber)) {
                    result.setSignedByApple(true);
                    result.setWwdrCertificateFound(true);
                    result.setWwdrCertificateIsCorrectVersion(true);
                }
                else {
                    // "Find another cert issued by Apple Inc."
                    X500Name issuerName = cert.getIssuer();
                    String issuerOrg = issuerName.getRDNs(BCStyle.O)[0].getFirst().getValue().toString();
                    String issuerCN = issuerName.getRDNs(BCStyle.CN)[0].getFirst().getValue().toString();

                    if ("Apple Inc.".equals(issuerOrg) && "Apple Worldwide Developer Relations Certification Authority".equals(issuerCN)) {
                        passKitCertificate = cert;
                    }
                }
            }

            if (passKitCertificate != null) {
                result.setPassKitCertificateFound(true);

               for (ASN1ObjectIdentifier oid : passKitCertificate.getExtensions().getExtensionOIDs()) {
                    // "1.2.840.113635.100.6.1.16 is the OID of the problematic part I think.
                    // This is an Apple custom extension (1.2.840.113635.100.6.1.16) and in good passes, 
                    // the value matches the pass type identifier."
                    if (oid.getId().equals("1.2.840.113635.100.6.1.16")) {
                        byte[] rawData = passKitCertificate.getExtension(oid).getExtnValue().getOctets();
                        var value = new String(rawData, StandardCharsets.US_ASCII).substring(2);
                        result.setPassKitCertificateNameCorrect(value.equals(manifestPassTypeIdentifier));
                        break;
                    }
               }

               result.setPassKitCertificateExpired(passKitCertificate.getNotAfter().before(new Date()));

               X500Name issuerName = passKitCertificate.getIssuer();
               String passKitIssuerOrg = issuerName.getRDNs(BCStyle.O)[0].getFirst().getValue().toString();
               String passKitIssuerCommonName = issuerName.getRDNs(BCStyle.CN)[0].getFirst().getValue().toString();

               var orgIsApple = passKitIssuerOrg.equals("Apple Inc.");
               var cnIsWWDR = passKitIssuerCommonName.equals("Apple Worldwide Developer Relations Certification Authority");

               result.setPassKitCertificateIssuedByApple(orgIsApple && cnIsWWDR);
            }

            // "Now check the subject and type idenfier match."
            X509Certificate signerCertificate = signerCertificate(signedCms, signer);
            var certName = new X500Name(signerCertificate.getSubjectX500Principal().getName());

            var certificateCommonName = certName.getRDNs(BCStyle.CN)[0].getFirst().getValue().toString();
            var signaturePassTypeIdentifier = certificateCommonName.replace("Pass Type ID: ", "");

            var certificateOrganisationUnit = certName.getRDNs(BCStyle.OU)[0].getFirst().getValue().toString();

            result.setHasSignatureExpired(signerCertificate.getNotAfter().before(new Date()));
            result.setSignatureExpirationDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(signerCertificate.getNotAfter()));

            result.setPassTypeIdentifierMatches(manifestPassTypeIdentifier.equals(signaturePassTypeIdentifier));
            result.setTeamIdentifierMatches(manifestTeamIdentifier.equals(certificateOrganisationUnit));
        }

        return result;
    }

    private static String getKeyStringValue(JSONObject jsonObject, String key) {
        return jsonObject.has(key) ? jsonObject.getString(key) : null;
    }

    private static void checkSignature(CMSSignedData signedCms) throws Exception {
        SignerInformation signer = signedCms.getSignerInfos().iterator().next();

        @SuppressWarnings("unchecked")
        Collection<X509CertificateHolder> certCollection = signedCms.getCertificates().getMatches(signer.getSID());

        var certFactory = CertificateFactory.getInstance("X.509");
        var inStream = new ByteArrayInputStream(certCollection.iterator().next().getEncoded());
        var cert = (X509Certificate) certFactory.generateCertificate(inStream);
        // Verify the signature using the public key in the certificate
        if (!signer.verify(new JcaSignerInfoVerifierBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(cert))) {
            throw new Exception("Signature verification failed.");
        }
    }

    private static X509Certificate signerCertificate(CMSSignedData signedCms, SignerInformation signer) throws Exception {
        SignerId signerId = signer.getSID();

        @SuppressWarnings("unchecked")
        var certHolder = (X509CertificateHolder) signedCms.getCertificates().getMatches(signerId).iterator().next();

        var certFactory = CertificateFactory.getInstance("X.509");
        return (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(certHolder.getEncoded()));
    }
    
}
