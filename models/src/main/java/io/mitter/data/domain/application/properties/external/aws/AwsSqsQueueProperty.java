package io.mitter.data.domain.application.properties.external.aws;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.mitter.data.domain.annotations.ApplicationPropertySystemName;
import io.mitter.data.domain.application.properties.StandardApplicationProperty;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeName(StandardApplicationProperty.AwsSqsQueueProperty)
@ApplicationPropertySystemName(StandardApplicationProperty.AwsSqsQueueProperty)
public class AwsSqsQueueProperty extends AwsServiceIntegrationProperty {
    private String queueName;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}
