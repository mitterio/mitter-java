package io.mitter.data.domain.application.properties.messaging;

import io.mitter.data.domain.application.properties.ApplicationProperty;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
public abstract class MessagingProperty extends ApplicationProperty {
    public MessagingProperty(String systemName, String instanceName) {
        super(systemName, instanceName);
    }

    public MessagingProperty() {}

    @Override
    public String toString() {
        return "MessagingProperty{} <- " + super.toString();
    }
}
