package io.mitter.sdk.java.clients

import feign.Headers
import feign.Param
import feign.RequestLine
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.models.acolyte.AclEntity
import io.mitter.models.acolyte.AppliedAclPatchList
import io.mitter.named.resources.http.endpoints.DomainEntityEndpoints
import io.mitter.sdk.java.support.IdentifiableExpander
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
enum class DomainEntityType(
    private val entityTypeName: String
) {
    Channel("channels"),
    Message("messages"),
    Application("applications");

    override fun toString() = entityTypeName
}

@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterAclOperationsClient {
    @RequestLine("PATCH " + DomainEntityEndpoints.Acolyte.AclOperations.ForEntityId)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun modifyAclsForEntity(
        @Param(DomainEntityEndpoints.EntityTypeParam) entityType: DomainEntityType,

        @Param(DomainEntityEndpoints.EntityIdParam, expander = IdentifiableExpander::class)
        entityId: Identifiable<AclEntity<*>>,
        appliedAclPatchList: AppliedAclPatchList
    )
}