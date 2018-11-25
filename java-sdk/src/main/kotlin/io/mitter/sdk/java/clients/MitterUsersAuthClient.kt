package io.mitter.sdk.java.clients

import feign.Headers
import feign.Param
import feign.RequestLine
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.user.User
import io.mitter.models.auth.issuance.AttachedIssuedToken
import io.mitter.models.requests.IssueUserTokenResponse
import io.mitter.named.resources.http.endpoints.UserEndpointsV1
import io.mitter.sdk.java.support.IdentifiableExpander
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterUsersAuthClient {

    @RequestLine("POST " + UserEndpointsV1.Tokens.ForUserId)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun issueUserToken(
        @Param(UserEndpointsV1.UserIdParam, expander = IdentifiableExpander::class)
        userId: Identifiable<User>
    ): IssueUserTokenResponse

    @RequestLine("GET " + UserEndpointsV1.Tokens.ForUserId)
    fun getTokensForUser(
        @Param(UserEndpointsV1.UserIdParam, expander = IdentifiableExpander::class)
        userId: Identifiable<User>
    ): List<AttachedIssuedToken<User>>

    @RequestLine("DELETE " + UserEndpointsV1.Tokens.Specified.ForUserId)
    fun revokeTokensForUser(
        @Param(UserEndpointsV1.Tokens.TokenIdsParam) tokenIdsCsv: String,
        @Param(UserEndpointsV1.UserIdParam, expander = IdentifiableExpander::class)
        userId: Identifiable<User>
    )

    @RequestLine("GET " + UserEndpointsV1.Tokens.ForMe)
    fun getTokensForMe(): List<AttachedIssuedToken<User>>

    @RequestLine("GET " + UserEndpointsV1.Tokens.Ancillary.Logout)
    fun logoutMe()

    @RequestLine("DELETE " + UserEndpointsV1.Tokens.Specified.ForMe)
    fun revokeTokensForMe(@Param(UserEndpointsV1.Tokens.TokenIdsParam) tokenIdsCsv: String)
}
