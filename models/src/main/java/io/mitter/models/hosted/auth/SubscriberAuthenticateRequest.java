package io.mitter.models.hosted.auth;

/**
 * Created by ganessh on 17/12/16.
 */
public class SubscriberAuthenticateRequest {
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public SubscriberAuthenticateRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SubscriberAuthenticateRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
