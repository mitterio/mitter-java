package io.mitter.sdk.java.objects.credentials

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
sealed class MitterAccessCredentials

sealed class MitterSubscriberCredentials : MitterAccessCredentials()
sealed class MitterApplicationCredentials : MitterAccessCredentials()
sealed class MitterUserCredentials : MitterAccessCredentials()

class MitterAnonymousCredentials : MitterAccessCredentials()

data class CredentialsJson(
    val json: String
)

data class AccessKeyPair(
    val accessKey: String,
    val accessSecretBase64: String
) {
    private constructor(credentialsJson: JsonNode) : this(
        credentialsJson["accessKey"]["key"].asText(),
        credentialsJson["accessSecret"]["secret"].asText()
    )

    constructor(credentialsJson: CredentialsJson) :
            this(ObjectMapper().readTree(credentialsJson.json))
}

fun accessKeyFromJson(credentialJson: String) = AccessKeyPair(CredentialsJson(credentialJson))

interface AccessKeyCredential {
    val accessKeyPair: AccessKeyPair
}

data class MitterSubscriberApiAccessCredentials(
    override val accessKeyPair : AccessKeyPair
) : MitterSubscriberCredentials(), AccessKeyCredential {
    constructor(accessKey: String, accessSecretBase64: String) :
        this(AccessKeyPair(accessKey, accessSecretBase64))
}

data class MitterSudoApplicationCredentials(
    val applicationId: String,
    val mitterSubscriberCredentials: MitterSubscriberCredentials
) : MitterApplicationCredentials()

data class MitterApplicationAccessKeyCredentials(
    override val accessKeyPair: AccessKeyPair
) : MitterApplicationCredentials(), AccessKeyCredential {
    constructor(accessKey: String, accessSecretBase64: String) :
        this(AccessKeyPair(accessKey, accessSecretBase64))
}

data class MitterUserTokenCredentials(
    val signedUserToken: String,
    val applicationId: String? = null,
    val tokenId: String? = null
) : MitterUserCredentials()

data class MitterSudoUserCredentials(
    val userId: String,
    val mitterApplicationCredentials: MitterApplicationCredentials
) : MitterUserCredentials()

data class MitterAuthorizeSubscriberCredentials(
    val signedToken: String
) : MitterSubscriberCredentials()