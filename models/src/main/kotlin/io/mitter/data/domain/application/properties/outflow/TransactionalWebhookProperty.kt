package io.mitter.data.domain.application.properties.outflow

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeName
import io.mitter.data.domain.annotations.ApplicationPropertySystemName
import io.mitter.data.domain.application.properties.ApplicationProperty
import io.mitter.data.domain.application.properties.StandardApplicationProperty

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeName(StandardApplicationProperty.TransactionalWebhookProperty)
@ApplicationPropertySystemName(StandardApplicationProperty.TransactionalWebhookProperty)
data class TransactionalWebhookProperty(
    @JsonProperty("webhookUri")
    val webhookUri: String,

    @JsonProperty("eventSubscriptions")
    val eventSubscriptions: List<String> = emptyList()
) : ApplicationProperty()
