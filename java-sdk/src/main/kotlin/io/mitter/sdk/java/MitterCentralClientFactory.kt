package io.mitter.sdk.java

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Feign
import feign.Logger
import feign.RequestInterceptor
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import io.mitter.sdk.java.clients.*
import io.mitter.sdk.java.clients.hlc.MitterMessagesHlcClient
import io.mitter.sdk.java.objects.credentials.*
import io.mitter.sdk.java.support.MitterSdkSupport
import io.mitter.sdk.java.support.encoder.MitterSupplementalEncoder
import io.mitter.sdk.java.support.interceptors.*
import mu.KotlinLogging
import java.util.*

/**
 *
 * @author Rohan Prabhu (rohan@rohanprabhu.com)
 */
class MitterCentralClientFactory {
    companion object {
        const val MitterCentralApiEndpoint = "https://api.mitter.io"
        const val LoggerStatementLimit = 600
    }

    private val objectMapper   : ObjectMapper = MitterSdkSupport.configureJackson(ObjectMapper())
    private val jacksonDecoder : JacksonDecoder
    private val jacksonEncoder : JacksonEncoder
    private val mitterAccessCredentials: MitterAccessCredentials?
    private val mitterCentralEndpoint : String?
    private var enableLogging : Boolean = false

    constructor(mitterAccessCredentials: MitterAccessCredentials) {
        this.mitterAccessCredentials = mitterAccessCredentials
        this.mitterCentralEndpoint = null
    }

    constructor(mitterCentralEndpoint: String) {
        this.mitterAccessCredentials = null
        this.mitterCentralEndpoint = mitterCentralEndpoint
    }

    constructor(mitterAccessCredentials: MitterAccessCredentials, mitterCentralEndpoint: String) {
        this.mitterAccessCredentials = mitterAccessCredentials
        this.mitterCentralEndpoint = mitterCentralEndpoint
    }

    constructor() {
        this.mitterCentralEndpoint = null
        this.mitterAccessCredentials = null
    }

    init {

        this.jacksonDecoder = JacksonDecoder(this.objectMapper)
        this.jacksonEncoder = JacksonEncoder(this.objectMapper)
    }

    fun setLoggingEnabled(enable: Boolean) { this.enableLogging = enable }

    /*
      -- CLIENT METHODS --
     */

    /* ****
       Access Key Test Client Methods
       ****
     */
    fun accessKeyTestClient() = accessKeyTestClient(mitterAccessCredentials)

