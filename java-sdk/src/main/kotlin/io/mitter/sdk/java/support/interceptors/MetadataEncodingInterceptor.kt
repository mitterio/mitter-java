package io.mitter.sdk.java.support.interceptors

import feign.RequestInterceptor
import feign.RequestTemplate
import java.net.URLEncoder

class MetadataEncodingInterceptor : RequestInterceptor {
    val customEncodeKeys = listOf("metadata")

    override fun apply(template: RequestTemplate) {
        template.queries().map {
            if (customEncodeKeys.contains(it.key)) {
                template.query(true, it.key, it.value.map { URLEncoder.encode(it) })
            }
        }
    }
}