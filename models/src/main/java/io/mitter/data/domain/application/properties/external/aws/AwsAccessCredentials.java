package io.mitter.data.domain.application.properties.external.aws;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.mitter.data.domain.annotations.ApplicationPropertySystemName;
import io.mitter.data.domain.application.properties.StandardApplicationProperty;
import io.mitter.data.domain.application.properties.external.ServiceIntegrationProperty;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeName(StandardApplicationProperty.AwsAccessCredentials)
@ApplicationPropertySystemName(StandardApplicationProperty.AwsAccessCredentials)
public class AwsAccessCredentials extends ServiceIntegrationProperty {
    private String awsAccessKey;
    private String awsAccessSecret;
    private String awsRegion;

    public String getAwsAccessKey() {
        return awsAccessKey;
    }

    public AwsAccessCredentials setAwsAccessKey(String awsAccessKey) {
        this.awsAccessKey = awsAccessKey;
        return this;
    }

    public String getAwsAccessSecret() {
        return awsAccessSecret;
    }

    public AwsAccessCredentials setAwsAccessSecret(String awsAccessSecret) {
        this.awsAccessSecret = awsAccessSecret;
        return this;
    }

    public String getAwsRegion() {
        return awsRegion;
    }

    public AwsAccessCredentials setAwsRegion(String awsRegion) {
        this.awsRegion = awsRegion;
        return this;
    }
}
