package phlppnhllngr.pkpassvalidator.validation;

// https://github.com/tomasmcguinness/pkpassvalidator/blob/master/PassValidator.Web/Validation/ValidationResult.cs
public class ValidationResult {

    private boolean hasPass;
    private boolean hasManifest;
    private boolean hasSignature;
    private boolean teamIdentifierMatches;
    private boolean passTypeIdentifierMatches;
    private boolean signedByApple;
    private boolean hasSignatureExpired = true;
    private String signatureExpirationDate;
    private boolean hasIcon3x;
    private boolean hasIcon2x;
    private boolean hasIcon1x;
    private boolean hasPassTypeIdentifier;
    private boolean hasTeamIdentifier;
    private boolean hasDescription;
    private boolean hasFormatVersion;
    private boolean hasSerialNumber;
    private boolean hasSerialNumberOfCorrectLength;
    private boolean hasOrganizationName;
    private boolean hasAppLaunchUrl;
    private boolean hasAssociatedStoreIdentifiers;
    private boolean wwdrCertificateExpired = true;
    private boolean wwdrCertificateSubjectMatches;
    private boolean wwdrCertificateIsCorrectVersion;
    private boolean hasAuthenticationToken;
    private boolean hasWebServiceUrl;
    private boolean webServiceUrlIsHttps;
    private boolean authenticationTokenRequiresWebServiceUrl;
    private boolean webServiceUrlRequiresAuthenticationToken;
    private boolean passKitCertificateNameCorrect;
    private boolean passKitCertificateExpired = true;
    private boolean wwdrCertificateFound;
    private boolean passKitCertificateFound;
    private boolean authenticationTokenCorrectLength;
    private boolean passKitCertificateIssuedByApple;


