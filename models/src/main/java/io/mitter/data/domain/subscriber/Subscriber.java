package io.mitter.data.domain.subscriber;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mitter.data.domain.annotations.IdentifiableEntity;
import io.mitter.data.domain.annotations.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.mitter.models.commons.AuditInfo;
import io.mitter.models.commons.Auditable;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
public class Subscriber implements IdentifiableEntity<Subscriber>, Auditable {
    private String subscriberId;
    private String internalId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private AuditInfo auditInfo;

    private final String federatedUserId;
    private final String federationProvider;

    public Subscriber(String federatedUserId, String federationProvider) {
        this.federatedUserId = federatedUserId;
        this.federationProvider = federationProvider;
    }

    public Subscriber() {
        this(null, null);
    }

    @Identifier
    public String getSubscriberId() {
        return subscriberId;
    }

    public String getInternalId() {
        return internalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public Subscriber setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
        return this;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getUsername() {
        return username;
    }

    public Subscriber setUsername(String username) {
        this.username = username;
        return this;
    }

    public Subscriber setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Subscriber setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Subscriber setEmail(String email) {
        this.email = email;
        return this;
    }

    public Subscriber setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @NotNull
    @Override
    public Class<? extends Subscriber> type() {
        return this.getClass();
    }

    @NotNull
    @Override
    public String domainId() {
        return getSubscriberId();
    }

    @Nullable
    @Override
    public String internalId() {
        return getInternalId();
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "subscriberId='" + subscriberId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    @Override
    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    @Override
    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    @JsonIgnore
    public String getFederatedUserId() {
        return federatedUserId;
    }

    @JsonIgnore
    public String getFederationProvider() {
        return federationProvider;
    }
}
