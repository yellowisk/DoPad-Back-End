package br.com.dopad.web.model.membership.response;

import br.com.dopad.domain.entities.page.PageMembership;

import java.util.UUID;

public class PageMembershipResponse {
    private UUID id;
    private UUID pageId;
    private UUID userId;
    private String status;

    public PageMembershipResponse(UUID id, UUID pageId, UUID userId, String status) {
        this.id = id;
        this.pageId = pageId;
        this.userId = userId;
        this.status = status;
    }

    public static PageMembershipResponse fromPageMembership(PageMembership membership) {
        return new PageMembershipResponse(membership.getId(), membership.getPageId(),
                membership.getUserId(), membership.getStatus().name());
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
