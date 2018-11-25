package io.mitter.sdk.java.support.interceptors

import feign.RequestInterceptor
import feign.RequestTemplate
import io.mitter.sdk.java.objects.credentials.MitterSudoUserCredentials

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class MitterApplicationSudoUserSetter(
    private val mitterSudoUserCredentials: MitterSudoUserCredentials
) : RequestInterceptor {
    companion object {
        const val SudoUserHeaderKey = "X-Mitter-Sudo-User-Id"
    }

    override fun apply(requestTemplate: RequestTemplate) {
        requestTemplate.header(SudoUserHeaderKey, mitterSudoUserCredentials.userId)
    }
}