package io.mitter.sdk.java.clients

import feign.Headers
import feign.Param
import feign.RequestLine
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.application.Application
import io.mitter.data.domain.application.properties.ApplicationProperty
import io.mitter.models.requests.PatchApplicationPropertyRequest
import io.mitter.named.resources.http.endpoints.ApplicationEndpointsV1
import io.mitter.sdk.java.support.IdentifiableExpander
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on 02/2018
 *
 */

@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterApplicationPropertiesClient {
    @RequestLine("GET " + ApplicationEndpointsV1.ApplicationProperties.Base)
    fun getApplicationProperties(
        @Param(ApplicationEndpointsV1.Applications.IdParam, expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>
    ) : List<ApplicationProperty>

    @RequestLine("PATCH " + ApplicationEndpointsV1.ApplicationProperties.Specified.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun patchApplicationProperty(
        @Param(ApplicationEndpointsV1.Applications.IdParam, expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>,
        @Param(ApplicationEndpointsV1.ApplicationProperties.SystemNameParam) systemName: String,
        @Param(ApplicationEndpointsV1.ApplicationProperties.InstanceNameParam) instanceName: String,
        patchApplicationPropertyRequest: PatchApplicationPropertyRequest
    )

    @RequestLine("POST " + ApplicationEndpointsV1.ApplicationProperties.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun newApplicationProperty(
        @Param(ApplicationEndpointsV1.Applications.IdParam, expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>,
        applicationProperty: ApplicationProperty
    )

    @RequestLine("DELETE " + ApplicationEndpointsV1.ApplicationProperties.Specified.Base)
    fun deleteApplicationProperty(
        @Param(ApplicationEndpointsV1.Applications.IdParam, expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>,
        @Param(ApplicationEndpointsV1.ApplicationProperties.SystemNameParam) systemName: String,
        @Param(ApplicationEndpointsV1.ApplicationProperties.InstanceNameParam) instanceName: String
    )
}
