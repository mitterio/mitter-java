package io.mitter.sdk.java.utils

import com.fasterxml.jackson.databind.JsonNode
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.models.mardle.messaging.Channel
import io.mitter.models.mardle.messaging.StandardRuleSetNames
import io.mitter.models.mardle.wire.SortOrder

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on Mar / 2018
 *
 */
class MitterChannelsHelper {
    companion object {
        @JvmStatic
        fun newChannel(channelId: String) =
            Channel(channelId = channelId, defaultRuleSet = StandardRuleSetNames.GroupChat, systemChannel = false)

        @JvmStatic
        fun newChannel(channelId: String, defaultRuleSet: String ) =
            Channel(channelId = channelId, defaultRuleSet = defaultRuleSet, systemChannel = false)

        @JvmStatic
        fun newChannel(channelId: String, defaultRuleSet: String, systemChannel: Boolean) =
            Channel(channelId = channelId, defaultRuleSet = defaultRuleSet, systemChannel = systemChannel)

        @JvmStatic
        fun channelQuery(
            beforeChannelId: Identifiable<Channel>? = null, afterChannelId: Identifiable<Channel>? = null,
            limit: Int? = null, sortOrder: SortOrder? = null, entityCountOffset: Int? = null
        )= mutableMapOf<String, Any>().apply {
            limit ?.let { this.put("limit",  it) }
            sortOrder ?.let { this.put("sortOrder",  it) }
            entityCountOffset ?.let {this.put("entityCountOffset",it) }
            beforeChannelId ?.let { this.put("before", it.domainId()) }
            afterChannelId ?.let { this.put("after",  it.domainId()) }
            }
    }
}