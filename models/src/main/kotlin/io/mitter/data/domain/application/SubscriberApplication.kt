package io.mitter.data.domain.application

import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.subscriber.Subscriber

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class SubscriberApplication(
    @JsonProperty("application")
    val application: Application,

    @JsonProperty("applicationProfile")
    val applicationProfile: ApplicationProfile,

    @JsonProperty("subscriber")
    var subscriber: Identifiable<Subscriber>? = null
)
