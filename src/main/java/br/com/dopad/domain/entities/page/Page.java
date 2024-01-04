package br.com.dopad.domain.entities.page;

import br.com.dopad.domain.entities.user.User;

import java.util.Map;
import java.util.UUID;

public class Page {
    private UUID pageId;
    private UUID ownerId;
    private String title;
    private PageStatus status;
    private String changeCode;
    private boolean isPrivate;
    private Map<User, Integer> members;

    public Page(UUID pageId, UUID ownerId, String title, PageStatus status, String changeCode, boolean isPrivate, Map<User, Integer> members) {
        this.pageId = pageId;
        this.ownerId = ownerId;
        this.title = title;
        this.status = status;
        this.changeCode = changeCode;
        this.isPrivate = isPrivate;
        this.members = members;
    }

    public Page(UUID ownerId, String title, boolean isPrivate) {
        this.ownerId = ownerId;
        this.title = title;
        this.isPrivate = isPrivate;
    }

    public static Page createWithOwnerTitleAndIsPrivate(UUID ownerId, String title, boolean isPrivate) {
        return new Page(ownerId, title, isPrivate);
    }

    public static Page createFull(UUID pageId, UUID ownerId, String title, PageStatus status, String changeCode, boolean isPrivate, Map<User, Integer> members) {
        return new Page(pageId, ownerId, title, status, changeCode, isPrivate, members);
    }

    public UUID getPageId() {
        return pageId;
    }

    public void setPageId(UUID pageId) {
        this.pageId = pageId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PageStatus getStatus() {
        return status;
    }

    public void setStatus(PageStatus status) {
        this.status = status;
    }

    public String getChangeCode() {
        return changeCode;
    }

    public void setChangeCode(String changeCode) {
        this.changeCode = changeCode;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Map<User, Integer> getMembers() {
        return members;
    }

    public void setMembers(Map<User, Integer> members) {
        this.members = members;
    }
}
