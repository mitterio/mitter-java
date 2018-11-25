package io.mitter.data.domain.user.locators;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * An user locator for getting emails. Uses the "email" prefix for string representations:
 *
 * <pre>
 * {@code
 * EmailUserLocator emailUserLocator = new EmailUserLocator().setEmail("test@coco.gg");
 *
 * EmailUserLocator emailUserLocator = new EmailUserLocator().setLocator("email:test@coco.gg");
 * }
 * </pre>
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeName("email")
public class EmailUserLocator extends UserLocator {
    public static final String LOCATOR_SERIALIZED_PREFIX = "email";
    /**
     * The email id of the locator.
     */
    private String email;

    public EmailUserLocator() {
        super(LOCATOR_SERIALIZED_PREFIX);
    }

    public String getEmail() {
        return email;
    }

    public EmailUserLocator setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * {@inheritDoc}
     * @return Returns the email address as a string
     */
    @Override
    String getSerializedLocator() {
        return this.email;
    }

    /**
     * {@inheritDoc}
     * @param serializedLocator Accepts an email address as a string
     */
    @Override
    UserLocator setSerializedLocator(String serializedLocator) {
        this.email = serializedLocator;
        return this;
    }

    @Override
    public Class<? extends UserLocator> type() {
        return this.getClass();
    }

    @Override
    public String toString() {
        return "EmailUserLocator{" +
                "email='" + email + '\'' +
                "} <- " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailUserLocator that = (EmailUserLocator) o;

        return getEmail() != null ? getEmail().equals(that.getEmail()) : that.getEmail() == null;
    }

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : 0;
    }
}
