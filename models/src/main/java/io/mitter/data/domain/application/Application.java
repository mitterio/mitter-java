package io.mitter.data.domain.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mitter.data.domain.annotations.IdentifiableEntity;
import io.mitter.data.domain.annotations.Identifier;
import io.mitter.data.domain.entity.EntityMetadata;
import io.mitter.models.acolyte.AclEntity;
import io.mitter.models.acolyte.AppliedAcl;
import io.mitter.models.acolyte.AppliedAclList;
import io.mitter.models.commons.AuditInfo;
import io.mitter.models.commons.Auditable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vedavyas Bhat (vedavyas@mitter.io)
 */
public class Application implements IdentifiableEntity<Application>, AclEntity<Application>, Auditable {
    private String applicationId;
    private String internalId;
    private String name;
    private Boolean sandboxed;
    private AppliedAclList appliedAcls = new AppliedAclList(
            Collections.<AppliedAcl>emptyList(), Collections.<AppliedAcl>emptyList());
    private AuditInfo auditInfo;
    private List<String> labels = new ArrayList<>();
    private EntityMetadata entityMetadata = null;

    @Identifier
    public String getApplicationId() {
        return applicationId;
    }

    @JsonIgnore
    public String getInternalId() {
        return internalId;
    }

    public String getName() {
        return name;
    }

    public Application setApplicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public Application setInternalId(String internalId) {
        this.internalId = internalId;
        return this;
    }

    public Application setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getSandboxed() {
        return sandboxed;
    }

    public Application setSandboxed(Boolean sandboxed) {
        this.sandboxed = sandboxed;
        return this;
    }

    @Override
    public Class<? extends Application> type() {
        return this.getClass();
    }

    @NotNull
    @Override
    public String domainId() {
        return getApplicationId();
    }

    @Nullable
    @Override
    public String internalId() {
        return getInternalId();
    }

    @NotNull
    @Override
    public AppliedAclList getAppliedAcls() {
        return this.appliedAcls;
    }

    public void setAppliedAcls(@NotNull AppliedAclList appliedAclList) {
        this.appliedAcls = appliedAclList;
    }

    @NotNull
    @Override
    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    @Override
    public void setEntityMetadata(@NotNull EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId='" + applicationId + '\'' +
                ", name='" + name + '\'' +
                ", sandboxed=" + sandboxed +
                ", appliedAcls=" + appliedAcls +
                ", auditInfo=" + auditInfo +
                '}';
    }

    @Override
    public AuditInfo getAuditInfo() {
        return auditInfo;
    }

    @Override
    public void setAuditInfo(AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    public List<String> getLabels() {
        return labels;
    }

    public Application setLabels(List<String> labels) {
        this.labels = labels;
        return this;
    }
}
