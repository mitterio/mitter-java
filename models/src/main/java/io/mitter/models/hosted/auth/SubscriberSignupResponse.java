package io.mitter.models.hosted.auth;

import io.mitter.data.domain.annotations.Identifiable;
import io.mitter.data.domain.subscriber.Subscriber;
import io.mitter.models.hosted.ErrorResponseBody;
import io.mitter.models.requests.SubscriberAuthResponse;

/**
 * @author Ganessh Kumar (ganessh@nomadly.in)
 */
public class SubscriberSignupResponse {
    private Identifiable<Subscriber> subscriberId;
    private SubscriberAuthResponse autoLoginAuthResponse;

    private ErrorResponseBody error;

    public Identifiable<Subscriber> getSubscriberId() {
        return subscriberId;
    }

    public SubscriberSignupResponse setSubscriberId(Identifiable<Subscriber> subscriberId) {
        this.subscriberId = subscriberId;
        return this;
    }

    public SubscriberAuthResponse getAutoLoginAuthResponse() {
        return autoLoginAuthResponse;
    }

    public void setAutoLoginAuthResponse(SubscriberAuthResponse autoLoginAuthResponse) {
        this.autoLoginAuthResponse = autoLoginAuthResponse;
    }

    public ErrorResponseBody getError() {
        return error;
    }

    public SubscriberSignupResponse setError(ErrorResponseBody error) {
        this.error = error;
        return this;
    }
}
