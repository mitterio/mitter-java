package io.mitter.sdk.java.clients

import feign.Headers
import feign.Param
import feign.RequestLine
import io.mitter.auth.data.domain.credential.accesscredential.AccessCredential
import io.mitter.auth.data.domain.credential.accesscredential.AccessCredentialStub
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.application.Application
import io.mitter.named.resources.http.endpoints.ApplicationEndpointsV1
import io.mitter.sdk.java.support.IdentifiableExpander
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on 02/2018
 *
 */

@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterApplicationAccessKeyClient {
    @RequestLine("POST " + ApplicationEndpointsV1.AccessKeys.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun newAccessKey(
        @Param(ApplicationEndpointsV1.Applications.IdParam , expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>
    ) : AccessCredential

    @RequestLine("GET " + ApplicationEndpointsV1.AccessKeys.Base)
    fun getAccessKeys(
        @Param(ApplicationEndpointsV1.Applications.IdParam , expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>
    ) : List<AccessCredentialStub>

    @RequestLine("DELETE " + ApplicationEndpointsV1.AccessKeys.Specified.Base)
    fun deleteAccessKeys(
        @Param(ApplicationEndpointsV1.Applications.IdParam , expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>,
        @Param(ApplicationEndpointsV1.AccessKeys.AccessKeyParam) accessKey: String
    )
}
