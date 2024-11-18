package phlppnhllngr.pkpassvalidator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import phlppnhllngr.pkpassvalidator.validation.ValidationResult;
import phlppnhllngr.pkpassvalidator.validation.Validator;

public class Main {

    // Print every check for debugging
    private static final boolean PRINT_EVERYTHING = "true".equals(System.getProperty("pkpassvalidator.printeverything"));

    public static void main(String[] args) throws Exception {
        Path path = Paths.get(args[0]);
        byte[] passContent = Files.readAllBytes(path);
        ValidationResult result = new Validator().validate(passContent);
        String printable = printableResult(result);
        System.out.println(printable);
        int exitCode = printable.lines().anyMatch(line -> line.contains("ISSUE") && !line.contains("not mandatory")) ? 1 : 0;
        System.exit(exitCode);
    }

    // https://github.com/tomasmcguinness/pkpassvalidator/blob/master/PassValidator.Web/Views/Home/Index.cshtml
    private static String printableResult(ValidationResult result) {
        var sb = new StringBuilder();
        sb
            .append("_____________________________________________________________")
            .append(System.lineSeparator())
            .append(System.lineSeparator())
            .append("""
            File structure:
            -------------------------
            * %s Has manifest.json file
            * %s Has pass.json
            * %s Has signature file
            * %s Has icon.png file
            * %s Has icon@2x.png file
            * %s Has icon@3x.png file (not mandatory)
            
            Standard Keys:
            ------------------------
            * %s Has description
            * %s Has format version with value of 1
            * %s Has organizationName
            * %s Has passTypeIdentifier
            * %s Has serialNumber
            * %s Has teamIdentifier
            """.formatted(
                getStatus(result.hasManifest()),
                getStatus(result.hasPass()),
                getStatus(result.hasSignature()),
                getStatus(result.hasIcon1x()),
                getStatus(result.hasIcon2x()),
                getStatus(result.hasIcon3x()),
    
                getStatus(result.hasDescription()),
                getStatus(result.hasFormatVersion()),
                getStatus(result.hasOrganizationName()),
                getStatus(result.hasPassTypeIdentifier()),
                getStatus(result.hasSerialNumber()),
                getStatus(result.hasTeamIdentifier())
        ));
        if (result.hasAssociatedStoreIdentifiers() || result.hasAppLaunchUrl() || PRINT_EVERYTHING) {
            sb
                .append(System.lineSeparator())
                .append("""
                Associated App Keys:
                ------------------------
                * %s Has appLaunchUrl
                * %s Has associatedStoreIdentifiers
                """.formatted(
                    getStatus(result.hasAppLaunchUrl()),
                    getStatus(result.hasAssociatedStoreIdentifiers())
                ));
            if (result.hasAppLaunchUrl() || PRINT_EVERYTHING) {
                sb.append("* %s The appLaunchUrl key requires associatedStoreIdentifiers key".formatted(
                    getStatus(result.hasAssociatedStoreIdentifiers())
                ));            
            }
        }
        if (result.hasWebServiceUrl() || result.hasAuthenticationToken() || PRINT_EVERYTHING) {
            sb
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("""
                Updates:
                ------------------------
                * %s Includes WebServiceUrl
                * %s WebServiceUrl is HTTPS
                * %s Includes AuthenticationToken
                """.formatted(
                    getStatus(result.hasWebServiceUrl()),
                    getStatus(result.isWebServiceUrlIsHttps()),
                    getStatus(result.hasAuthenticationToken())
                ));
            if (result.hasAuthenticationToken() || PRINT_EVERYTHING) {
                sb
                    .append(System.lineSeparator())
                    .append("* %s AuthenticationToken is 16 characters in length".formatted(getStatus(result.isAuthenticationTokenCorrectLength())));
            }
            if (result.isAuthenticationTokenRequiresWebServiceUrl() || PRINT_EVERYTHING) {
                sb
                    .append(System.lineSeparator())
                    .append("* %s AuthenticationToken present but no WebServiceUrl found".formatted(getStatus(result.isAuthenticationTokenRequiresWebServiceUrl())));
            }
            if (result.isWebServiceUrlRequiresAuthenticationToken() || PRINT_EVERYTHING) {
                sb
                    .append(System.lineSeparator())
                    .append("* %s WebServiceUrl requires an AuthenticationToken".formatted(getStatus(result.isWebServiceUrlRequiresAuthenticationToken())));
            }
        }
        sb
            .append(System.lineSeparator())
            .append(System.lineSeparator())
            .append("""
            Signature:
            ------------------------
            * %s PassTypeIdentifier in signature matches value in pass.json
            * %s TeamIdentifier in signature matches value in pass.json

            WWDR Certificate:
            ------------------------
            * %s WWDR Certificate is version (G4)
                    As of November 2021, the Apple PKI page at https://www.apple.com/certificateauthority/
                    lists five "Worldwide Developer Relations" certificates.
                    Only the G4 certificate is valid for signing passes with certificates generated after the 27th of January, 2022.

            PassKit Certificate:
            ------------------------
            * %s PassKit Certificate issued by Apple
            """.formatted(
                getStatus(result.isPassTypeIdentifierMatches()),
                getStatus(result.isTeamIdentifierMatches()),
                getStatus(result.isWwdrCertificateIsCorrectVersion()),
                getStatus(result.isPassKitCertificateIssuedByApple())
            ));
        if (!result.isPassKitCertificateNameCorrect() || PRINT_EVERYTHING) {
            sb
                .append("* %s PassKit Certificate has an incorrect value (https://github.com/tomasmcguinness/pkpassvalidator/wiki/PassKit-certificate-contains-an-incorrect-value)"
                    .formatted(getStatus(!result.isPassKitCertificateNameCorrect()))
                )
                .append(System.lineSeparator());
        }
        sb
            .append("* %s PassKit Certificate in date".formatted(getStatus(!result.isPassKitCertificateExpired())))
            .append(System.lineSeparator())
            .append("_____________________________________________________________");
        return sb.toString();
    }

    private static String getStatus(boolean check) {
        return check ? "OK   " : "ISSUE";
    }

}