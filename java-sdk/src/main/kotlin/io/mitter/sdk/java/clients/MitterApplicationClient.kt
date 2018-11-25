package io.mitter.sdk.java.clients

import feign.Headers
import feign.Param
import feign.RequestLine
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.application.Application
import io.mitter.data.domain.application.ApplicationProfile
import io.mitter.data.domain.application.SubscriberApplication
import io.mitter.named.resources.http.endpoints.ApplicationEndpointsV1
import io.mitter.sdk.java.support.IdentifiableExpander
import io.mitter.sdk.java.utils.StandardHeaderLines

/**
 *
 * @author Ankush Kumar(ankush@mitter.io) on 02/2018
 *
 */

@Headers(StandardHeaderLines.AcceptApplicationJson)
interface MitterApplicationClient {
    @RequestLine("POST " + ApplicationEndpointsV1.Applications.Base)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun addApplication(
        subscriberApplication: SubscriberApplication
    ) : SubscriberApplication

    @RequestLine("GET " + ApplicationEndpointsV1.Applications.Base)
    fun getApplications() : List<SubscriberApplication>

    @RequestLine("GET " + ApplicationEndpointsV1.Applications.Specified.Base)
    fun getApplicationWithId(
        @Param(ApplicationEndpointsV1.Applications.IdParam, expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>
    ) : SubscriberApplication

    @RequestLine("DELETE " + ApplicationEndpointsV1.Applications.Specified.Base)
    fun deleteApplication(
        @Param(ApplicationEndpointsV1.Applications.IdParam, expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>
    )

    @RequestLine("GET " + ApplicationEndpointsV1.Applications.Specified.Profile)
    fun getApplicationProfile(
        @Param(ApplicationEndpointsV1.Applications.IdParam, expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>
    ) : SubscriberApplication

    @RequestLine("PUT " + ApplicationEndpointsV1.Applications.Specified.Profile)
    @Headers(StandardHeaderLines.ContentTypeApplicationJson)
    fun setApplicationProfile(
        applicationProfile: ApplicationProfile,
        @Param(ApplicationEndpointsV1.Applications.IdParam, expander = IdentifiableExpander::class)
        applicationId: Identifiable<Application>
    )
}
