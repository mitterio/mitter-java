package io.mitter.auth.data.domain.credential.accesscredential;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A stub for an access credential, which does not contain any secrets.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class AccessCredentialStub {
    private final AccessKey accessKey;
    private final Long lastUsed;
    private final Long createdAt;
    private final String lastAction;

    public AccessCredentialStub(
            @JsonProperty("accessKey") AccessKey accessKey,
            @JsonProperty("lastUsed") Long lastUsed,
            @JsonProperty("createdAt") Long createdAt,
            @JsonProperty("lastAction") String lastAction) {
        this.accessKey = accessKey;
        this.lastUsed = lastUsed;
        this.lastAction = lastAction;
        this.createdAt = createdAt;
    }

    public AccessKey getAccessKey() {
        return accessKey;
    }

    public Long getLastUsed() {
        return lastUsed;
    }

    public String getLastAction() {
        return lastAction;
    }

    public Long getCreatedAt() {
        return createdAt;
    }
}
