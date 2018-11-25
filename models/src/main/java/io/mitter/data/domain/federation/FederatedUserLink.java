package io.mitter.data.domain.federation;

import io.mitter.data.domain.annotations.Identifiable;
import io.mitter.data.domain.user.User;

/**
 * Represents a link between a {@link FederatedUser} and a {@link User}. This might not necessarily be used to represent
 * a link within the system or an entity that exists. This can also be used to denote a proposed-link or a linkable candidate
 * between these two entities.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class FederatedUserLink {
    /**
     * The user within this link.
     */
    private Identifiable<User> linkUserId;
    /**
     * The federated user within this link.
     */
    private Identifiable<FederatedUser> federatedUserId;
    /**
     * The state of the link. If this value is null, it means that this object is not representative of any persisted
     * or existent entity.
     */
    private FederatedUserLinkState linkState;

    public Identifiable<User> getLinkUserId() {
        return linkUserId;
    }

    public FederatedUserLink setLinkUserId(Identifiable<User> linkUserId) {
        this.linkUserId = linkUserId;
        return this;
    }

    public Identifiable<FederatedUser> getFederatedUserId() {
        return federatedUserId;
    }

    public FederatedUserLink setFederatedUserId(Identifiable<FederatedUser> federatedUserId) {
        this.federatedUserId = federatedUserId;
        return this;
    }

    public FederatedUserLinkState getLinkState() {
        return linkState;
    }

    public FederatedUserLink setLinkState(FederatedUserLinkState linkState) {
        this.linkState = linkState;
        return this;
    }

    @Override
    public String toString() {
        return "FederatedUserLink{" +
                "linkUserId=" + linkUserId +
                ", federatedUserId=" + federatedUserId +
                ", linkState=" + linkState +
                '}';
    }
}
