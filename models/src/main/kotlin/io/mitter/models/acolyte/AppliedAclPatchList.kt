package io.mitter.models.acolyte

import com.fasterxml.jackson.annotation.*
import io.mitter.models.commons.PatchType

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "patchType")
@JsonSubTypes(
    /* Mardle payloads */
    JsonSubTypes.Type(value = SetAclsPatchList::class),
    JsonSubTypes.Type(value = DiffAclsPatchList::class)
)
sealed class AppliedAclPatchList(
    val patchType: String
)

@JsonTypeName(PatchType.Set)
data class SetAclsPatchList(
    @get:JsonProperty("setAcls")
    val setAcls: AppliedAclList
) : AppliedAclPatchList(PatchType.Set) {
    companion object {
        @JsonCreator
        @JvmStatic
        fun constructionSupport(
            @JsonProperty("setAcls") setAcls: AppliedAclList
        ) = SetAclsPatchList(setAcls)
    }
}

@JsonTypeName(PatchType.Diff)
data class DiffAclsPatchList(
    @get:JsonProperty("removeAcls")
    val removeAcls: AppliedAclList = emptyAclList(),

    @get:JsonProperty("addAcls")
    val addAcls   : AppliedAclList = emptyAclList()
) : AppliedAclPatchList(PatchType.Diff) {
    companion object {
        @JsonCreator
        @JvmStatic
        fun constructionSupport(
            @JsonProperty("removeAcls") removeAcls: AppliedAclList?,
            @JsonProperty("addAcls")    addAcls   : AppliedAclList?
        ) = DiffAclsPatchList(
            removeAcls = removeAcls ?: emptyAclList(),
            addAcls    = addAcls    ?: emptyAclList()
        )
    }
}
