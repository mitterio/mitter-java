package io.mitter.auth.data.domain.credential.accesscredential;

/**
 * Representation of an access key
 *
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
public class AccessKey {
    private String key;

    public String getKey() {
        return key;
    }

    public AccessKey setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessKey accessKey = (AccessKey) o;

        return getKey() != null ? getKey().equals(accessKey.getKey()) : accessKey.getKey() == null;

    }

    @Override
    public int hashCode() {
        return getKey() != null ? getKey().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AccessKey{" +
                "key='" + key + '\'' +
                '}';
    }
}
