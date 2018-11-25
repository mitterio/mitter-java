package io.mitter.data.domain.annotations;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
public class EntityIdentifierHolder<T extends Identifiable<T>> implements Identifiable<T> {
    private String domainId;
    private String internalId;
    private Class<T> type;

    public EntityIdentifierHolder(String domainId, Class<T> type) {
        this.domainId = domainId;
        this.type = type;
    }

    @NotNull
    @Override
    public String domainId() {
        return domainId;
    }

    @Nullable
    @Override
    public String internalId() {
        return internalId;
    }

    @NotNull
    @Override
    public Class<? extends T> type() {
        return type;
    }

    public String getDomainId() {
        return domainId;
    }

    public EntityIdentifierHolder<T> setDomainId(String domainId) {
        this.domainId = domainId;
        return this;
    }

    public String getInternalId() {
        return internalId;
    }

    public EntityIdentifierHolder<T> setInternalId(String internalId) {
        this.internalId = internalId;
        return this;
    }

    @Override
    public String toString() {
        return "EntityIdentifierHolder{" +
                "domainId='" + domainId + '\'' +
                "internalId='" + internalId + '\'' +
                '}';
    }
}
