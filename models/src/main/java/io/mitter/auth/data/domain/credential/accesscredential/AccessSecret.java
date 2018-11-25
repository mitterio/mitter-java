package io.mitter.auth.data.domain.credential.accesscredential;

import java.util.Arrays;

/**
 * Representation of an access secret
 *
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
public class AccessSecret {
    private byte[] secret;

    public byte[] getSecret() {
        return secret;
    }

    public AccessSecret setSecret(byte[] secret) {
        this.secret = secret;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessSecret that = (AccessSecret) o;

        return Arrays.equals(getSecret(), that.getSecret());

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getSecret());
    }
}
