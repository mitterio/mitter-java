package io.mitter.sdk.java.support.interceptors

import com.fasterxml.jackson.databind.ObjectMapper
import feign.RequestInterceptor
import feign.RequestTemplate
import io.mitter.sdk.java.objects.credentials.MitterUserTokenCredentials
import java.nio.charset.Charset
import java.util.*

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class MitterUserTokenCredentialSetter(
    private val userTokenCredentials: MitterUserTokenCredentials
) : RequestInterceptor {
    companion object {
        private val objectMapper = ObjectMapper()

        const val UserCredentialTokenHeaderKey = "X-Issued-Mitter-User-Authorization"
        const val ApplicationIdHeaderKey       = "X-Mitter-Application-Id"
    }

    override fun apply(request: RequestTemplate) {
        request.header(UserCredentialTokenHeaderKey, userTokenCredentials.signedUserToken)
        request.header(ApplicationIdHeaderKey, getApplicationId())
    }

    private fun getApplicationId(): String {
        if (this.userTokenCredentials.applicationId != null) {
            return userTokenCredentials.applicationId
        }

        userTokenCredentials.signedUserToken.split(":")
            .let {
                if (it.size != 3) {
                    throw IllegalArgumentException("The user jwt is invalid. Expected three period-separated parts")
                }

                val jwtJson = Base64.getDecoder().decode(it[1]).toString(Charset.defaultCharset())
                val jwtJsonNode = objectMapper.readTree(jwtJson)

                if (jwtJsonNode["applicationId"] == null) {
                    throw IllegalArgumentException("You are using an old format of the JWT where" +
                        " applicationIds were not provided. This requires you to set an applicationId when" +
                        " creating the MitterUserTokenCredentialSetter, but one was not found on this instance.")
                }

                return jwtJsonNode["applicationId"].asText()
            }
    }
}
