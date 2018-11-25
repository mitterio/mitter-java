package io.mitter.data.domain.application;

import io.mitter.data.domain.annotations.Identifiable;

/**
 * @author Ganessh Kumar (ganessh@nomadl.in)
 */
public class ApplicationProfile {
    private Identifiable<Application> applicationId;
    private String description;
    private String website;

    public Identifiable<Application> getApplicationId() {
        return applicationId;
    }

    public ApplicationProfile setApplicationId(Identifiable<Application> applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ApplicationProfile setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public ApplicationProfile setWebsite(String website) {
        this.website = website;
        return this;
    }

    @Override
    public String toString() {
        return "ApplicationProfile{" +
                "applicationId=" + applicationId +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
