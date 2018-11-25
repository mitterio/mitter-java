package io.mitter.data.domain.federation;

import io.mitter.data.domain.user.ScreenName;
import io.mitter.data.domain.user.locators.UserLocator;

import java.util.List;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class FederatedUserProfile {
    private List<UserLocator> userLocators;
    private ScreenName suggestedScreenName;

    public List<UserLocator> getUserLocators() {
        return userLocators;
    }

    public FederatedUserProfile setUserLocators(List<UserLocator> userLocators) {
        this.userLocators = userLocators;
        return this;
    }

    public ScreenName getSuggestedScreenName() {
        return suggestedScreenName;
    }

    public FederatedUserProfile setSuggestedScreenName(ScreenName suggestedScreenName) {
        this.suggestedScreenName = suggestedScreenName;
        return this;
    }
}