    @Override
    public String toString() {
        return "ValidationResult [hasPass=" + hasPass + ", hasManifest=" + hasManifest + ", hasSignature="
                + hasSignature + ", teamIdentifierMatches=" + teamIdentifierMatches + ", passTypeIdentifierMatches="
                + passTypeIdentifierMatches + ", signedByApple=" + signedByApple + ", hasSignatureExpired="
                + hasSignatureExpired + ", signatureExpirationDate=" + signatureExpirationDate + ", hasIcon3x="
                + hasIcon3x + ", hasIcon2x=" + hasIcon2x + ", hasIcon1x=" + hasIcon1x + ", hasPassTypeIdentifier="
                + hasPassTypeIdentifier + ", hasTeamIdentifier=" + hasTeamIdentifier + ", hasDescription="
                + hasDescription + ", hasFormatVersion=" + hasFormatVersion + ", hasSerialNumber=" + hasSerialNumber
                + ", hasSerialNumberOfCorrectLength=" + hasSerialNumberOfCorrectLength + ", hasOrganizationName="
                + hasOrganizationName + ", hasAppLaunchUrl=" + hasAppLaunchUrl + ", hasAssociatedStoreIdentifiers="
                + hasAssociatedStoreIdentifiers + ", wwdrCertificateExpired=" + wwdrCertificateExpired
                + ", wwdrCertificateSubjectMatches=" + wwdrCertificateSubjectMatches
                + ", wwdrCertificateIsCorrectVersion=" + wwdrCertificateIsCorrectVersion + ", hasAuthenticationToken="
                + hasAuthenticationToken + ", hasWebServiceUrl=" + hasWebServiceUrl + ", webServiceUrlIsHttps="
                + webServiceUrlIsHttps + ", authenticationTokenRequiresWebServiceUrl="
                + authenticationTokenRequiresWebServiceUrl + ", webServiceUrlRequiresAuthenticationToken="
                + webServiceUrlRequiresAuthenticationToken + ", passKitCertificateNameCorrect="
                + passKitCertificateNameCorrect + ", passKitCertificateExpired=" + passKitCertificateExpired
                + ", wwdrCertificateFound=" + wwdrCertificateFound + ", passKitCertificateFound="
                + passKitCertificateFound + ", authenticationTokenCorrectLength=" + authenticationTokenCorrectLength
                + ", passKitCertificateIssuedByApple=" + passKitCertificateIssuedByApple + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (hasPass ? 1231 : 1237);
        result = prime * result + (hasManifest ? 1231 : 1237);
        result = prime * result + (hasSignature ? 1231 : 1237);
        result = prime * result + (teamIdentifierMatches ? 1231 : 1237);
        result = prime * result + (passTypeIdentifierMatches ? 1231 : 1237);
        result = prime * result + (signedByApple ? 1231 : 1237);
        result = prime * result + (hasSignatureExpired ? 1231 : 1237);
        result = prime * result + ((signatureExpirationDate == null) ? 0 : signatureExpirationDate.hashCode());
        result = prime * result + (hasIcon3x ? 1231 : 1237);
        result = prime * result + (hasIcon2x ? 1231 : 1237);
        result = prime * result + (hasIcon1x ? 1231 : 1237);
        result = prime * result + (hasPassTypeIdentifier ? 1231 : 1237);
        result = prime * result + (hasTeamIdentifier ? 1231 : 1237);
        result = prime * result + (hasDescription ? 1231 : 1237);
        result = prime * result + (hasFormatVersion ? 1231 : 1237);
        result = prime * result + (hasSerialNumber ? 1231 : 1237);
        result = prime * result + (hasSerialNumberOfCorrectLength ? 1231 : 1237);
        result = prime * result + (hasOrganizationName ? 1231 : 1237);
        result = prime * result + (hasAppLaunchUrl ? 1231 : 1237);
        result = prime * result + (hasAssociatedStoreIdentifiers ? 1231 : 1237);
        result = prime * result + (wwdrCertificateExpired ? 1231 : 1237);
        result = prime * result + (wwdrCertificateSubjectMatches ? 1231 : 1237);
        result = prime * result + (wwdrCertificateIsCorrectVersion ? 1231 : 1237);
        result = prime * result + (hasAuthenticationToken ? 1231 : 1237);
        result = prime * result + (hasWebServiceUrl ? 1231 : 1237);
        result = prime * result + (webServiceUrlIsHttps ? 1231 : 1237);
        result = prime * result + (authenticationTokenRequiresWebServiceUrl ? 1231 : 1237);
        result = prime * result + (webServiceUrlRequiresAuthenticationToken ? 1231 : 1237);
        result = prime * result + (passKitCertificateNameCorrect ? 1231 : 1237);
        result = prime * result + (passKitCertificateExpired ? 1231 : 1237);
        result = prime * result + (wwdrCertificateFound ? 1231 : 1237);
        result = prime * result + (passKitCertificateFound ? 1231 : 1237);
        result = prime * result + (authenticationTokenCorrectLength ? 1231 : 1237);
        result = prime * result + (passKitCertificateIssuedByApple ? 1231 : 1237);
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ValidationResult other = (ValidationResult) obj;
        if (hasPass != other.hasPass)
            return false;
        if (hasManifest != other.hasManifest)
            return false;
        if (hasSignature != other.hasSignature)
            return false;
        if (teamIdentifierMatches != other.teamIdentifierMatches)
            return false;
        if (passTypeIdentifierMatches != other.passTypeIdentifierMatches)
            return false;
        if (signedByApple != other.signedByApple)
            return false;
        if (hasSignatureExpired != other.hasSignatureExpired)
            return false;
        if (signatureExpirationDate == null) {
            if (other.signatureExpirationDate != null)
                return false;
        } else if (!signatureExpirationDate.equals(other.signatureExpirationDate))
            return false;
        if (hasIcon3x != other.hasIcon3x)
            return false;
        if (hasIcon2x != other.hasIcon2x)
            return false;
        if (hasIcon1x != other.hasIcon1x)
            return false;
        if (hasPassTypeIdentifier != other.hasPassTypeIdentifier)
            return false;
        if (hasTeamIdentifier != other.hasTeamIdentifier)
            return false;
        if (hasDescription != other.hasDescription)
            return false;
        if (hasFormatVersion != other.hasFormatVersion)
            return false;
        if (hasSerialNumber != other.hasSerialNumber)
            return false;
        if (hasSerialNumberOfCorrectLength != other.hasSerialNumberOfCorrectLength)
            return false;
        if (hasOrganizationName != other.hasOrganizationName)
            return false;
        if (hasAppLaunchUrl != other.hasAppLaunchUrl)
            return false;
        if (hasAssociatedStoreIdentifiers != other.hasAssociatedStoreIdentifiers)
            return false;
        if (wwdrCertificateExpired != other.wwdrCertificateExpired)
            return false;
        if (wwdrCertificateSubjectMatches != other.wwdrCertificateSubjectMatches)
            return false;
        if (wwdrCertificateIsCorrectVersion != other.wwdrCertificateIsCorrectVersion)
            return false;
        if (hasAuthenticationToken != other.hasAuthenticationToken)
            return false;
        if (hasWebServiceUrl != other.hasWebServiceUrl)
            return false;
        if (webServiceUrlIsHttps != other.webServiceUrlIsHttps)
            return false;
        if (authenticationTokenRequiresWebServiceUrl != other.authenticationTokenRequiresWebServiceUrl)
            return false;
        if (webServiceUrlRequiresAuthenticationToken != other.webServiceUrlRequiresAuthenticationToken)
            return false;
        if (passKitCertificateNameCorrect != other.passKitCertificateNameCorrect)
            return false;
        if (passKitCertificateExpired != other.passKitCertificateExpired)
            return false;
        if (wwdrCertificateFound != other.wwdrCertificateFound)
            return false;
        if (passKitCertificateFound != other.passKitCertificateFound)
            return false;
        if (authenticationTokenCorrectLength != other.authenticationTokenCorrectLength)
            return false;
        if (passKitCertificateIssuedByApple != other.passKitCertificateIssuedByApple)
            return false;
        return true;
    }


