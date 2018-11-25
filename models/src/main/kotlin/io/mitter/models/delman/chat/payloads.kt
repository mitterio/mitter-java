package io.mitter.models.delman.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.mitter.data.domain.IdGenerator
import java.util.*

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes(
    /* Mardle payloads */
    JsonSubTypes.Type(value = NewChannelPayload::class),
    JsonSubTypes.Type(value = NewMessagePayload::class),
    JsonSubTypes.Type(value = NewMessageTimelineEventPayload::class),
    JsonSubTypes.Type(value = NewChannelTimelineEventPayload::class),
    JsonSubTypes.Type(value = ParticipationChangedEventPayload::class),
    JsonSubTypes.Type(value = ChannelStreamData::class),

    /* Pipeline-control payloads */
    JsonSubTypes.Type(value = PipelineControlPayload::class)
)
abstract class MessagingPipelinePayload(
    @JsonProperty("globalPipelinePayloadId")
    val globalPipelinePayloadId: String = IdGenerator.generateDomainId(MessagingPipelinePayload::class.java)
)

data class SerializedMessagePipelinePayload(
    @JsonProperty("data")
    val data: String
)
