package io.mitter.data.domain.application.properties.external.google;

import io.mitter.data.domain.application.properties.external.ServiceIntegrationProperty;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
public abstract class GoogleCredential extends ServiceIntegrationProperty {
    private String projectNumber;
    private String applicationName;

    public GoogleCredential(String systemName, String instanceName) {
        super(systemName, instanceName);
    }

    public GoogleCredential() {}

    public String getProjectNumber() {
        return projectNumber;
    }

    public GoogleCredential setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
        return this;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public GoogleCredential setApplicationName(String applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    @Override
    public String toString() {
        return "GoogleCredential{" +
                "projectNumber='" + projectNumber + '\'' +
                ", applicationName='" + applicationName + '\'' +
                "} <- " + super.toString();
    }
}
