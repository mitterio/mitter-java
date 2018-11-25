package io.mitter.data.domain.application.properties.external.google;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.mitter.data.domain.annotations.ApplicationPropertySystemName;
import io.mitter.data.domain.application.properties.StandardApplicationProperty;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
@JsonTypeName(StandardApplicationProperty.GoogleOAuthCredential)
@ApplicationPropertySystemName(StandardApplicationProperty.GoogleOAuthCredential)
public class GoogleOAuthCredential extends GoogleCredential {
    private String clientId;
    private String clientSecret;

    public GoogleOAuthCredential(String systemName, String instanceName) {
        super(systemName, instanceName);
    }

    public GoogleOAuthCredential() {}

    public String getClientId() {
        return clientId;
    }

    public GoogleOAuthCredential setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public GoogleOAuthCredential setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    @Override
    public String toString() {
        return "GoogleOAuthCredential{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                "} <- " + super.toString();
    }
}
