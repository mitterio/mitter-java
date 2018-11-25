package io.mitter.data.domain.application.properties.external.aws;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.mitter.data.domain.annotations.ApplicationPropertySystemName;
import io.mitter.data.domain.application.properties.StandardApplicationProperty;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeName(StandardApplicationProperty.AwsSnsTopicProperty)
@ApplicationPropertySystemName(StandardApplicationProperty.AwsSnsTopicProperty)
public class AwsSnsTopicProperty extends AwsServiceIntegrationProperty {
    private String topicName;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
