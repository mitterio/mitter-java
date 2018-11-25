package io.mitter.models.hosted.auth;

import io.mitter.data.domain.annotations.Identifiable;
import io.mitter.data.domain.subscriber.Subscriber;

/**
 * @author Ganessh Kumar (ganessh@nomadly.in)
 */
public class SubscriberAuthenticateResponse {
    private Identifiable<Subscriber> subscriberId;

    private boolean success;

    public Identifiable<Subscriber> getSubscriberId() {
        return subscriberId;
    }

    public SubscriberAuthenticateResponse setSubscriberId(Identifiable<Subscriber> subscriberId) {
        this.subscriberId = subscriberId;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public SubscriberAuthenticateResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
