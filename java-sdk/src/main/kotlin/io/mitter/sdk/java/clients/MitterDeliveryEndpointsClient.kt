package io.mitter.sdk.java.clients

import feign.Headers
import feign.Param
import feign.RequestLine
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.user.User
import io.mitter.models.delman.DeliveryEndpoint
import io.mitter.named.resources.http.endpoints.UserEndpointsV1
import io.mitter.sdk.java.support.IdentifiableExpander
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterDeliveryEndpointsClient {

    @RequestLine("POST " + UserEndpointsV1.DeliveryEndpoints.ForMe)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun addDeliveryEndpointsForMe(
        deliveryEndpoint: DeliveryEndpoint
    ) : DeliveryEndpoint

    @RequestLine("GET " + UserEndpointsV1.DeliveryEndpoints.Specified.ForMeByEndpoint)
    fun getDeliveryEndpointsForMe(
        @Param(UserEndpointsV1.DeliveryEndpoints.SerializedEndpointParam)
        serializedDeliveryEndpoint: String
    ) : DeliveryEndpoint

    @RequestLine("DELETE " + UserEndpointsV1.DeliveryEndpoints.Specified.ForMeByEndpoint)
    fun deleteDeliveryEndpointsForMe(
        @Param(UserEndpointsV1.DeliveryEndpoints.SerializedEndpointParam)
        serializedDeliveryEndpoint: String
    )

    @RequestLine("POST " + UserEndpointsV1.DeliveryEndpoints.ForUserId)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun addDeliveryEndpointsForUser(
        @Param(UserEndpointsV1.UserIdParam, expander = IdentifiableExpander::class)
        userId: Identifiable<User>,
        deliveryEndpoint: DeliveryEndpoint
    )

    @RequestLine("DELETE " + UserEndpointsV1.DeliveryEndpoints.Specified.ForUserIdByEndpoint)
    fun deleteDeliveryEndpointsForUser(
        @Param(UserEndpointsV1.UserIdParam, expander = IdentifiableExpander::class)
        userId: Identifiable<User>,
        @Param(UserEndpointsV1.DeliveryEndpoints.SerializedEndpointParam) serializedEndpoint: String
    )
}