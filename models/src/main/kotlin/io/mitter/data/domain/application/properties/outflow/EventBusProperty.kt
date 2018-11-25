package io.mitter.data.domain.application.properties.outflow

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName
import io.mitter.data.domain.annotations.ApplicationPropertySystemName
import io.mitter.data.domain.application.properties.ApplicationProperty
import io.mitter.data.domain.application.properties.StandardApplicationProperty
import io.mitter.data.domain.application.properties.external.aws.AwsSnsTopicProperty
import io.mitter.data.domain.application.properties.external.aws.AwsSqsQueueProperty

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
open class EventBusProperty(
    open var active: Boolean
) : ApplicationProperty()

@JsonTypeName(StandardApplicationProperty.AwsSqsEventBusProperty)
@ApplicationPropertySystemName(StandardApplicationProperty.AwsSqsEventBusProperty)
data class AwsSqsQueueEventBus(
    @JsonProperty("awsSqsQueueProperty")
    val awsSqsQueueProperty: AwsSqsQueueProperty? = null,
    @JsonProperty("active")
    override var active: Boolean
) : EventBusProperty(active)

@JsonTypeName(StandardApplicationProperty.AwsSnsEventBusProperty)
@ApplicationPropertySystemName(StandardApplicationProperty.AwsSnsEventBusProperty)
data class AwsSnsTopicEventBus(
    @JsonProperty("awsSnsTopicProperty")
    val awsSnsTopicProperty: AwsSnsTopicProperty? = null,
    @JsonProperty("active")
    override var active: Boolean
) : EventBusProperty(active)
