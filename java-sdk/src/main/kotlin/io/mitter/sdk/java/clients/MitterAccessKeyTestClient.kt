package io.mitter.sdk.java.clients

import com.fasterxml.jackson.databind.node.ObjectNode
import feign.Headers
import feign.RequestLine
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Rohan Prabhu (rohan@rohanprabhu.com)
 */
@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterAccessKeyTestClient {
    @RequestLine("GET /v1/test/application/by/accesskey")
    fun testGetWithApplicationAccessKey() : ObjectNode

    @RequestLine("POST /v1/test/application/by/accesskey")
    @Headers("Content-Type: application/json")
    fun testPostWithApplicationAccessKey(requestObject: ObjectNode) : ObjectNode

    @RequestLine("GET /v1/test/subscriber/by/accesskey")
    fun testGetWithSubscriberAccessKey() : ObjectNode

    @RequestLine("POST /v1/test/subscriber/by/accesskey")
    @Headers("Content-Type: application/json")
    fun testPostWithSubscriberAccessKey(requestObject: ObjectNode) : ObjectNode
}
