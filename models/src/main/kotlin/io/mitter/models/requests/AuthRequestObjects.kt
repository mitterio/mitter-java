package io.mitter.models.requests

import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.subscriber.Subscriber
import io.mitter.models.hosted.ErrorResponseBody

/**
 * Common objects for Subscriber authentication.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class SubscriberAuthRequest(
    @JsonProperty("subscriberEmail") var subscriberEmail: String,
    @JsonProperty("hashedPassword") var hashedPassword: String
)

data class SubscriberAuthResponse(
        @JsonProperty("subscriberId") var subscriberId: Identifiable<Subscriber>?,
        @JsonProperty("issuedWebToken") var issuedWebToken: IssuedWebToken?,
        @JsonProperty("error") var error: ErrorResponseBody?
)
