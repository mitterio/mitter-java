package io.mitter.data.domain.user;

/**
 * The verification status of a certain locator
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public enum VerificationStatus {
    /**
     * The locator has been verified by the issuing authority
     */
    Verified,
    /**
     * The locator has not been verified
     */
    Unverified,
    /**
     * The status has not been provided by the implementing infrastructure
     */
    StatusNotProvided,
    /**
     * A request for verification has been initiated, but the result of the same has not been received
     */
    VerificationPending,
    /**
     * This locator does not config verification or a verification is not applicable for locators of this type
     */
    VerificationNotApplicable
}
