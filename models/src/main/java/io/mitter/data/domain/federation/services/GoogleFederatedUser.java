package io.mitter.data.domain.federation.services;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.mitter.data.domain.annotations.Identifiable;
import io.mitter.data.domain.application.Application;
import io.mitter.data.domain.federation.FederatedUser;
import io.mitter.data.domain.user.User;
import io.mitter.data.domain.user.locators.UserLocator;

import java.util.List;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeName("google-federated-user")
public class GoogleFederatedUser extends FederatedUser {
    private String googleUserId;
    private GoogleFederationCredentials googleFederationCredentials;

    public GoogleFederatedUser(List<UserLocator> userLocators,
                               String federatedUserId,
                               User referencedUser,
                               Identifiable<Application> applicationId,
                               String googleUserId) {
        super(userLocators, federatedUserId, referencedUser, applicationId);
        this.googleUserId = googleUserId;
    }

    public GoogleFederatedUser() {}

    public String getGoogleUserId() {
        return googleUserId;
    }

    @Override
    public GoogleFederationCredentials getFederationCredentials() {
        return this.googleFederationCredentials;
    }

    public void setFederationCredentials(GoogleFederationCredentials googleFederationCredentials) {
        this.googleFederationCredentials = googleFederationCredentials;
    }

    public GoogleFederationCredentials getGoogleFederationCredentials() {
        return googleFederationCredentials;
    }

    public GoogleFederatedUser setGoogleUserId(String googleUserId) {
        this.googleUserId = googleUserId;
        return this;
    }

    public GoogleFederatedUser setGoogleFederationCredentials(GoogleFederationCredentials googleFederationCredentials) {
        this.googleFederationCredentials = googleFederationCredentials;
        return this;
    }

    @Override
    public Class<? extends FederatedUser> type() {
        return this.getClass();
    }

    @Override
    public String toString() {
        return "GoogleFederatedUser{" +
                "googleUserId='" + googleUserId + '\'' +
                ", googleFederationCredentials=" + googleFederationCredentials +
                "} " + super.toString();
    }
}
