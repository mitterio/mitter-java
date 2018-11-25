package io.mitter.sdk.java.support.interceptors

import io.mitter.sdk.java.objects.credentials.MitterSubscriberApiAccessCredentials
import io.mitter.sdk.java.support.interceptors.utils.AccessKeySigner

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
class MitterSubscriberAccessKeySigner(
    mitterSubscriberApiAccessCredentials: MitterSubscriberApiAccessCredentials
) : AccessKeySigner(mitterSubscriberApiAccessCredentials, SubscriberAccessKeyHeader) {
    companion object {
        val SubscriberAccessKeyHeader = "X-Mitter-Subscriber-Access-Key"
    }
}
