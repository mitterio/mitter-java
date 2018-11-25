package io.mitter.data.domain.exceptions

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
abstract class DiagnosableException : RuntimeException, NarratableException, DiagnosticsAvailableException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) :
        super(message, cause, enableSuppression, writableStackTrace)

    override fun getTransportableMessage() : String = StandardTransportationExceptionValues.ErrorDescription
}

interface NarratableException {
    fun getTransportableMessage() : String
}

interface DiagnosticsAvailableException {
    fun getDiagnostics() : Any
}

object StandardTransportationExceptionValues {
    const val ErrorDescription = "An unknown error occurred, or no description is available for this error"
    const val ErrorCode = "unknown_error"
    const val StatusCode = 500
}
