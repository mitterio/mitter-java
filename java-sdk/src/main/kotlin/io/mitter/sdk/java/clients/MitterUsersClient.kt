package io.mitter.sdk.java.clients

import com.fasterxml.jackson.databind.JsonNode
import feign.Headers
import feign.Param
import feign.QueryMap
import feign.RequestLine
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.entity.Metadata
import io.mitter.data.domain.user.User
import io.mitter.models.hosted.GetUsersScreenNameResponse
import io.mitter.models.hosted.PutUserScreenNameRequest
import io.mitter.named.resources.http.endpoints.UserEndpointsV1
import io.mitter.sdk.java.support.IdentifiableExpander
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Rohan Prabhu (rohan@rohanprabhu.com)
 */
@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterUsersClient {

    @RequestLine("POST " + UserEndpointsV1.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun newUser(user: User): Identifiable<User>

    @RequestLine("GET " + UserEndpointsV1.Specified.Me)
    fun getMe(): User

    @RequestLine("PUT " + UserEndpointsV1.ScreenName.ByUserId)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun setScreenName(
        @Param(UserEndpointsV1.UserIdParam, expander = IdentifiableExpander::class) userId: Identifiable<User>,
        putUserScreenName: PutUserScreenNameRequest
    )

    @RequestLine("DELETE " + UserEndpointsV1.Specified.ByUserId)
    fun deleteUser(
        @Param(UserEndpointsV1.UserIdParam, expander = IdentifiableExpander::class) userId: Identifiable<User>
    )

    @RequestLine("GET " + UserEndpointsV1.Specified.ByUserId)
    fun getUser(
        @Param(UserEndpointsV1.UserIdParam, expander = IdentifiableExpander::class) userId: Identifiable<User>
    ): User

    @RequestLine("GET " + UserEndpointsV1.ScreenName.ByUserIds)
    fun getScreenNames(
        @Param(UserEndpointsV1.UserIdsParam) userIdsCsv: String
    ): GetUsersScreenNameResponse

    @RequestLine("GET " + UserEndpointsV1.Base)
    fun getSandboxedUsers(): List<User>

    @RequestLine("GET " + UserEndpointsV1.Specified.ByLocators)
    fun getUsers(@Param(UserEndpointsV1.Specified.locatorsParam) serializedLocators: String): List<User>

    /* User Metadata Operations */

    @RequestLine("GET " + UserEndpointsV1.Base)
    fun getUsersWithMetadata( @QueryMap queryMap: Map<String, Any> ): List<User>

    @RequestLine("POST " + UserEndpointsV1.MetaData.ForUserId)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun addUserMetadata(
        @Param(UserEndpointsV1.UserIdParam, expander = IdentifiableExpander::class)
        userId: Identifiable<User>,
        metadata: JsonNode
    )

    @RequestLine("GET " + UserEndpointsV1.MetaData.ForUserId)
    fun getUserMetadata(
        @Param(UserEndpointsV1.UserIdParam, expander = IdentifiableExpander::class)
        userId: Identifiable<User>
    ): List<Metadata>
}
