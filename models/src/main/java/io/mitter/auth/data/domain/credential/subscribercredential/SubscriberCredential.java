package io.mitter.auth.data.domain.credential.subscribercredential;

import io.mitter.data.domain.annotations.Identifiable;
import io.mitter.data.domain.subscriber.Subscriber;

import java.time.Instant;

/**
 * @author Ganessh Kumar (ganessh@noamdly.in)
 */
public class SubscriberCredential {
    private Identifiable<Subscriber> subscriberId;
    private String hashedPassword;
    private Instant passwordExpiryTime;
    private String resetToken;
    private Instant resetTokenExpiryTime;
    private Instant lastUpdated;

    public Identifiable<Subscriber> getSubscriberId() {
        return subscriberId;
    }

    public SubscriberCredential setSubscriberId(Identifiable<Subscriber> subscriberId) {
        this.subscriberId = subscriberId;
        return this;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public SubscriberCredential setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
        return this;
    }

    public Instant getPasswordExpiryTime() {
        return passwordExpiryTime;
    }

    public SubscriberCredential setPasswordExpiryTime(Instant passwordExpiryTime) {
        this.passwordExpiryTime = passwordExpiryTime;
        return this;
    }

    public String getResetToken() {
        return resetToken;
    }

    public SubscriberCredential setResetToken(String resetToken) {
        this.resetToken = resetToken;
        return this;
    }

    public Instant getResetTokenExpiryTime() {
        return resetTokenExpiryTime;
    }

    public SubscriberCredential setResetTokenExpiryTime(Instant resetTokenExpiryTime) {
        this.resetTokenExpiryTime = resetTokenExpiryTime;
        return this;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public SubscriberCredential setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }
}
