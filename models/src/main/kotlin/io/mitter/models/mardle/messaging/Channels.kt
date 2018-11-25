package io.mitter.models.mardle.messaging

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.annotations.*
import io.mitter.data.domain.entity.EntityMetadata
import io.mitter.data.domain.entity.MetadataAttachable
import io.mitter.data.domain.entity.emptyEntityMetadata
import io.mitter.data.domain.entity.EntityProfile
import io.mitter.data.domain.user.User
import io.mitter.models.acolyte.AclEntity
import io.mitter.models.acolyte.AppliedAclList
import io.mitter.models.acolyte.emptyAclList
import io.mitter.models.central.entityprofile.ProfileAttachable
import io.mitter.models.commons.AuditInfo
import io.mitter.models.commons.Auditable
import io.swagger.annotations.ApiModelProperty

/**
 * Created by rohan on 6/2/17.
 */

object StandardRuleSetNames {
    const val DirectMessage = "io.mitter.ruleset.chats.DirectMessage"
    const val GroupChat = "io.mitter.ruleset.chats.GroupChat"
    const val SystemChannel = "io.mitter.ruleset.chats.SystemChannel"
    const val SingleParticipantChannel = "io.mitter.ruleset.chats.SingleParticipantChannel"
}

enum class StandardRuleSet(
    val ruleSetIdentifier: String,
    val __restricted_prefix: String = "io.mitter"
) {
    DirectMessage(ruleSetIdentifier = StandardRuleSetNames.DirectMessage),
    GroupChat(ruleSetIdentifier = StandardRuleSetNames.GroupChat),
    SystemChannel(ruleSetIdentifier = StandardRuleSetNames.SystemChannel),
    SingleParticipantChannel(ruleSetIdentifier = StandardRuleSetNames.SingleParticipantChannel)
}

enum class ParticipationStatus {
    Active,
    ReadOnly,
    Disabled
}

data class Channel(
    /* The string domainId of the chat */
    val channelId: String,

    /* The internal domain id */
    val internalId: String? = null,

    /* The default rule set that applies to this chat */
    val defaultRuleSet: String,

    /* A list of participations in this chat */
    val participation: List<ChannelParticipation> = emptyList(),

    val systemChannel : Boolean,

    /* Associated metadata with this chat */
    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    override var entityMetadata: EntityMetadata = emptyEntityMetadata(),

    /* Associated entity-profile with this chat */
    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    override var entityProfile: EntityProfile? = EntityProfile(IdUtils.of(channelId, Channel::class.java), emptyList()),


    val timelineEvents: List<TimelineEvent> = emptyList(),

    @get:JsonProperty("appliedAcls")
    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(hidden = true)
    override val appliedAcls: AppliedAclList = emptyAclList(),
    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    override var auditInfo: AuditInfo? = null
) : MitterDomainEntity<Channel>, ProfileAttachable, AclEntity<Channel>, Auditable {
    override fun domainId() = channelId
    override fun internalId(): String? = internalId
    override fun type() = Channel::class.java

    companion object {
        @JvmStatic
        @JsonCreator
        fun constructionSupport(
            @JsonProperty("channelId") channelId: String?,
            @JsonProperty("defaultRuleSet") defaultRuleSet: String,
            @JsonProperty("participation") participation: List<ChannelParticipation>?,
            @JsonProperty("systemChannel") systemChannel: Boolean?,
            @JsonProperty("appliedAcls") appliedAcls: AppliedAclList?,
            @JsonProperty("entityMetadata") entityMetadata: EntityMetadata?,
            @JsonProperty("entityProfile") entityProfile: EntityProfile?,
            @JsonProperty("timelineEvents") timelineEvents: List<TimelineEvent>?
        ) = Channel(
            channelId = channelId ?: throw IdentifierNotSetException("channelId", Channel::class),
            defaultRuleSet = defaultRuleSet,
            participation = participation ?: emptyList(),
            systemChannel = systemChannel ?: false,
            entityMetadata = entityMetadata ?: emptyEntityMetadata(),
            appliedAcls = appliedAcls ?: emptyAclList(),
            timelineEvents = timelineEvents ?: emptyList(),
            entityProfile = entityProfile ?: EntityProfile(IdUtils.of(channelId, Channel::class.java), emptyList())
        )
    }
}

data class ChannelParticipation(
    @JsonProperty("channelParticipationId")
    val channelParticipationId: String? = null,

    @JsonProperty("participantId")
    val participantId: Identifiable<User>,

    @JsonProperty("participationStatus")
    val participationStatus: ParticipationStatus = ParticipationStatus.Active,

    @JsonProperty("channelId")
    val channelId: Identifiable<Channel>? = null,

    @JsonProperty("participant")
    val participant: User? = null,

    @JsonProperty("auditInfo")
    override var auditInfo: AuditInfo? = null
) : IdentifiableEntity<ChannelParticipation>, Auditable {
    @JsonIgnore
    override fun domainId() = channelParticipationId ?: throw IllegalStateException("This channelParticipation does not contain an id.")
    override fun internalId() = channelParticipationId
    override fun type() = ChannelParticipation::class.java

    companion object {
        @JvmStatic
        @JsonCreator
        fun constructionSupport(
            @JsonProperty("channelParticipationId") channelParticipationId: String?,
            @JsonProperty("participantId") participantId: Identifiable<User>,
            @JsonProperty("participationStatus") participationStatus: ParticipationStatus?,
            @JsonProperty("channelId") channelId: Identifiable<Channel>?,
            @JsonProperty("participant") participant: User?,
            @JsonProperty("auditInfo") auditInfo: AuditInfo?
        ) = ChannelParticipation(
                channelParticipationId = channelParticipationId,
            participantId = participantId,
            participant = participant,
            participationStatus = participationStatus ?: ParticipationStatus.Active,
            channelId = channelId,
            auditInfo = auditInfo
        )
    }
}

data class ParticipatedChannel(
    @JsonProperty("participationId")
    val participationId: String,

    @JsonProperty("internalId")
    val internalId: String? = null,

    @JsonProperty("participationStatus")
    val participationStatus: ParticipationStatus,

    @JsonProperty("channel")
    val channel: Channel
): IdentifiableEntity<ParticipatedChannel>{
    override fun domainId() = participationId
    override fun internalId(): String? = internalId
    override fun type() = ParticipatedChannel::class.java


}
