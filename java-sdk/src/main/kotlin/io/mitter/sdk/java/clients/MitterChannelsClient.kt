package io.mitter.sdk.java.clients

import com.fasterxml.jackson.databind.JsonNode
import feign.Headers
import feign.Param
import feign.QueryMap
import feign.RequestLine
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.user.User
import io.mitter.models.mardle.messaging.*
import io.mitter.models.mardle.wire.ChannelTimelineEvent
import io.mitter.models.mardle.wire.Paginated
import io.mitter.models.mardle.wire.PatchChannelParticipation
import io.mitter.named.resources.http.endpoints.ChannelEndpointsV1
import io.mitter.named.resources.http.endpoints.ChannelMessageEndpointsV1
import io.mitter.named.resources.http.endpoints.UserEndpointsV1
import io.mitter.sdk.java.support.IdentifiableExpander
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Rohan Prabhu (rohan@rohanprabhu.com)
 */
@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterChannelsClient {

    @RequestLine("POST " + ChannelEndpointsV1.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun newChannel(channel: Channel): Identifiable<Channel>

    @RequestLine("DELETE " + ChannelEndpointsV1.Specified.ForChannelId)
    fun deleteChannel(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>
    )

    @RequestLine("GET " + ChannelEndpointsV1.Specified.ForChannelId)
    fun getChannel(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>
    ): Channel

    @RequestLine("GET " + ChannelEndpointsV1.Base)
    fun getChannels( @QueryMap queryMap: Map<String, Any> ): Paginated<Channel>

    @RequestLine("DELETE " + ChannelMessageEndpointsV1.Base)
    fun deleteMessagesFromChannel(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>
    )

    /* ~~ Channel Timeline event operations ~~ */

    @RequestLine("POST " + ChannelEndpointsV1.TimelineEvents.ForChannelId)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun addChannelTimelineEvents(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        timelineEvent: TimelineEvent
    )

    @RequestLine("GET " + ChannelEndpointsV1.TimelineEvents.ForChannelIds)
    fun getChannelTimelineEvents(
        @Param(ChannelEndpointsV1.ChannelIdsParam, expander = IdentifiableExpander::class)
        channelIds: Identifiable<Channel>
    ): List<ChannelTimelineEvent>

    /* ~~ Channel streams operations ~~ */

    @RequestLine("POST " + ChannelEndpointsV1.Streams.Specified.ByStreamId)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun sendStreamData(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        @Param(ChannelEndpointsV1.Streams.StreamIdParam, expander = IdentifiableExpander::class)
        streamId: Identifiable<Stream>,
        streamData: ContextFreeMessage
    )

    @RequestLine("GET " + ChannelEndpointsV1.Streams.Specified.ByStreamId)
    fun getChannelStreamByStreamId(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        @Param(ChannelEndpointsV1.Streams.StreamIdParam, expander = IdentifiableExpander::class)
        streamId: Identifiable<Stream>
    ) : Stream

    @RequestLine("POST " + ChannelEndpointsV1.Streams.ForChannelId)
    fun createChannelStream(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        channel: Channel
    ) : Stream

    /* ~~ Participant operations -- */
    @RequestLine("POST " + ChannelEndpointsV1.Participation.ForParticipants)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun addParticipantToChannel(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        channelParticipation: ChannelParticipation
    )

    @RequestLine("GET " + ChannelEndpointsV1.Participation.ForParticipants)
    fun getChannelParticipations(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>
    ): List<ChannelParticipation>

    @RequestLine("DELETE " + ChannelEndpointsV1.Participation.Specified.ForParticipantId)
    fun deleteParticipantFromChannel(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        @Param(ChannelEndpointsV1.Participation.ParticipantIdParam, expander = IdentifiableExpander::class)
        participantId: Identifiable<User>
    )

    @RequestLine("GET " + UserEndpointsV1.Channels.ForUserId)
    fun getChannelsWithParticipant(
        @Param(UserEndpointsV1.UserIdParam, expander = IdentifiableExpander::class)
        userId: Identifiable<User>
    ): List<ParticipatedChannel>

    @RequestLine("PATCH " + ChannelEndpointsV1.Participation.Specified.ForParticipantId)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun updateParticipationStatus(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        @Param(ChannelEndpointsV1.Participation.ParticipantIdParam, expander = IdentifiableExpander::class)
        participantId: Identifiable<User>,
        patchChannelParticipation: PatchChannelParticipation
    )

    /* Channel Metadata Operations  */

    @RequestLine("GET " + ChannelEndpointsV1.Base)
    fun getChannelsWithMetadata( @QueryMap queryMap: Map<String, Any> ): List<Channel>

    @RequestLine("POST " + ChannelEndpointsV1.Metadata.ForChannelId)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun addChannelMetadata(
        @Param(ChannelEndpointsV1.ChannelIdParam, expander = IdentifiableExpander::class)
        channelId: Identifiable<Channel>,
        metadata: JsonNode
    )
}
