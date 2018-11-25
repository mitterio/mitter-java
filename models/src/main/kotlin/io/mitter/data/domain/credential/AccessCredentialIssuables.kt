package io.mitter.data.domain.credential

import io.mitter.auth.data.domain.credential.accesscredential.AccessCredential
import io.mitter.data.domain.annotations.Identifiable
import io.mitter.data.domain.application.Application
import io.mitter.data.domain.subscriber.Subscriber

/**
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
interface AccessCredentialIssuable<T:Identifiable<T>> {
    val entity : T
}

data class SubscriberAccessCredentialIssuable(
    val subscriber: Subscriber,
    override val entity : Subscriber = subscriber
) : AccessCredentialIssuable<Subscriber>

data class ApplicationAccessCredentialIssuable(
    val application: Application,
    override val entity: Application = application
) : AccessCredentialIssuable<Application>

data class IssuedAccessCredential<E: Identifiable<E>, T: AccessCredentialIssuable<E>>(
    val accessCredentialIssuable : T,
    val accessCredential: AccessCredential
)
