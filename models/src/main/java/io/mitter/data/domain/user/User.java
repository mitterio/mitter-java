package io.mitter.data.domain.user;

import com.google.common.collect.Lists;
import io.mitter.data.domain.annotations.Identifier;
import io.mitter.data.domain.annotations.MitterDomainEntity;
import io.mitter.data.domain.entity.EntityMetadata;
import io.mitter.data.domain.entity.EntityProfile;
import io.mitter.data.domain.entity.MetadataAttachable;
import io.mitter.data.domain.user.locators.UserLocator;
import io.mitter.models.central.entityprofile.ProfileAttachable;
import io.mitter.models.commons.AuditInfo;
import io.mitter.models.commons.Auditable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Models a user.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class User implements MitterDomainEntity<User>, ProfileAttachable, Auditable {
    /**
     * A publically addressable id for the user
     */
    private String userId;

    /**
     * An id used internally by mitter
     */
    private String internalId;
    /**
     * A list of locators that can be used to locate this user
     */
    private List<? extends UserLocator> userLocators;
    /**
     * The current set screen name of the user. Inserting objects may not have this unset
     */
    private ScreenName screenName;

    private boolean systemUser;

    private boolean synthetic = false;

    private EntityProfile entityProfile = null;

    private EntityMetadata entityMetadata = null;

    private AuditInfo auditInfo;

    private boolean blocked = false;

    @Identifier
    public String getUserId() {
        return userId;
    }

    public String getInternalId() {
        return internalId;
    }

    public User setInternalId(String internalId) {
        this.internalId = internalId;
        return this;
    }

    public User() {
        this.userLocators = Lists.newArrayList();
    }

    public List<? extends UserLocator> getUserLocators() {
        return userLocators;
    }

    public User setUserLocators(List<? extends UserLocator> userLocators) {
        this.userLocators = userLocators;
        return this;
    }

    public ScreenName getScreenName() {
        return screenName;
    }

    public User setScreenName(ScreenName screenName) {
        this.screenName = screenName;
        return this;
    }

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public boolean isSystemUser() {
        return systemUser;
    }

    public void setSystemUser(boolean systemUser) {
        this.systemUser = systemUser;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public Class<? extends User> type() {
        return this.getClass();
    }

    @Override
    public String domainId() {
        return getUserId();
    }

    @Nullable
    @Override
    public String internalId() {
        return getInternalId();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userLocators=" + userLocators +
                ", screenName=" + screenName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (userLocators != null ? !userLocators.equals(user.userLocators) : user.userLocators != null) return false;
        return screenName != null ? screenName.equals(user.screenName) : user.screenName == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userLocators != null ? userLocators.hashCode() : 0);
        result = 31 * result + (screenName != null ? screenName.hashCode() : 0);
        return result;
    }

    public boolean isSynthetic() {
        return synthetic;
    }

    public void setSynthetic(boolean synthetic) {
        this.synthetic = synthetic;
    }

    @Nullable
    @Override
    public EntityProfile getEntityProfile() {
        return entityProfile;
    }

    @Override
    public void setEntityProfile(@Nullable EntityProfile entityProfile) {
        this.entityProfile = entityProfile;
    }

    @NotNull
    @Override
    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }

    @Nullable
    @Override
    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    @Override
    public void setAuditInfo(@Nullable AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }
}
