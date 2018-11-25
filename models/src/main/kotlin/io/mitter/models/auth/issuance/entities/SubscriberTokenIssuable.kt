package io.mitter.models.auth.issuance.entities

import com.fasterxml.jackson.annotation.JsonTypeName
import io.mitter.models.auth.issuance.TokenIssuable
import io.mitter.data.domain.annotations.IdUtils
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.subscriber.Subscriber
import io.mitter.models.auth.issuance.StandardTokenIssuables

/**
 * A Token Issuable for a Subscriber.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeName(StandardTokenIssuables.Subscriber)
class SubscriberTokenIssuable(subscriberId: Identifiable<Subscriber>):
        TokenIssuable<Subscriber>(
                subscriberId.domainId(),
                Subscriber::class.java,
                StandardTokenIssuables.Subscriber) {
    val subscriberId: Identifiable<Subscriber>
        get() = IdUtils.of(this.entityId, Subscriber::class.java)
}
