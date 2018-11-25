package io.mitter.models.hosted.auth;

import io.mitter.data.domain.subscriber.Subscriber;

/**
 * This is a temporary data structure.
 * This will be removed when github and google+ login comes into action in subscriber credentials.
 *
 * @author Ganessh Kumar (ganessh@nomadly.in)
 */
public class SubscriberSignupRequest {
    private Subscriber subscriber;
    private String password;

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public SubscriberSignupRequest setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SubscriberSignupRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "SubscriberSignupRequest{" +
                "subscriber=" + subscriber +
                ", password='" + "<redacted>" + '\'' +
                '}';
    }
}
