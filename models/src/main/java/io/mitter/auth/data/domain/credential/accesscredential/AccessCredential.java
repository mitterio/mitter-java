package io.mitter.auth.data.domain.credential.accesscredential;

import io.mitter.data.domain.application.Application;

/**
 * Access credentials for {@link Application}s
 *
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
public class AccessCredential {
    private AccessKey accessKey;
    private AccessSecret accessSecret;
    private Long createdAt;

    public AccessKey getAccessKey() {
        return accessKey;
    }

    public AccessCredential setAccessKey(AccessKey accessKey) {
        this.accessKey = accessKey;
        return this;
    }

    public AccessSecret getAccessSecret() {
        return accessSecret;
    }

    public AccessCredential setAccessSecret(AccessSecret accessSecret) {
        this.accessSecret = accessSecret;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessCredential that = (AccessCredential) o;

        if (getAccessKey() != null ? !getAccessKey().equals(that.getAccessKey()) : that.getAccessKey() != null)
            return false;
        return getAccessSecret() != null ? getAccessSecret().equals(that.getAccessSecret()) : that.getAccessSecret() == null;

    }

    @Override
    public int hashCode() {
        int result = getAccessKey() != null ? getAccessKey().hashCode() : 0;
        result = 31 * result + (getAccessSecret() != null ? getAccessSecret().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccessCredential{" +
                "accessKey=" + accessKey +
                ", accessSecret=" + accessSecret +
                '}';
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public AccessCredential setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
