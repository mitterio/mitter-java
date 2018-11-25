package io.mitter.models.central.exceptions

import io.mitter.data.domain.exceptions.DiagnosableException
import io.mitter.data.domain.exceptions.StandardErrorCodes
import io.mitter.data.domain.exceptions.TransportableException
import io.mitter.models.central.context.ResolvableContext
import io.mitter.models.wire.HttpStatusCodes

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@TransportableException(errorCode = StandardErrorCodes.MissingContext,
        httpStatus = HttpStatusCodes.BAD_REQUEST, transportableMessage = true)
class MissingContextException(
    private val missingContext: String
) : DiagnosableException("The operation you tried to perform  requires a context of type `$missingContext`" +
    " but such a context could not be resolved. Maybe you are missing one of the required headers in your request or" +
    " configuration in your other pipelines ") {
    companion object {
        @JvmStatic
        fun ofContextType(contextType: Class<*>) =
            MissingContextException(contextType.getAnnotation(ResolvableContext::class.java)!!
                .value)
    }

    data class Diagnostics(
        val missingContext: String
    )

    override fun getDiagnostics() = Diagnostics(
        missingContext = missingContext
    )
}