    fun accessKeyTestClient(mitterAccessCredentials: MitterAccessCredentials?) =
        accessKeyTestClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun accessKeyTestClient(
        mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
        enableFullLogging: Boolean = this.enableLogging
    ) : MitterAccessKeyTestClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterAccessKeyTestClient::class.java)
            .target(MitterAccessKeyTestClient::class.java, centralApiEndpoint)
    }

    /* ****
       ACL Operations Client Methods
       ****
     */
    fun aclOperationsClient() = aclOperationsClient(mitterAccessCredentials)

    fun aclOperationsClient(mitterAccessCredentials: MitterAccessCredentials?) =
        aclOperationsClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun aclOperationsClient(
        mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
        enableFullLogging: Boolean = this.enableLogging
    ) : MitterAclOperationsClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterAccessKeyTestClient::class.java)
            .target(MitterAclOperationsClient::class.java, centralApiEndpoint)
    }

    /* ****
       Application properties Client Methods
       ****
     */

    fun applicationPropertiesClient() = applicationPropertiesClient(mitterAccessCredentials)

    fun applicationPropertiesClient(mitterAccessCredentials: MitterAccessCredentials?) =
        applicationPropertiesClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun applicationPropertiesClient(
        mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
        enableFullLogging: Boolean = this.enableLogging
    ) : MitterApplicationPropertiesClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterApplicationClient::class.java)
            .target(MitterApplicationPropertiesClient::class.java, centralApiEndpoint)
    }

    /* ****
       Application AccessKey Client Methods
       ****
     */

    fun applicationAccessKeyClient() = applicationAccessKeyClient(mitterAccessCredentials)

    fun applicationAccessKeyClient(mitterAccessCredentials: MitterAccessCredentials?) =
        applicationAccessKeyClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun applicationAccessKeyClient(
        mitterAccessCredentials: MitterAccessCredentials?,
        centralApiEndpoint: String,
        enableFullLogging: Boolean = this.enableLogging
    ) : MitterApplicationAccessKeyClient {
        return getBaseBuilder(mitterAccessCredentials,
            enableFullLogging,
            MitterApplicationAccessKeyClient::class.java)
            .target(MitterApplicationAccessKeyClient::class.java, centralApiEndpoint)
    }

    /* ****
        Application Client Methods
    ****/

    fun applicationClient() = applicationClient(mitterAccessCredentials)

    fun applicationClient(mitterAccessCredentials: MitterAccessCredentials?) =
        applicationClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun applicationClient(
        mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
        enableFullLogging: Boolean = this.enableLogging
    ) : MitterApplicationClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterApplicationClient::class.java)
            .target(MitterApplicationClient::class.java, centralApiEndpoint)
    }

    /* ****
      Subscriber Client Methods
      ****
    */

    fun subscriberClient() = subscriberClient(mitterAccessCredentials)

    fun subscriberClient(mitterAccessCredentials: MitterAccessCredentials?) =
        subscriberClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun subscriberClient(
        mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
        enableFullLogging: Boolean = this.enableLogging
    ) : MitterAuthenticationEndpointsClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterAuthenticationEndpointsClient::class.java)
            .target(MitterAuthenticationEndpointsClient::class.java, centralApiEndpoint)
    }

    /* ****
       Subscriber Client Methods
       ****
    */

    fun subscriberAccessApiClient() = subscriberAccessApiClient(mitterAccessCredentials)

    fun subscriberAccessApiClient(mitterAccessCredentials: MitterAccessCredentials?) =
        subscriberAccessApiClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun subscriberAccessApiClient(
        mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
        enableFullLogging: Boolean = this.enableLogging
    ) : MitterSubscriberAccessApiClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterSubscriberAccessApiClient::class.java)
            .target(MitterSubscriberAccessApiClient::class.java, centralApiEndpoint)
    }

    /* ****
       Users Client Methods
       ****
     */

    fun usersClient() = usersClient(mitterAccessCredentials)

    fun usersClient(mitterAccessCredentials: MitterAccessCredentials?) =
        usersClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun usersClient(
        mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
        enableFullLogging: Boolean = this.enableLogging
    ) : MitterUsersClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterUsersClient::class.java)
            .requestInterceptor(MetadataEncodingInterceptor())
            .target(MitterUsersClient::class.java, centralApiEndpoint)
    }

    /* ****
       User Auth Client Methods
       ****
     */

    fun userAuthClient() = userAuthClient(mitterAccessCredentials)

    fun userAuthClient(mitterAccessCredentials: MitterAccessCredentials?) =
        userAuthClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun userAuthClient(mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
                       enableFullLogging: Boolean = this.enableLogging) : MitterUsersAuthClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterUsersAuthClient::class.java)
            .target(MitterUsersAuthClient::class.java, centralApiEndpoint)
    }

    /* ****
      User Presence Client Methods
      ****
    */

    fun userPresenceClient() = userPresenceClient(mitterAccessCredentials)

    fun userPresenceClient(mitterAccessCredentials: MitterAccessCredentials?) =
        userPresenceClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun userPresenceClient(mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
                       enableFullLogging: Boolean = this.enableLogging) : MitterUserPresenceClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterUserPresenceClient::class.java)
            .target(MitterUserPresenceClient::class.java, centralApiEndpoint)
    }

    /* ****
      Entity Profile Client Methods
      ****
    */

    fun entityProfileClient() = entityProfileClient(mitterAccessCredentials)

    fun entityProfileClient(mitterAccessCredentials: MitterAccessCredentials?) =
        entityProfileClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun entityProfileClient(mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
                       enableFullLogging: Boolean = this.enableLogging) : MitterEntityProfileClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterEntityProfileClient::class.java)
            .target(MitterEntityProfileClient::class.java, centralApiEndpoint)
    }

    /* ****
       Channels Client Methods
       ****
     */

    fun  channelsClient() = channelsClient(mitterAccessCredentials)

    fun channelsClient(mitterAccessCredentials: MitterAccessCredentials?) =
        channelsClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun channelsClient(
        mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
        enableFullLogging: Boolean = this.enableLogging
    ) : MitterChannelsClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterChannelsClient::class.java)
            .requestInterceptor(MetadataEncodingInterceptor())
            .target(MitterChannelsClient::class.java, centralApiEndpoint)
    }

    /* ****
       Delivery Endpoint Client Methods
       ****
     */

    fun deliveryEndpointClient() = deliveryEndpointClient(mitterAccessCredentials)

    fun deliveryEndpointClient(mitterAccessCredentials: MitterAccessCredentials?) =
        deliveryEndpointClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun deliveryEndpointClient(
        mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
        enableFullLogging: Boolean = this.enableLogging
    ) : MitterDeliveryEndpointsClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterDeliveryEndpointsClient::class.java)
            .target(MitterDeliveryEndpointsClient::class.java, centralApiEndpoint)
    }

    /* ****
       Messages Client Methods
       ****
     */

    fun messagesClient() = messagesClient(mitterAccessCredentials)

    fun messagesClient(mitterAccessCredentials: MitterAccessCredentials?) =
        messagesClient(mitterAccessCredentials, mitterCentralEndpoint ?: MitterCentralApiEndpoint)

    fun messagesClient(
        mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
        enableFullLogging: Boolean = this.enableLogging
    ) : MitterMessagesClient {
        return getBaseBuilder(mitterAccessCredentials, enableFullLogging, MitterMessagesClient::class.java)
            .target(MitterMessagesClient::class.java, centralApiEndpoint)
    }

    /* ****
       Messages HLC Client methods
       ****
     */

    fun messagesHlcClient() = MitterMessagesHlcClient(messagesClient())

    fun messagesHlcClient(
        mitterAccessCredentials: MitterAccessCredentials?
    ) = MitterMessagesHlcClient(
        messagesClient(mitterAccessCredentials)
    )

    fun messagesHlcClient(
        mitterAccessCredentials: MitterAccessCredentials?, centralApiEndpoint: String,
        enableFullLogging: Boolean
    ) = MitterMessagesHlcClient(
        messagesClient(mitterAccessCredentials, centralApiEndpoint, enableFullLogging)
    )

    /*
      -- END OF CLIENT METHODS --
     */

    private fun getBaseBuilder(
        mitterAccessCredentials: MitterAccessCredentials?, enableFullLogging: Boolean,
        target: Class<*>
    ) : Feign.Builder =
        mitterAccessCredentials?.let {
            Feign.builder()
                .decoder(this.jacksonDecoder)
                .encoder(MitterSupplementalEncoder(this.jacksonEncoder))
                .let {
                    if (enableFullLogging) {
                        it.logLevel(Logger.Level.FULL).logger(MitterLogger(target))
                    } else {
                        it
                    }
                }
                .client(OkHttpClient())
                .configureInterceptors(it)
        } ?: throw IllegalStateException("Must set the credentials either in the factory," +
            " or pass it to the individual client creator method")

    private fun Feign.Builder.configureInterceptors(mitterAccessCredentials: MitterAccessCredentials) : Feign.Builder {
        val interceptorChain = mutableListOf<RequestInterceptor>()

        fun resolveInterceptor(mitterAccessCredentials: MitterAccessCredentials) {
            when (mitterAccessCredentials) {
                is MitterApplicationCredentials ->
                    when (mitterAccessCredentials) {
                        is MitterApplicationAccessKeyCredentials ->
                            interceptorChain.add(MitterApplicationAccessKeySigner(mitterAccessCredentials))

                        is MitterSudoApplicationCredentials -> {
                            interceptorChain.add(MitterSudoApplicationSetter(mitterAccessCredentials))
                            resolveInterceptor(mitterAccessCredentials.mitterSubscriberCredentials)
                        }
                    }

                is MitterUserCredentials ->
                    when (mitterAccessCredentials) {
                        is MitterUserTokenCredentials ->
                            interceptorChain.add(MitterUserTokenCredentialSetter(mitterAccessCredentials))

                        is MitterSudoUserCredentials -> {
                            interceptorChain.add(MitterApplicationSudoUserSetter(mitterAccessCredentials))
                            resolveInterceptor(mitterAccessCredentials.mitterApplicationCredentials)
                        }
                    }

                is MitterSubscriberCredentials -> {
                    when(mitterAccessCredentials) {
                        is MitterSubscriberApiAccessCredentials -> {
                            interceptorChain.add(MitterSubscriberAccessKeySigner(mitterAccessCredentials))
                        }

                        is MitterAuthorizeSubscriberCredentials -> {
                            interceptorChain.add(MitterSubscriberAuthorizationSetter(mitterAccessCredentials))
                        }
                    }
                }

                is MitterAnonymousCredentials -> {
                   // do nothing
                   // this is a call that is made without any authorization information
                }
            }
        }

        resolveInterceptor(mitterAccessCredentials)
        return this.requestInterceptor(MitterChainedRequestInterceptor(interceptorChain))
    }

    private fun getApiEndpoint(apiEndpoint: String?) : String =
        apiEndpoint ?: MitterCentralApiEndpoint

    private class MitterLogger(loggingClass: Class<*>) : Logger() {
        private val logger = KotlinLogging.logger("[WIRE] " + loggingClass.simpleName)

        override fun log(configKey: String?, format: String?, vararg args: Any?) {
            logger.info { Formatter().format(format, *args).toString()
                .let {
                    if (it.length > LoggerStatementLimit) {
                        it.substring(0, LoggerStatementLimit) + " .. [TRUNCATED to $LoggerStatementLimit characters]"
                    } else {
                        it
                    }
                }
            }
        }
    }
}
