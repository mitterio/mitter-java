package io.mitter.sdk.java.support.interceptors

import io.mitter.sdk.java.objects.credentials.MitterApplicationAccessKeyCredentials
import io.mitter.sdk.java.support.interceptors.utils.AccessKeySigner

/**
 *
 * @author Rohan Prabhu (rohan@rohanprabhu.com)
 */
class MitterApplicationAccessKeySigner(
    mitterApplicationAccessKeyCredentials: MitterApplicationAccessKeyCredentials
) : AccessKeySigner(mitterApplicationAccessKeyCredentials, ApplicationAccessKeyHeader) {
    companion object {
        val ApplicationAccessKeyHeader = "X-Mitter-Application-Access-Key"
    }
}
