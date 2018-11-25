package io.mitter.data.domain.application.properties.messaging.mechanism;

import io.mitter.data.domain.annotations.ApplicationPropertySystemName;
import io.mitter.data.domain.application.properties.StandardApplicationProperty;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
@ApplicationPropertySystemName(StandardApplicationProperty.ApnsProperties)
public class APNSProperties extends MessagingMechanismProperty {
    private String keyId;

    public APNSProperties() {}

    public APNSProperties(String systemName, String instanceName) {
        super(systemName, instanceName);
    }

    public String getKeyId() {
        return keyId;
    }

    public APNSProperties setKeyId(String keyId) {
        this.keyId = keyId;
        return this;
    }

    @Override
    public String toString() {
        return "APNSProperties{" +
                "keyId='" + keyId + '\'' +
                "} <- " + super.toString();
    }
}
