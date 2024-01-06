package br.com.dopad.domain.entities.page;

import java.util.UUID;

public class PageMembership {
    private UUID id;
    private UUID pageId;
    private UUID userId;

    private PageMembershipStatus status;

    public PageMembership(UUID id, UUID pageId, UUID userId, PageMembershipStatus status) {
        this.id = id;
        this.pageId = pageId;
        this.userId = userId;
        this.status = status;
    }

    public PageMembership(UUID pageId, UUID userId, PageMembershipStatus status) {
        this.pageId = pageId;
        this.userId = userId;
        this.status = status;
    }

    public PageMembership(UUID pageId) {
        this.pageId = pageId;
    }

    public PageMembership getNewInstanceWithId(UUID id) {
        return new PageMembership(id, pageId, userId, status);
    }

    public static PageMembership createFull(UUID id, UUID pageId, UUID userId, PageMembershipStatus status) {
        return new PageMembership(id, pageId, userId, status);
    }

    public static PageMembership createForDB(UUID pageId, UUID userId, PageMembershipStatus status) {
        return new PageMembership(pageId, userId, status);
    }

    public static PageMembership createWithOnlyPageId(UUID pageId) {
        return new PageMembership(pageId);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPageId() {
        return pageId;
    }

    public void setPageId(UUID pageId) {
        this.pageId = pageId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public PageMembershipStatus getStatus() {
        return status;
    }

    public void setStatus(PageMembershipStatus status) {
        this.status = status;
    }
}
