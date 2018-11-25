package io.mitter.data.domain.application.properties.external.google;

import com.fasterxml.jackson.databind.JsonNode;
import io.mitter.data.domain.annotations.ApplicationPropertySystemName;
import io.mitter.data.domain.application.properties.StandardApplicationProperty;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
@ApplicationPropertySystemName(StandardApplicationProperty.GoogleServiceAccountCredential)
public class GoogleServiceAccountCredential extends GoogleCredential {
    private JsonNode config;

    public GoogleServiceAccountCredential(String systemName, String instanceName) {
        super(systemName, instanceName);
    }

    public GoogleServiceAccountCredential() {}

    public JsonNode getConfig() {
        return config;
    }

    public GoogleServiceAccountCredential setConfig(JsonNode config) {
        this.config = config;
        return this;
    }

    @Override
    public String toString() {
        return "GoogleServiceAccountCredential{" +
                "config=" + config +
                "} <- " + super.toString();
    }
}
