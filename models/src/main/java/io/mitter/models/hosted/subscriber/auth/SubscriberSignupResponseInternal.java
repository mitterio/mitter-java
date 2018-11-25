package io.mitter.models.hosted.subscriber.auth;

import io.mitter.data.domain.annotations.Identifiable;
import io.mitter.data.domain.subscriber.Subscriber;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class SubscriberSignupResponseInternal {
    private Identifiable<Subscriber> subscriberId;

    public Identifiable<Subscriber> getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Identifiable<Subscriber> subscriberId) {
        this.subscriberId = subscriberId;
    }
}
