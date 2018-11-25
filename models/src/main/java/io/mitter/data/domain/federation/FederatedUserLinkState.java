package io.mitter.data.domain.federation;

/**
 * Represents the state of a link between a federated user and a user.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public enum FederatedUserLinkState {
    /**
     * This federated user is not linked to any user
     */
    Unlinked,
    /**
     * This federated user is linked to a user
     */
    Linked,
    /**
     * This federated user is linked to a user, but is currently detached. All entities MUST obey the semantics of
     * this federated user not being linked to any user
     */
    Detached
}
