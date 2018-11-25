package io.mitter.data.domain.user.locators;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.mitter.data.domain.annotations.Identifiable;
import io.mitter.data.domain.annotations.IdentifiableEntity;
import io.mitter.data.domain.annotations.Identifier;
import io.mitter.data.domain.application.Application;
import io.mitter.data.domain.user.VerificationStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A user locator is any object that can be used to locate a user based on a certain property or possession (email,
 * state provided id etc.).
 *
 * This class cannot be used as-is, but is to be subclassed based on what the identifying object is. All implementations
 * must provide a serialized representation (as a {@link String}) and must also accept the same format back. All
 * modules handling this object promise to never modify the serialized string in any way and will only use the object
 * as-is.
 *
 * The class exports the serialized locator using a prefix followed by a colon (":") to differentiate amongst different
 * locators. This prefix must be declared by each implementation.
 *
 * It is recommeneded that the prefix be a static public String field on each implementation.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailUserLocator.class),
        @JsonSubTypes.Type(value = MobileNumberLocator.class)
})
public abstract class UserLocator implements IdentifiableEntity<UserLocator> {
    private final String locatorSerializationPrefix;

    /**
     * The verification status of this locator. Defaults to {@link VerificationStatus#StatusNotProvided}
     */
    private VerificationStatus verificationStatus;
    private Identifiable<Application> applicationId;
    private String userLocatorId;

    @Identifier
    public String getUserLocatorId() {
        return userLocatorId;
    }

    /**
     * @param locatorSerializationPrefix The prefix for this locator to be used in the serialized format.
     */
    protected UserLocator(String locatorSerializationPrefix) {
        this.locatorSerializationPrefix = locatorSerializationPrefix;
        this.verificationStatus = VerificationStatus.StatusNotProvided;
    }

    /**
     * @return The serialized implementation of the locator
     */
    abstract String getSerializedLocator();

    /**
     * @param serializedLocator The serialized implementation of the locator
     */
    abstract UserLocator setSerializedLocator(String serializedLocator);

    /**
     * @return Get the serialized locator with the specific prefix
     */
    public final String getLocator() {
        return this.locatorSerializationPrefix + ":" + this.getSerializedLocator();
    }

    /**
     * @param serializedLocator The serialized locator with the declared prefix
     * @return The current object (of the subclassed type, might require casting for use)
     */
    public final UserLocator setLocator(String serializedLocator) {
        if (!serializedLocator.startsWith(locatorSerializationPrefix + ":")) {
            throw new IllegalArgumentException("Invalid string for locator `" + this.getClass().getName()
                    + "`: " + serializedLocator);
        }

        String[] parts = serializedLocator.split(":", 2);
        this.setSerializedLocator(parts[1]);
        return this;
    }

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public UserLocator setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
        return this;
    }

    public Identifiable<Application> getApplicationId() {
        return applicationId;
    }

    public UserLocator setApplicationId(Identifiable<Application> applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public UserLocator setUserLocatorId(String userLocatorId) {
        this.userLocatorId = userLocatorId;
        return this;
    }

    @NotNull
    @Override
    public String domainId() {
        return getUserLocatorId();
    }

    @Nullable
    @Override
    public String internalId() {
        return getUserLocatorId();
    }

    @Override
    public String toString() {
        return "UserLocator[id=" + domainId() + "]{" +
                "locatorSerializationPrefix='" + locatorSerializationPrefix + '\'' +
                ", verificationStatus=" + verificationStatus +
                ", applicationId=" + applicationId +
                ", userLocatorId='" + userLocatorId + '\'' +
                '}';
    }
}
