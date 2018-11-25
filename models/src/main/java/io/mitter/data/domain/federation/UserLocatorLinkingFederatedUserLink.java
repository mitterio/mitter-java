package io.mitter.data.domain.federation;

import io.mitter.data.domain.user.locators.UserLocator;

/**
 * A federated user link that denotes a link between a user and a federated-user based on common user-locators
 * amongst them.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class UserLocatorLinkingFederatedUserLink extends FederatedUserLink {
    /**
     * The user locator that is common to both the federated user and the user.
     */
    private UserLocator linkingUserLocator;

    public UserLocator getLinkingUserLocator() {
        return linkingUserLocator;
    }

    public UserLocatorLinkingFederatedUserLink setLinkingUserLocator(UserLocator linkingUserLocator) {
        this.linkingUserLocator = linkingUserLocator;
        return this;
    }
}
