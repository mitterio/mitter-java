package io.mitter.data.domain.application.properties.messaging.mechanism;

import io.mitter.data.domain.application.properties.messaging.MessagingProperty;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
public class MessagingMechanismProperty extends MessagingProperty {
    public MessagingMechanismProperty(String systemName, String instanceName) {
        super(systemName, instanceName);
    }

    public MessagingMechanismProperty() {}

    @Override
    public String toString() {
        return "MessagingMechanismProperty{} <- " + super.toString();
    }
}
