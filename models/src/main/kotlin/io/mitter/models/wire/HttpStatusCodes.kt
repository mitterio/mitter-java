package io.mitter.models.wire

/**
 * Sourced from: http://grepcode.com/file_/repo1.maven.org/maven2/org.apache.httpcomponents/httpcore/4.1/org/apache/http/HttpStatus.java/?v=source
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 *
 * ORIGINAL LICENSE
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
object HttpStatusCodes {
    const val CONTINUE : Int = 100
    /** <tt>101 Switching Protocols</tt> (HTTP/1.1 - RFC 2616)*/
    const val SWITCHING_PROTOCOLS : Int = 101
    /** <tt>102 Processing</tt> (WebDAV - RFC 2518) */
    const val PROCESSING : Int = 102

    // --- 2xx Success ---

    /** <tt>200 OK</tt> (HTTP/1.0 - RFC 1945) */
    const val OK : Int = 200
    /** <tt>201 Created</tt> (HTTP/1.0 - RFC 1945) */
    const val CREATED : Int = 201
    /** <tt>202 Accepted</tt> (HTTP/1.0 - RFC 1945) */
    const val ACCEPTED : Int = 202
    /** <tt>203 Non Authoritative Information</tt> (HTTP/1.1 - RFC 2616) */
    const val NON_AUTHORITATIVE_INFORMATION : Int = 203
    /** <tt>204 No Content</tt> (HTTP/1.0 - RFC 1945) */
    const val NO_CONTENT : Int = 204
    /** <tt>205 Reset Content</tt> (HTTP/1.1 - RFC 2616) */
    const val RESET_CONTENT : Int = 205
    /** <tt>206 Partial Content</tt> (HTTP/1.1 - RFC 2616) */
    const val PARTIAL_CONTENT : Int = 206
    /**
     * <tt>207 Multi-Status</tt> (WebDAV - RFC 2518) or <tt>207 Partial Update
     * OK</tt> (HTTP/1.1 - draft-ietf-http-v11-spec-rev-01?)
     */
    const val MULTI_STATUS : Int = 207

    // --- 3xx Redirection ---

    /** <tt>300 Mutliple Choices</tt> (HTTP/1.1 - RFC 2616) */
    const val MULTIPLE_CHOICES : Int = 300
    /** <tt>301 Moved Permanently</tt> (HTTP/1.0 - RFC 1945) */
    const val MOVED_PERMANENTLY : Int = 301
    /** <tt>302 Moved Temporarily</tt> (Sometimes <tt>Found</tt>) (HTTP/1.0 - RFC 1945) */
    const val MOVED_TEMPORARILY : Int = 302
    /** <tt>303 See Other</tt> (HTTP/1.1 - RFC 2616) */
    const val SEE_OTHER : Int = 303
    /** <tt>304 Not Modified</tt> (HTTP/1.0 - RFC 1945) */
    const val NOT_MODIFIED : Int = 304
    /** <tt>305 Use Proxy</tt> (HTTP/1.1 - RFC 2616) */
    const val USE_PROXY : Int = 305
    /** <tt>307 Temporary Redirect</tt> (HTTP/1.1 - RFC 2616) */
    const val TEMPORARY_REDIRECT : Int = 307

    // --- 4xx Client Error ---

    /** <tt>400 Bad Request</tt> (HTTP/1.1 - RFC 2616) */
    const val BAD_REQUEST : Int = 400
    /** <tt>401 Unauthorized</tt> (HTTP/1.0 - RFC 1945) */
    const val UNAUTHORIZED : Int = 401
    /** <tt>402 Payment Required</tt> (HTTP/1.1 - RFC 2616) */
    const val PAYMENT_REQUIRED : Int = 402
    /** <tt>403 Forbidden</tt> (HTTP/1.0 - RFC 1945) */
    const val FORBIDDEN : Int = 403
    /** <tt>404 Not Found</tt> (HTTP/1.0 - RFC 1945) */
    const val NOT_FOUND : Int = 404
    /** <tt>405 Method Not Allowed</tt> (HTTP/1.1 - RFC 2616) */
    const val METHOD_NOT_ALLOWED : Int = 405
    /** <tt>406 Not Acceptable</tt> (HTTP/1.1 - RFC 2616) */
    const val NOT_ACCEPTABLE : Int = 406
    /** <tt>407 Proxy Authentication Required</tt> (HTTP/1.1 - RFC 2616)*/
    const val PROXY_AUTHENTICATION_REQUIRED : Int = 407
    /** <tt>408 Request Timeout</tt> (HTTP/1.1 - RFC 2616) */
    const val REQUEST_TIMEOUT : Int = 408
    /** <tt>409 Conflict</tt> (HTTP/1.1 - RFC 2616) */
    const val CONFLICT : Int = 409
    /** <tt>410 Gone</tt> (HTTP/1.1 - RFC 2616) */
    const val GONE : Int = 410
    /** <tt>411 Length Required</tt> (HTTP/1.1 - RFC 2616) */
    const val LENGTH_REQUIRED : Int = 411
    /** <tt>412 Precondition Failed</tt> (HTTP/1.1 - RFC 2616) */
    const val PRECONDITION_FAILED : Int = 412
    /** <tt>413 Request Entity Too Large</tt> (HTTP/1.1 - RFC 2616) */
    const val REQUEST_TOO_LONG : Int = 413
    /** <tt>414 Request-URI Too Long</tt> (HTTP/1.1 - RFC 2616) */
    const val REQUEST_URI_TOO_LONG : Int = 414
    /** <tt>415 Unsupported Media Type</tt> (HTTP/1.1 - RFC 2616) */
    const val UNSUPPORTED_MEDIA_TYPE : Int = 415
    /** <tt>416 Requested Range Not Satisfiable</tt> (HTTP/1.1 - RFC 2616) */
    const val REQUESTED_RANGE_NOT_SATISFIABLE : Int = 416
    /** <tt>417 Expectation Failed</tt> (HTTP/1.1 - RFC 2616) */
    const val EXPECTATION_FAILED : Int = 417

    /**
     * Static constant for a 418 error.
     * <tt>418 Unprocessable Entity</tt> (WebDAV drafts?)
     * or <tt>418 Reauthentication Required</tt> (HTTP/1.1 drafts?)
     */
    // not used
    // public static final int SC_UNPROCESSABLE_ENTITY = 418;

    /**
     * Static constant for a 419 error.
     * <tt>419 Insufficient Space on Resource</tt>
     * (WebDAV - draft-ietf-webdav-protocol-05?)
     * or <tt>419 Proxy Reauthentication Required</tt>
     * (HTTP/1.1 drafts?)
     */
    const val INSUFFICIENT_SPACE_ON_RESOURCE : Int = 419
    /**
     * Static constant for a 420 error.
     * <tt>420 Method Failure</tt>
     * (WebDAV - draft-ietf-webdav-protocol-05?)
     */
    const val METHOD_FAILURE : Int = 420
    /** <tt>422 Unprocessable Entity</tt> (WebDAV - RFC 2518) */
    const val UNPROCESSABLE_ENTITY : Int = 422
    /** <tt>423 Locked</tt> (WebDAV - RFC 2518) */
    const val LOCKED : Int = 423
    /** <tt>424 Failed Dependency</tt> (WebDAV - RFC 2518) */
    const val FAILED_DEPENDENCY : Int = 424

    // --- 5xx Server Error ---

    /** <tt>500 Server Error</tt> (HTTP/1.0 - RFC 1945) */
    const val INTERNAL_SERVER_ERROR : Int = 500
    /** <tt>501 Not Implemented</tt> (HTTP/1.0 - RFC 1945) */
    const val NOT_IMPLEMENTED : Int = 501
    /** <tt>502 Bad Gateway</tt> (HTTP/1.0 - RFC 1945) */
    const val BAD_GATEWAY : Int = 502
    /** <tt>503 Service Unavailable</tt> (HTTP/1.0 - RFC 1945) */
    const val SERVICE_UNAVAILABLE : Int = 503
    /** <tt>504 Gateway Timeout</tt> (HTTP/1.1 - RFC 2616) */
    const val GATEWAY_TIMEOUT : Int = 504
    /** <tt>505 HTTP Version Not Supported</tt> (HTTP/1.1 - RFC 2616) */
    const val HTTP_VERSION_NOT_SUPPORTED : Int = 505

    /** <tt>507 Insufficient Storage</tt> (WebDAV - RFC 2518) */
    const val INSUFFICIENT_STORAGE : Int = 507
}