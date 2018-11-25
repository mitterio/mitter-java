package io.mitter.named.resources.http.endpoints

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
object ApplicationEndpointsV1 {
    object Applications {
        const val IdParam = "applicationId"

        const val Base    = EndpointsV1.VersionPrefix + "/applications"
        const val Profile = EndpointsV1.VersionPrefix + "/applications/profile"

        object Specified {
            const val Base =    Applications.Base           + "/{applicationId}"
            const val Profile = Applications.Specified.Base + "/profile"
        }
    }

    object AccessKeys {
        const val AccessKeyParam = "accessKey"

        const val Base = Applications.Specified.Base + "/accessKeys"

        object Specified {
            const val Base = AccessKeys.Base + "/{accessKey}"
        }
    }

    object ApplicationProperties {
        const val SystemNameParam = "systemName"
        const val InstanceNameParam = "instanceName"

        const val Base = Applications.Specified.Base + "/properties"

        object Eventbus {
            const val Base = ApplicationProperties.Base + "/eventbus"

            object Specified {
                const val Base = Eventbus.Base + "/{${ApplicationProperties.InstanceNameParam}}"
                //   /applications/{applicationId}/properties/eventbus/instanceName
            }
        }

        object Specified {
            const val Base = ApplicationProperties.Base + "/{systemName}/{instanceName}/"
        }
    }
}
