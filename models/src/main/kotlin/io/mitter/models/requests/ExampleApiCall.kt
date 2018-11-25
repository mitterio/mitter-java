package io.mitter.models.requests

import java.net.URI

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class ExampleApiCall (
    val method: String,
    val uri: URI,
    val exampleParams: Map<String, String>,
    val headers: Map<String, String>,
    val expectedOutput: String,
    val requestBody: String
)
