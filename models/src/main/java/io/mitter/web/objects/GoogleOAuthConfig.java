package io.mitter.web.objects;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class GoogleOAuthConfig {
    private PayloadUri googleRedirectUri;
    private PayloadUri googleOAuthEndpoint;
    private String googleOAuthClientId;
    private PayloadUri oauthRedirectUri;
    private Boolean serviceActive;


    public String getGoogleOAuthClientId() {
        return googleOAuthClientId;
    }

    public void setGoogleOAuthClientId(String googleOAuthClientId) {
        this.googleOAuthClientId = googleOAuthClientId;
    }

    public Boolean getServiceActive() {
        return serviceActive;
    }

    public void setServiceActive(Boolean serviceActive) {
        this.serviceActive = serviceActive;
    }

    public PayloadUri getGoogleRedirectUri() {
        return googleRedirectUri;
    }

    public void setGoogleRedirectUri(PayloadUri googleRedirectUri) {
        this.googleRedirectUri = googleRedirectUri;
    }

    public PayloadUri getGoogleOAuthEndpoint() {
        return googleOAuthEndpoint;
    }

    public void setGoogleOAuthEndpoint(PayloadUri googleOAuthEndpoint) {
        this.googleOAuthEndpoint = googleOAuthEndpoint;
    }

    public PayloadUri getOauthRedirectUri() {
        return oauthRedirectUri;
    }

    public void setOauthRedirectUri(PayloadUri oauthRedirectUri) {
        this.oauthRedirectUri = oauthRedirectUri;
    }
}
