package io.mitter.data.domain.federation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mitter.data.domain.annotations.Identifiable;
import io.mitter.data.domain.user.User;

import java.util.List;

/**
 * Holds the result of a federated user registration. An instance of this class denotes a successful registration,
 * with other optional post-actions pending or completed.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class FederatedUserRegistration {
    /**
     * The federated user that requested federation.
     */
    private FederatedUser federatedUser;
    /**
     * The user that was auto-linked to (in case of such a provision).
     */
    private Identifiable<User> linkedUser;
    /**
     * The status of this user's registration. Refer to {@link FederatedUserRegistrationState} for possible values
     * and their semantics.
     */
    private FederatedUserRegistrationState registrationState;
    /**
     * The external service that identifies this uer.
     */
    private String federatingService;
    /**
     * A serialized form of the tokens that were issued by this service. The serialization is done in context
     * and must be used by consumers against the {@link #federatingService} to consume.
     */
    private String externalServiceTokens;
    private String autoLoginToken;

    private boolean errored = false;

    @JsonIgnore
    private Exception causeOfFailure;
    private boolean thirdPartyError;
    private String errorMessage;

    public String getThirdPartyErrorCode() {
        return thirdPartyErrorCode;
    }

    public FederatedUserRegistration setThirdPartyErrorCode(String thirdPartyErrorCode) {
        this.thirdPartyErrorCode = thirdPartyErrorCode;
        return this;
    }

    private String thirdPartyErrorCode;

    /**
     * The redirect-uri used for this authentication. This does not necessarily have to be web URI, it merely references
     * a resource hosted by the third-party application that will act as an entry point for further processing of this
     * registration.
     */
    @JsonIgnore
    private String redirectUri;

    /**
     * A list of candidates that can be auto-linked to this user, based on various criteria.
     */
    private List<User> autoLinkCandidates;

    public FederatedUser getFederatedUser() {
        return federatedUser;
    }

    public FederatedUserRegistration setFederatedUser(FederatedUser federatedUser) {
        this.federatedUser = federatedUser;
        return this;
    }

    public Identifiable<User> getLinkedUser() {
        return linkedUser;
    }

    public FederatedUserRegistration setLinkedUser(Identifiable<User> linkedUser) {
        this.linkedUser = linkedUser;
        return this;
    }

    public List<User> getAutoLinkCandidates() {
        return autoLinkCandidates;
    }

    public FederatedUserRegistration setAutoLinkCandidates(List<User> autoLinkCandidates) {
        this.autoLinkCandidates = autoLinkCandidates;
        return this;
    }

    public FederatedUserRegistrationState getRegistrationState() {
        return registrationState;
    }

    public FederatedUserRegistration setRegistrationState(FederatedUserRegistrationState registrationState) {
        this.registrationState = registrationState;
        return this;
    }

    public String getFederatingService() {
        return federatingService;
    }

    public FederatedUserRegistration setFederatingService(String federatingService) {
        this.federatingService = federatingService;
        return this;
    }

    public String getExternalServiceTokens() {
        return externalServiceTokens;
    }

    public FederatedUserRegistration setExternalServiceTokens(String externalServiceTokens) {
        this.externalServiceTokens = externalServiceTokens;
        return this;
    }

    public String getAutoLoginToken() {
        return autoLoginToken;
    }

    public void setAutoLoginToken(String autoLoginToken) {
        this.autoLoginToken = autoLoginToken;
    }


    public String getRedirectUri() {
        return redirectUri;
    }

    public FederatedUserRegistration setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public FederatedUserRegistration setErrored(boolean errored) {
        this.errored = errored;
        return this;
    }

    public boolean isErrored() {
        return errored;
    }

    @JsonIgnore
    public Exception getCauseOfFailure() {
        return causeOfFailure;
    }

    public FederatedUserRegistration setCauseOfFailure(Exception causeOfFailure) {
        this.causeOfFailure = causeOfFailure;
        return this;
    }

    public boolean isThirdPartyError() {
        return thirdPartyError;
    }

    public FederatedUserRegistration setThirdPartyError(boolean thirdPartyError) {
        this.thirdPartyError = thirdPartyError;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public FederatedUserRegistration setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
