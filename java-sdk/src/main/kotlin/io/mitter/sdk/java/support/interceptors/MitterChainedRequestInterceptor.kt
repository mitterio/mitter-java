package io.mitter.sdk.java.support.interceptors

import feign.RequestInterceptor
import feign.RequestTemplate
import mu.KLoggable

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class MitterChainedRequestInterceptor(
    private val interceptors: List<RequestInterceptor>
) : RequestInterceptor {
    companion object : Any(), KLoggable {
        override val logger = logger()
    }

    override fun apply(request: RequestTemplate) {
        if (interceptors.filter { it is SigningInterceptor }.size > 1) {
            throw IllegalArgumentException("There can be at most one signing interceptor in a request interceptor chain")
        }

        interceptors.sortedBy { if (it is SigningInterceptor) { 1 } else { 0 }  }
            .forEach { it.apply(request) }
    }
}