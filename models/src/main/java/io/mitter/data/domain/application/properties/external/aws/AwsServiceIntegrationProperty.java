package io.mitter.data.domain.application.properties.external.aws;

import io.mitter.data.domain.application.properties.external.ServiceIntegrationProperty;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public abstract class AwsServiceIntegrationProperty extends ServiceIntegrationProperty {
    private AwsAccessCredentials awsAccessCredentials;
    private String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public AwsAccessCredentials getAwsAccessCredentials() {
        return awsAccessCredentials;
    }

    public void setAwsAccessCredentials(AwsAccessCredentials awsAccessCredentials) {
        this.awsAccessCredentials = awsAccessCredentials;
    }
}
