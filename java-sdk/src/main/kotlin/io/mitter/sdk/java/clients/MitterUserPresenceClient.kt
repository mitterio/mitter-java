package io.mitter.sdk.java.clients

import feign.Headers
import feign.Param
import feign.RequestLine
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.user.Presence
import io.mitter.data.domain.user.User
import io.mitter.named.resources.http.endpoints.UserEndpointsV1
import io.mitter.named.resources.http.endpoints.UserPresenceEndpointsV1
import io.mitter.sdk.java.support.IdentifiableExpander
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on 02/2018
 *
 */

@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterUserPresenceClient {
    @RequestLine("POST "+UserPresenceEndpointsV1.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun setUserPresence(
        @Param(UserEndpointsV1.UserIdParam , expander = IdentifiableExpander::class) userId: Identifiable<User>,
        presence: Presence
    )

    @RequestLine("GET "+UserPresenceEndpointsV1.Base)
    fun getUserPresence(
        @Param(UserEndpointsV1.UserIdParam , expander = IdentifiableExpander::class) userId: Identifiable<User>
    ) : Presence?
}
