package io.mitter.data.domain.entity

import com.fasterxml.jackson.annotation.JsonProperty
import io.mitter.data.domain.annotations.Identifiable

/**
 * @author Vedavyas Bhat (vedavyas@nomadly.in)
 */

object StandardEntityProfileAttributes {
    object UserDefaultTypes {
        const val FirstName  = "mitter.upa.FirstName"
        const val LastName   = "mitter.upa.LastName"
        const val AvatarURL  = "mitter.upa.AvatarURL"
        const val Mobile     = "mitter.upa.Mobile"
        const val DOB        = "mitter.upa.DOB"
        const val Bio        = "mitter.upa.Bio"
        const val Gender     = "mitter.upa.Gender"
        const val Email      = "mitter.upa.Email"

        object Address {
            const val Street   = "mitter.upa.address.Street"
            const val City     = "mitter.upa.address.City"
            const val State    = "mitter.upa.address.State"
            const val Zip      = "mitter.upa.address.Zip"
            const val Country  = "mitter.upa.address.Country"
        }
    }

    object ChannelDefaultTypes {
        const val DisplayName    = "mitter.cpa.DisplayName"
        const val Description    = "mitter.cpa.Description"
        const val ChannelIconURL = "mitter.cpa.ChannelIconURL"
        const val Purpose        = "mitter.cpa.Purpose"
    }

    object ContentTypes {
        const val TextPlain = "text/plain"
        const val Json = "application/json"
    }

    object Encoding {
        const val Utf8 = "UTF-8"
    }
}

data class AttributeDef(
    @JsonProperty("key")
    val key: String, //Unique within an application
    @JsonProperty("allowedContentTypes")
    val allowedContentTypes: List<String> = emptyList(),
    @JsonProperty("allowedContentEncodings")
    val allowedContentEncodings: List<String> = emptyList(),
    @JsonProperty("canBeEmpty")
    val canBeEmpty: Boolean = true,
    @JsonProperty("entityType")
    val entityType: String // can be users or channels as entityType
)

data class Attribute(
    @JsonProperty("key")
    var key: String,
    @JsonProperty("contentType")
    var contentType: String,
    @JsonProperty("contentEncoding")
    var contentEncoding: String,
    @JsonProperty("value")
    var value: String
)

data class EntityProfile(
    @JsonProperty("entityId")
    val entityId: Identifiable<*>,
    @JsonProperty("attributes")
    val attributes: List<Attribute> = emptyList()
)
