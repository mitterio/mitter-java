package io.mitter.sdk.java.clients

import feign.Headers
import feign.Param
import feign.RequestLine
import io.mitter.auth.data.domain.credential.accesscredential.AccessCredential
import io.mitter.auth.data.domain.credential.accesscredential.AccessCredentialStub
import io.mitter.named.resources.http.endpoints.SubscriberEndpointsV1
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on Mar / 2018
 *
 */

@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterSubscriberAccessApiClient {
    @RequestLine("POST " + SubscriberEndpointsV1.Subscribers.ApiAccess.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun newApiAccessKeyForSubscriber() : AccessCredential

    @RequestLine("DELETE " + SubscriberEndpointsV1.Subscribers.ApiAccess.Specified)
    fun deleteApiAccessKeyForSubscriber(
        @Param(SubscriberEndpointsV1.Subscribers.ApiAccess.AccessKeyIdParam) accessKeyId: String
    )

    @RequestLine("GET " + SubscriberEndpointsV1.Subscribers.ApiAccess.Base)
    fun getApiAccessKeysForSubscriber() : List<AccessCredentialStub>
}