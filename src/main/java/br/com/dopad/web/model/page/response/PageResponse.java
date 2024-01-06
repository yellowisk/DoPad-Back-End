package br.com.dopad.web.model.page.response;

import br.com.dopad.domain.entities.line.Line;
import br.com.dopad.domain.entities.page.Page;
import br.com.dopad.domain.entities.page.PageStatus;
import br.com.dopad.web.model.user.response.UserResponse;

import java.sql.Timestamp;
import java.util.*;

public class PageResponse {
    private UUID id;
    private UUID ownerId;
    private String title;
    private List<Line> lines;
    private PageStatus status;
    private String changeCode;
    private boolean isPrivate;
    private Timestamp uploadDate;
    private List<UserResponse> members;

    public PageResponse(UUID id, UUID ownerId, String title, List<Line> lines, PageStatus status, String changeCode, boolean isPrivate, Timestamp uploadDate, List<UserResponse> members) {
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.lines = lines;
        this.status = status;
        this.changeCode = changeCode;
        this.isPrivate = isPrivate;
        this.uploadDate = uploadDate;
        this.members = members;
    }

    public static PageResponse fromPage(Page page) {

        return new PageResponse(page.getId(), page.getOwnerId(), page.getTitle(), page.getLines(),
                page.getStatus(), page.getChangeCode(), page.isPrivate(), page.getUploadDate(),
                page.getMembers().stream().map(UserResponse::fromUser).toList());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
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

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    public List<UserResponse> getMembers() {
        return members;
    }

    public void setMembers(List<UserResponse> members) {
        this.members = members;
    }
}
