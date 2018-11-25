package io.mitter.data.domain.exceptions

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
object StandardErrorCodes {
    const val ChannelRuleSetViolation = "channel_ruleset_violation"
    const val EntityNotFound = "entity_not_found"
    const val DuplicateEntity = "duplicate_entity"
    const val InvalidParameterSet = "invalid_parameter_set"
    const val LimitExceeded = "limit_exceeded"
    const val MissingContext = "missing_context"
    const val MissingPrivilege = "missing_privilege"
    const val InvalidActionSpecification = "invalid_action_specification"
    const val BadRequest = "bad_request"
    const val AuthorizationError = "authorization_error"
    const val TokenRevoked = "token_revoked"
    const val TtlExtensionRefused = "ttl_extension_refused"
    const val TokenIssuanceRefused = "token_issuance_refused"
    const val TokenConfigurationIncomplete = "token_configuration_incomplete"
    const val InvalidEntity = "invalid_entity"
    const val MethodNotAllowed = "method_not_allowed"
    const val UnsupportedMediaType = "unsupported_media_type"
    const val IdentifierNotSet = "identifier_not_set"
    const val MismatchedPrincipal = "mismatched_principal"
    const val InvalidDomainAttrbiute = "invalid_domain_attribute"
    const val InvalidObjectDefintion = "invalid_object_definition"
    const val InvalidInputSyntax = "invalid_input_syntax"
}
