package io.mitter.data.domain.federation.services;

import io.mitter.data.domain.federation.FederationCredentials;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class GoogleFederationCredentials extends FederationCredentials {
    private String accessToken;
    private String refreshToken;

    public GoogleFederationCredentials(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public GoogleFederationCredentials() {}

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public GoogleFederationCredentials setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public GoogleFederationCredentials setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    // TODO. Use LogUtil.secure for this (will need to move LogUtil from commons to a package that models can depend on)
    @Override
    public String toString() {
        return "GoogleFederationCredentials{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                "} ";
    }
}
