package io.mitter.data.domain.annotations

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import io.mitter.data.domain.exceptions.DiagnosableException
import io.mitter.data.domain.exceptions.StandardErrorCodes
import io.mitter.data.domain.exceptions.TransportableException
import io.mitter.models.wire.HttpStatusCodes
import kotlin.reflect.KClass

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
interface Identifiable<out T: Identifiable<T>> {
    @JsonProperty("identifier")
    fun domainId() : String
    @JsonIgnore
    fun internalId(): String?
    fun type() : Class<out T>
}

@TransportableException(
    errorCode = StandardErrorCodes.IdentifierNotSet, httpStatus = HttpStatusCodes.BAD_REQUEST,
    transportableMessage = true
)
class IdentifierNotSetException(
    private val identifierField: String,
    private val identifierType: KClass<out Identifiable<*>>
) : DiagnosableException(
    "Identifier not set for type ${identifierType::class.java.simpleName}, required field $identifierField"
) {
    override fun getDiagnostics(): Any = JsonNodeFactory.instance
        .objectNode()
        .put("identifierField", identifierField)
        .put("identifierType", identifierType.java.simpleName)
}
