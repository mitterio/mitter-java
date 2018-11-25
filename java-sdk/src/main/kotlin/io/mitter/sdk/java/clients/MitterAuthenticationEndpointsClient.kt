package io.mitter.sdk.java.clients

import feign.Headers
import feign.RequestLine
import io.mitter.models.hosted.subscriber.auth.SubscriberSignupRequestInternal
import io.mitter.models.hosted.subscriber.auth.SubscriberSignupResponseInternal
import io.mitter.models.requests.SubscriberAuthRequest
import io.mitter.models.requests.SubscriberAuthResponse
import io.mitter.named.resources.http.endpoints.SubscriberEndpointsV1
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on 02/2018
 *
 */

@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterAuthenticationEndpointsClient {
    @RequestLine("POST " + SubscriberEndpointsV1.Subscribers.Token.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun authenticateSubscriber(
        subscriberAuthRequest: SubscriberAuthRequest
    ) : SubscriberAuthResponse

    @RequestLine("POST " + SubscriberEndpointsV1.Subscribers.Ancillary.Signup)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun signupSubscriber(
        subscriberSignupRequestInternal: SubscriberSignupRequestInternal
    ) : SubscriberSignupResponseInternal

    @RequestLine("GET "+ SubscriberEndpointsV1.Subscribers.Auth.Logout)
    fun logoutSubscriber()

    @RequestLine("DELETE "+ SubscriberEndpointsV1.Subscribers.Token.Base)
    fun revokeAllTokens() : List<String>
}
