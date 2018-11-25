package io.mitter.sdk.java.clients

import feign.Headers
import feign.Param
import feign.RequestLine
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.application.Application
import io.mitter.models.requests.PatchEventbusPropertyRequest
import io.mitter.named.resources.http.endpoints.ApplicationEndpointsV1
import io.mitter.sdk.java.support.IdentifiableExpander
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on Mar / 2018
 *
 */

@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterEventbusClient {
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    @RequestLine("PATCH " + ApplicationEndpointsV1.ApplicationProperties.Eventbus.Specified.Base)
    fun setEventbusActiveStatus(
        @Param(ApplicationEndpointsV1.Applications.IdParam , expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>,
        @Param(ApplicationEndpointsV1.ApplicationProperties.InstanceNameParam)
        eventbusPropertyInstanceName: String,
        patchEventbusPropertyRequest: PatchEventbusPropertyRequest
    )
}
