package io.mitter.models.acolyte.exceptions

import io.mitter.data.domain.exceptions.DiagnosableException
import io.mitter.data.domain.exceptions.StandardErrorCodes
import io.mitter.data.domain.exceptions.TransportableException
import io.mitter.models.wire.HttpStatusCodes

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@TransportableException(errorCode = StandardErrorCodes.MissingPrivilege,
        httpStatus = HttpStatusCodes.FORBIDDEN, transportableMessage = true)
class MissingPrivilegeException(
    private val missingPrivilege: String,
    private val onEntity: String,
    private val actingAccessor: String?
) : DiagnosableException("Required privilege `$missingPrivilege` on `$onEntity`, but is not granted to $actingAccessor") {
    data class Diagnostics(
        val missingPrivileges: List<String>,
        val actingEntity: List<String>,
        val actor: String?
    )

    override fun getDiagnostics() = Diagnostics(
        missingPrivileges = listOf(missingPrivilege),
        actingEntity = listOf(onEntity),
        actor = actingAccessor
    )
}
