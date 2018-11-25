package io.mitter.sdk.java.support.interceptors

import feign.RequestInterceptor
import feign.RequestTemplate
import io.mitter.sdk.java.objects.credentials.MitterAuthorizeSubscriberCredentials
import io.mitter.sdk.java.objects.credentials.MitterSubscriberApiAccessCredentials
import io.mitter.sdk.java.objects.credentials.MitterSudoUserCredentials
import io.mitter.sdk.java.support.interceptors.utils.AccessKeySigner

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on Mar / 2018
 *
 */
class MitterSubscriberAuthorizationSetter(
    private val mitterAuthorizeSubscriberCredentials: MitterAuthorizeSubscriberCredentials
) : RequestInterceptor {
    companion object {
        const val SubscriberAutorizeHeaderKey = "X-Issued-Mitter-Subscriber-Authorization"
    }

    override fun apply(requestTemplate: RequestTemplate) {
        requestTemplate.header(SubscriberAutorizeHeaderKey, mitterAuthorizeSubscriberCredentials.signedToken)
    }
}