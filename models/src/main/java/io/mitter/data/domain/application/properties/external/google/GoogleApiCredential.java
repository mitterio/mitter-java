package io.mitter.data.domain.application.properties.external.google;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.mitter.data.domain.annotations.ApplicationPropertySystemName;
import io.mitter.data.domain.application.properties.StandardApplicationProperty;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
@JsonTypeName(StandardApplicationProperty.GoogleApiCredential)
@ApplicationPropertySystemName(StandardApplicationProperty.GoogleApiCredential)
public class GoogleApiCredential extends GoogleCredential {
    private String apiKey;

    public GoogleApiCredential(String systemName, String instanceName) {
        super(systemName, instanceName);
    }

    public GoogleApiCredential() {}

    public String getApiKey() {
        return apiKey;
    }

    public GoogleApiCredential setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    @Override
    public String toString() {
        return "GoogleApiCredential{" +
                "apiKey='" + apiKey + '\'' +
                "} <- " + super.toString();
    }
}
