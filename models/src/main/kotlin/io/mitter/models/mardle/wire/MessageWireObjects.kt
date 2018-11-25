package io.mitter.models.mardle.wire

import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.models.acolyte.AclAccessorSelector
import io.mitter.models.mardle.messaging.Channel
import io.mitter.models.mardle.messaging.Message

/**
 *
 * @author Rohan Prabhu (rohan@rohanprabhu.com)
 */
enum class SortOrder {
    Ascending,
    Descending,
    Implied
}

data class AddableMessage(
    val message : Message
)

data class ChannelMessageSet(
    val channelId: Identifiable<Channel>,
    val messages: List<Message>
)

data class ChannelMessage(
    @JsonProperty("channelId")
    val channelId: Identifiable<Channel>,
    @JsonProperty("message")
    val message: Message
)

data class Paginated<T : Identifiable<T>>(
    @JsonProperty("data")
    val data : List<T>,
    @JsonProperty("estimatedTotalCount")
    val estimatedTotalCount: Int? = null
)

open class EntityQuery<T : Identifiable<T>>(
    val beforeId          : Identifiable<T>?,
    val afterId           : Identifiable<T>?,
    val limit             : Int,
    val entityCountOffset : Int,
    val sortOrder         : SortOrder,
    val withReadSelectors : List<AclAccessorSelector>
)

class MessageQuery(
    val channelId: Identifiable<Channel>,
    val payloadTypes: List<String> = emptyList(),

    // Both `since` and `after` message ids are exclusive
    val beforeMessageId: Identifiable<Message>? = null,
    val afterMessageId: Identifiable<Message>? = null,

    entityCountOffset: Int = 0,
    limit: Int = 10,
    withReadSelectors: List<AclAccessorSelector> = emptyList()
) : EntityQuery<Message>(
    beforeMessageId, afterMessageId,
    limit, entityCountOffset, SortOrder.Implied,
    withReadSelectors
)
