package io.mitter.data.domain.application.properties;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.mitter.data.domain.annotations.Identifiable;
import io.mitter.data.domain.application.Application;
import io.mitter.data.domain.application.properties.external.aws.AwsAccessCredentials;
import io.mitter.data.domain.application.properties.external.aws.AwsSnsTopicProperty;
import io.mitter.data.domain.application.properties.external.aws.AwsSqsQueueProperty;
import io.mitter.data.domain.application.properties.external.google.GoogleApiCredential;
import io.mitter.data.domain.application.properties.external.google.GoogleOAuthCredential;
import io.mitter.data.domain.application.properties.external.ouath.OAuthIntegrationProperty;
import io.mitter.data.domain.application.properties.messaging.mechanism.FCMProperties;
import io.mitter.data.domain.application.properties.outflow.AwsSnsTopicEventBus;
import io.mitter.data.domain.application.properties.outflow.AwsSqsQueueEventBus;
import io.mitter.data.domain.application.properties.outflow.TransactionalWebhookProperty;
import io.mitter.models.commons.AuditInfo;
import io.mitter.models.commons.Auditable;
import org.jetbrains.annotations.NotNull;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="systemName")
@JsonSubTypes({
    @JsonSubTypes.Type(value = OAuthIntegrationProperty.class),
    @JsonSubTypes.Type(value = GoogleOAuthCredential.class),
    @JsonSubTypes.Type(value = AwsAccessCredentials.class),
    @JsonSubTypes.Type(value = FCMProperties.class),
    @JsonSubTypes.Type(value = GoogleApiCredential.class),
    @JsonSubTypes.Type(value = TransactionalWebhookProperty.class),
    @JsonSubTypes.Type(value = AwsSqsQueueProperty.class),
    @JsonSubTypes.Type(value = AwsSnsTopicProperty.class),
    @JsonSubTypes.Type(value = AwsSqsQueueEventBus.class),
    @JsonSubTypes.Type(value = AwsSnsTopicEventBus.class)
})
public abstract class ApplicationProperty implements Auditable {
    private String systemName;
    private String instanceName;
    private Identifiable<Application> applicationId;
    private boolean isDefault;
    private AuditInfo auditInfo;

    public ApplicationProperty(String systemName, String instanceName) {
        this.systemName = systemName;
        this.instanceName = instanceName;
    }

    public ApplicationProperty() {}

    public String getSystemName() {
        return systemName;
    }

    public ApplicationProperty setSystemName(String systemName) {
        this.systemName = systemName;
        return this;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public ApplicationProperty setInstanceName(String instanceName) {
        this.instanceName = instanceName;
        return this;
    }

    public Identifiable<Application> getApplicationId() {
        return applicationId;
    }

    public ApplicationProperty setApplicationId(Identifiable<Application> applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public ApplicationProperty setDefault(boolean aDefault) {
        isDefault = aDefault;
        return this;
    }

    @Override
    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    @Override
    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    @Override
    public String toString() {
        return "ApplicationProperty{" +
                "systemName='" + systemName + '\'' +
                ", instanceName='" + instanceName + '\'' +
                ", applicationId='" + ((applicationId == null)?"null":applicationId.domainId()) + '\'' +
                ", auditInfo='" + auditInfo + '\'' +
                '}';
    }
}