    public boolean hasPass() {
        return hasPass;
    }


    public void setHasPass(boolean hasPass) {
        this.hasPass = hasPass;
    }


    public boolean hasManifest() {
        return hasManifest;
    }


    public void setHasManifest(boolean hasManifest) {
        this.hasManifest = hasManifest;
    }


    public boolean hasSignature() {
        return hasSignature;
    }


    public void setHasSignature(boolean hasSignature) {
        this.hasSignature = hasSignature;
    }


    public boolean isTeamIdentifierMatches() {
        return teamIdentifierMatches;
    }


    public void setTeamIdentifierMatches(boolean teamIdentifierMatches) {
        this.teamIdentifierMatches = teamIdentifierMatches;
    }


    public boolean isPassTypeIdentifierMatches() {
        return passTypeIdentifierMatches;
    }


    public void setPassTypeIdentifierMatches(boolean passTypeIdentifierMatches) {
        this.passTypeIdentifierMatches = passTypeIdentifierMatches;
    }


    public boolean isSignedByApple() {
        return signedByApple;
    }


    public void setSignedByApple(boolean signedByApple) {
        this.signedByApple = signedByApple;
    }


    public boolean hasSignatureExpired() {
        return hasSignatureExpired;
    }


    public void setHasSignatureExpired(boolean hasSignatureExpired) {
        this.hasSignatureExpired = hasSignatureExpired;
    }


    public String getSignatureExpirationDate() {
        return signatureExpirationDate;
    }


    public void setSignatureExpirationDate(String signatureExpirationDate) {
        this.signatureExpirationDate = signatureExpirationDate;
    }


    public boolean hasIcon3x() {
        return hasIcon3x;
    }


    public void setHasIcon3x(boolean hasIcon3x) {
        this.hasIcon3x = hasIcon3x;
    }


    public boolean hasIcon2x() {
        return hasIcon2x;
    }


    public void setHasIcon2x(boolean hasIcon2x) {
        this.hasIcon2x = hasIcon2x;
    }


    public boolean hasIcon1x() {
        return hasIcon1x;
    }


    public void setHasIcon1x(boolean hasIcon1x) {
        this.hasIcon1x = hasIcon1x;
    }


    public boolean hasPassTypeIdentifier() {
        return hasPassTypeIdentifier;
    }


    public void setHasPassTypeIdentifier(boolean hasPassTypeIdentifier) {
        this.hasPassTypeIdentifier = hasPassTypeIdentifier;
    }


    public boolean hasTeamIdentifier() {
        return hasTeamIdentifier;
    }


    public void setHasTeamIdentifier(boolean hasTeamIdentifier) {
        this.hasTeamIdentifier = hasTeamIdentifier;
    }


    public boolean hasDescription() {
        return hasDescription;
    }


    public void setHasDescription(boolean hasDescription) {
        this.hasDescription = hasDescription;
    }


    public boolean hasFormatVersion() {
        return hasFormatVersion;
    }


    public void setHasFormatVersion(boolean hasFormatVersion) {
        this.hasFormatVersion = hasFormatVersion;
    }


    public boolean hasSerialNumber() {
        return hasSerialNumber;
    }


    public void setHasSerialNumber(boolean hasSerialNumber) {
        this.hasSerialNumber = hasSerialNumber;
    }


    public boolean hasSerialNumberOfCorrectLength() {
        return hasSerialNumberOfCorrectLength;
    }


    public void setHasSerialNumberOfCorrectLength(boolean hasSerialNumberOfCorrectLength) {
        this.hasSerialNumberOfCorrectLength = hasSerialNumberOfCorrectLength;
    }


    public boolean hasOrganizationName() {
        return hasOrganizationName;
    }


    public void setHasOrganizationName(boolean hasOrganizationName) {
        this.hasOrganizationName = hasOrganizationName;
    }


