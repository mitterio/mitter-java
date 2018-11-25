package io.mitter.models.hosted

import org.slf4j.event.Level

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
data class ErrorResponseBody(
    val message: String = "Unknown error",
    val errorCode: String = "unknown_error",
    val instanceId: String? = null,
    val diagnostics: Any? = null,
    val timestamp: Long = System.currentTimeMillis()
)

data class ErrorResponse(
    val httpStatus: Int,
    val applicableException: Exception,
    val logLevel: Level,
    val errorResponseBody: ErrorResponseBody
)
