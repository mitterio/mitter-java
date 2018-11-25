package io.mitter.data.domain.application.properties.external.ouath;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.mitter.data.domain.annotations.ApplicationPropertySystemName;
import io.mitter.data.domain.application.properties.StandardApplicationProperty;
import io.mitter.data.domain.application.properties.external.ServiceIntegrationProperty;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeName(StandardApplicationProperty.OAuthIntegrationProperty)
@ApplicationPropertySystemName(StandardApplicationProperty.OAuthIntegrationProperty)
public class OAuthIntegrationProperty extends ServiceIntegrationProperty {
    private String oauthRedirectUrl;
    private boolean requireStateSigning;

    public String getOauthRedirectUrl() {
        return oauthRedirectUrl;
    }

    public OAuthIntegrationProperty setOauthRedirectUrl(String oauthRedirectUrl) {
        this.oauthRedirectUrl = oauthRedirectUrl;
        return this;
    }

    public boolean isRequireStateSigning() {
        return requireStateSigning;
    }

    public OAuthIntegrationProperty setRequireStateSigning(boolean requireStateSigning) {
        this.requireStateSigning = requireStateSigning;
        return this;
    }

    @Override
    public String toString() {
        return "OAuthIntegrationProperty{" +
                "oauthRedirectUrl='" + oauthRedirectUrl + '\'' +
                "} <- " + super.toString();
    }
}