    public boolean hasAppLaunchUrl() {
        return hasAppLaunchUrl;
    }


    public void setHasAppLaunchUrl(boolean hasAppLaunchUrl) {
        this.hasAppLaunchUrl = hasAppLaunchUrl;
    }


    public boolean hasAssociatedStoreIdentifiers() {
        return hasAssociatedStoreIdentifiers;
    }


    public void setHasAssociatedStoreIdentifiers(boolean hasAssociatedStoreIdentifiers) {
        this.hasAssociatedStoreIdentifiers = hasAssociatedStoreIdentifiers;
    }


    public boolean isWwdrCertificateExpired() {
        return wwdrCertificateExpired;
    }


    public void setWwdrCertificateExpired(boolean wwdrCertificateExpired) {
        this.wwdrCertificateExpired = wwdrCertificateExpired;
    }


    public boolean isWwdrCertificateSubjectMatches() {
        return wwdrCertificateSubjectMatches;
    }


    public void setWwdrCertificateSubjectMatches(boolean wwdrCertificateSubjectMatches) {
        this.wwdrCertificateSubjectMatches = wwdrCertificateSubjectMatches;
    }


    public boolean isWwdrCertificateIsCorrectVersion() {
        return wwdrCertificateIsCorrectVersion;
    }


    public void setWwdrCertificateIsCorrectVersion(boolean wwdrCertificateIsCorrectVersion) {
        this.wwdrCertificateIsCorrectVersion = wwdrCertificateIsCorrectVersion;
    }


    public boolean hasAuthenticationToken() {
        return hasAuthenticationToken;
    }


    public void setHasAuthenticationToken(boolean hasAuthenticationToken) {
        this.hasAuthenticationToken = hasAuthenticationToken;
    }


    public boolean hasWebServiceUrl() {
        return hasWebServiceUrl;
    }


    public void setHasWebServiceUrl(boolean hasWebServiceUrl) {
        this.hasWebServiceUrl = hasWebServiceUrl;
    }


    public boolean isWebServiceUrlIsHttps() {
        return webServiceUrlIsHttps;
    }


    public void setWebServiceUrlIsHttps(boolean webServiceUrlIsHttps) {
        this.webServiceUrlIsHttps = webServiceUrlIsHttps;
    }


    public boolean isAuthenticationTokenRequiresWebServiceUrl() {
        return authenticationTokenRequiresWebServiceUrl;
    }


    public void setAuthenticationTokenRequiresWebServiceUrl(boolean authenticationTokenRequiresWebServiceUrl) {
        this.authenticationTokenRequiresWebServiceUrl = authenticationTokenRequiresWebServiceUrl;
    }


    public boolean isWebServiceUrlRequiresAuthenticationToken() {
        return webServiceUrlRequiresAuthenticationToken;
    }


    public void setWebServiceUrlRequiresAuthenticationToken(boolean webServiceUrlRequiresAuthenticationToken) {
        this.webServiceUrlRequiresAuthenticationToken = webServiceUrlRequiresAuthenticationToken;
    }


    public boolean isPassKitCertificateNameCorrect() {
        return passKitCertificateNameCorrect;
    }


    public void setPassKitCertificateNameCorrect(boolean passKitCertificateNameCorrect) {
        this.passKitCertificateNameCorrect = passKitCertificateNameCorrect;
    }


    public boolean isPassKitCertificateExpired() {
        return passKitCertificateExpired;
    }


    public void setPassKitCertificateExpired(boolean passKitCertificateExpired) {
        this.passKitCertificateExpired = passKitCertificateExpired;
    }


    public boolean isWwdrCertificateFound() {
        return wwdrCertificateFound;
    }


    public void setWwdrCertificateFound(boolean wwdrCertificateFound) {
        this.wwdrCertificateFound = wwdrCertificateFound;
    }


    public boolean isPassKitCertificateFound() {
        return passKitCertificateFound;
    }


    public void setPassKitCertificateFound(boolean passKitCertificateFound) {
        this.passKitCertificateFound = passKitCertificateFound;
    }


    public boolean isAuthenticationTokenCorrectLength() {
        return authenticationTokenCorrectLength;
    }


    public void setAuthenticationTokenCorrectLength(boolean authenticationTokenCorrectLength) {
        this.authenticationTokenCorrectLength = authenticationTokenCorrectLength;
    }


    public boolean isPassKitCertificateIssuedByApple() {
        return passKitCertificateIssuedByApple;
    }


    public void setPassKitCertificateIssuedByApple(boolean passKitCertificateIssuedByApple) {
        this.passKitCertificateIssuedByApple = passKitCertificateIssuedByApple;
    }

}