package io.mitter.named.resources.http.endpoints

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object ChannelEndpointsV1 {
    const val Base = EndpointsV1.VersionPrefix + "/channels"
    const val ChannelIdParam  = "channelId"
    const val ChannelIdsParam = "channelIds"

    object Specified {
        const val ForChannelId  = Base + "/{channelId}"
        const val ForChannelIds = Base + "/{channelIds}"
    }

    object Participation {
        const val ForParticipants = ChannelEndpointsV1.Specified.ForChannelId + "/participants"
        const val ForParticipations = ChannelEndpointsV1.Specified.ForChannelId + "/participations"
        const val ParticipantIdParam = "participantId"
        const val ParticipationIdParam = "participationId"

        object Specified {
            const val ForParticipantId = Participation.ForParticipants + "/{participantId}"
            const val ForParticipationId = Participation.ForParticipations + "/{participationId}"
        }
    }

    object Metadata {
        const val ForChannelId = ChannelEndpointsV1.Specified.ForChannelId + "/metadata"
    }

    object Streams {
        const val ForChannelId = ChannelEndpointsV1.Specified.ForChannelId + "/streams"
        const val StreamIdParam = "streamId"

        object Specified {
            const val ByStreamId = Streams.ForChannelId + "/{streamId}"
        }
    }

    object TimelineEvents {
        const val ForChannelId  = ChannelEndpointsV1.Specified.ForChannelId  + "/timeline"
        const val ForChannelIds = ChannelEndpointsV1.Specified.ForChannelIds + "/timeline"
    }
}
