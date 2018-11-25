package io.mitter.sdk.java.clients

import com.fasterxml.jackson.databind.JsonNode
import feign.Headers
import feign.Param
import feign.QueryMap
import feign.RequestLine
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.models.mardle.messaging.Channel
import io.mitter.models.mardle.messaging.Message
import io.mitter.models.mardle.messaging.TimelineEvent
import io.mitter.models.mardle.wire.MessageTimelineEvent
import io.mitter.named.resources.http.endpoints.ChannelEndpointsV1
import io.mitter.named.resources.http.endpoints.ChannelMessageEndpointsV1
import io.mitter.named.resources.http.endpoints.MessageEndpointsV1
import io.mitter.sdk.java.support.IdentifiableExpander
import io.mitter.sdk.java.utils.StandardHeaderLines
import java.io.InputStream

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class BinaryPayload(
    val name: String,
    val data: InputStream,
    val mimeType: String
)

@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterMessagesClient {
    @RequestLine("POST " + ChannelMessageEndpointsV1.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun sendMessageToChannel(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>, message: Message
    ) : Message

    @RequestLine("POST " + ChannelMessageEndpointsV1.Base)
    @Headers(StandardHeaderLines.ContentTypeMultipartFormData + ";charset=utf-8")
    fun sendMessageToChannel(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        @Param("io.mitter.wire.requestbody;application/json")
        message: Message,
        @Param("binary-payloads")
        upload: List<BinaryPayload>
    ) : Message

    @RequestLine("GET " + ChannelMessageEndpointsV1.Base)
    fun getMessagesFromChannel(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        @QueryMap queryMap: Map<String, Any>
    ) : List<Message>


    @RequestLine("DELETE " + ChannelMessageEndpointsV1.Specified.ForMessageIds)
    fun removeMessagesFromChannel(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        @Param(ChannelMessageEndpointsV1.MessageIdsParam)
        messageIds: String
    ) : List<Message>

    @RequestLine("GET " + MessageEndpointsV1.Specified.ById)
    fun getMessageByMessageId(
        @Param(MessageEndpointsV1.MessageIdParam, expander = IdentifiableExpander::class)
        messageId: Identifiable<Message>
    ) : Message

    /* ~~ Channel's Messages Timeline event operations ~~ */

    @RequestLine("POST " + ChannelMessageEndpointsV1.TimelineEvents.ForMessageIds)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun addTimelineEventForMessage(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        @Param(ChannelMessageEndpointsV1.MessageIdsParam)
        messageIdsCsv: String,
        timelineEvent: TimelineEvent
    ) : Identifiable<TimelineEvent>

    @RequestLine("GET " + ChannelMessageEndpointsV1.TimelineEvents.EventTypeFilter.ByEventType)
    fun getTimelineEventForMessage(
        @Param(ChannelMessageEndpointsV1.TimelineEvents.EventTypeFilter.EventTypeFilterParam) eventTypeFilterCsv:String,
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        @Param(ChannelMessageEndpointsV1.MessageIdsParam)
        messageIdsCsv: String
    ) : List<MessageTimelineEvent>

    /* Channel Metadata Operations  */

    @RequestLine("GET " + ChannelMessageEndpointsV1.Base)
    fun getMessagesWithMetadata(
            @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
            channelId: Identifiable<Channel>,
            @QueryMap queryMap: Map<String, Any> ): List<Message>

    @RequestLine("POST " + MessageEndpointsV1.Metadata.ForMessageId)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun addMessageMetadata(
            @Param(MessageEndpointsV1.MessageIdParam, expander = IdentifiableExpander::class)
            messageId: Identifiable<Message>,
            metadata: JsonNode
    )
}
