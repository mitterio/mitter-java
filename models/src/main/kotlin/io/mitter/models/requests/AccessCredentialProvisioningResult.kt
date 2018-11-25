package io.mitter.models.requests

import io.mitter.auth.data.domain.credential.accesscredential.AccessCredential

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
data class AccessCredentialProvisioningResult(
        val accessCredentials: AccessCredential,
        val exampleApiCalls: List<ExampleApiCall>
)
