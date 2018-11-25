package io.mitter.data.domain.user;

/**
 * A profile associated with a user. Contains personal information of a user.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class Profile {
    /**
     * The first name of the user.
     */
    private String firstName;
    /**
     * The last name of the user.
     */
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
