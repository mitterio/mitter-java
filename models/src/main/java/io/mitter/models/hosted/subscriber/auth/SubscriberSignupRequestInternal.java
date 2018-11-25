package io.mitter.models.hosted.subscriber.auth;

import io.mitter.data.domain.subscriber.Subscriber;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class SubscriberSignupRequestInternal {
    private Subscriber subscriber;
    private String hashedPassword;
    private String federatedProvider;
    private String federatedProviderId;

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public SubscriberSignupRequestInternal setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
        return this;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public SubscriberSignupRequestInternal setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
        return this;
    }

    @Override
    public String toString() {
        return "SubscriberSignupRequestInternal{" +
                "subscriber=" + subscriber +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }

    public String getFederatedProvider() {
        return federatedProvider;
    }

    public void setFederatedProvider(String federatedProvider) {
        this.federatedProvider = federatedProvider;
    }

    public String getFederatedProviderId() {
        return federatedProviderId;
    }

    public void setFederatedProviderId(String federatedProviderId) {
        this.federatedProviderId = federatedProviderId;
    }
}
