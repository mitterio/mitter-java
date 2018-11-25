package io.mitter.sdk.java.clients

import feign.Headers
import feign.Param
import feign.RequestLine
import io.mitter.data.domain.entity.Attribute
import io.mitter.data.domain.entity.AttributeDef
import io.mitter.data.domain.entity.EntityProfile
import io.mitter.models.hosted.PatchEntityProfile
import io.mitter.named.resources.http.endpoints.EntityProfileEndpointsV1
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on 02/2018
 *
 */

@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterEntityProfileClient {
    @RequestLine("GET " + EntityProfileEndpointsV1.Profile.Base)
    fun getEntityProfile(
        @Param(EntityProfileEndpointsV1.EntityTypeParam) entityType: String,
        @Param(EntityProfileEndpointsV1.EntityIdParam) entityId: String
    ) : EntityProfile

    @RequestLine("GET " + EntityProfileEndpointsV1.Profile.Specified.ByAttributeKeys)
    fun getAttributesInProfile(
        @Param(EntityProfileEndpointsV1.EntityTypeParam) entityType: String,
        @Param(EntityProfileEndpointsV1.EntityIdParam) entityId: String,
        @Param(EntityProfileEndpointsV1.AttributeKeysParam) keys: String
    ) : List<Attribute>

    @RequestLine("POST " + EntityProfileEndpointsV1.Profile.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun setEntityProfile(
        @Param(EntityProfileEndpointsV1.EntityTypeParam) entityType: String,
        @Param(EntityProfileEndpointsV1.EntityIdParam) entityId: String,
        patchEntityProfile: PatchEntityProfile
    )

    @RequestLine("POST " + EntityProfileEndpointsV1.Profile.Specified.ByAttributeKey)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun addAttributeToProfile(
        @Param(EntityProfileEndpointsV1.EntityTypeParam) entityType: String,
        @Param(EntityProfileEndpointsV1.EntityIdParam) entityId: String,
        @Param(EntityProfileEndpointsV1.AttributeKeyParam) key: String,
        attribute: Attribute
    )

    @RequestLine("POST " + EntityProfileEndpointsV1.AttributeDef.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun addAttributeDef(
        @Param(EntityProfileEndpointsV1.EntityTypeParam) entityType: String,
        attributeDef: AttributeDef
    )

    @RequestLine("GET " + EntityProfileEndpointsV1.AttributeDef.Specified.ByAttributeKey)
    fun getAttributeDef(
        @Param(EntityProfileEndpointsV1.EntityTypeParam) entityType: String,
        @Param(EntityProfileEndpointsV1.AttributeKeyParam) key: String
    ) : AttributeDef

    @RequestLine("GET " + EntityProfileEndpointsV1.AttributeDef.Base)
    fun getAllAttributeDefs(
        @Param(EntityProfileEndpointsV1.EntityTypeParam) entityType: String
    ) : List<AttributeDef>
}
