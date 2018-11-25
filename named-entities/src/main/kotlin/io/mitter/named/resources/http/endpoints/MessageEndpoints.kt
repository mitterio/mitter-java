package io.mitter.named.resources.http.endpoints

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object MessageEndpointsV1 {
    const val Base = EndpointsV1.VersionPrefix + "/messages"
    const val MessageIdParam = "messageId"

    object Specified {
        const val ById = "$Base/{messageId}"
    }

    object Metadata {
        const val ForMessageId = Specified.ById + "/metadata"
    }
}

object ChannelMessageEndpointsV1 {
    const val Base            = ChannelEndpointsV1.Specified.ForChannelId + "/messages"
    const val MessageIdsParam = "messageIds"

    object Specified {
        const val ForMessageIds = Base + "/{messageIds}"
    }

    object TimelineEvents {
        const val ForMessageIds = ChannelMessageEndpointsV1.Specified.ForMessageIds + "/timeline"

        object EventTypeFilter {
            const val ByEventType = ForMessageIds + "?eventTypeFilter={eventTypeFilter}"
            const val EventTypeFilterParam = "eventTypeFilter"
        }
    }
}
