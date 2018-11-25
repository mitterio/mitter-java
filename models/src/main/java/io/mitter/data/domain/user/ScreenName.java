package io.mitter.data.domain.user;

/**
 * The screen name associated with a user. Is used as a display string in communications.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class ScreenName {
    private String screenName;

    public String getScreenName() {
        return screenName;
    }

    public ScreenName setScreenName(String screenName) {
        this.screenName = screenName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScreenName that = (ScreenName) o;

        return screenName != null ? screenName.equals(that.screenName) : that.screenName == null;

    }

    @Override
    public int hashCode() {
        return screenName != null ? screenName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ScreenName{" +
                "screenName='" + screenName + '\'' +
                '}';
    }
}
