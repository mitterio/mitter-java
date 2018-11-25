package io.mitter.sdk.java.support.interceptors

import feign.RequestInterceptor
import feign.RequestTemplate
import io.mitter.sdk.java.objects.credentials.MitterSudoApplicationCredentials
import mu.KLoggable

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class MitterSudoApplicationSetter(
    private val mitterSudoApplicationCredentials: MitterSudoApplicationCredentials
) : RequestInterceptor {
    companion object : Any(), KLoggable {
        const val SudoApplicationHeaderKey = "X-Mitter-Sudo-Application-Id"
        override val logger = logger()
    }

    override fun apply(request: RequestTemplate) {
        request.header(SudoApplicationHeaderKey, mitterSudoApplicationCredentials.applicationId)
    }
}
