package io.mitter.data.domain.application.properties.messaging.mechanism;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.mitter.data.domain.annotations.ApplicationPropertySystemName;
import io.mitter.data.domain.application.properties.StandardApplicationProperty;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
@JsonTypeName(StandardApplicationProperty.FcmProperties)
@ApplicationPropertySystemName(StandardApplicationProperty.FcmProperties)
public class FCMProperties extends MessagingMechanismProperty {
    private String serverKey;

    public FCMProperties(String systemName, String instanceName) {
        super(systemName, instanceName);
    }

    public FCMProperties() {}

    public String getServerKey() {
        return serverKey;
    }

    public FCMProperties setServerKey(String serverKey) {
        this.serverKey = serverKey;
        return this;
    }

    @Override
    public String toString() {
        return "FCMProperties{" +
                "serverKey=" + ((serverKey == null) ? null:"<redacted>") + '\'' +
                "} <- " + super.toString();
    }
}
