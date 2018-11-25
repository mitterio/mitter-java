package io.mitter.data.domain.application.properties.external;

import io.mitter.data.domain.application.properties.ApplicationProperty;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
public abstract class ServiceIntegrationProperty extends ApplicationProperty {
    public ServiceIntegrationProperty(String systemName, String instanceName) {
        super(systemName, instanceName);
    }

    public ServiceIntegrationProperty() {}

    @Override
    public String toString() {
        return "ServiceIntegrationProperty{} <- " + super.toString();
    }
}
