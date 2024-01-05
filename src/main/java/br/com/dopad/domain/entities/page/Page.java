package br.com.dopad.domain.entities.page;

import br.com.dopad.domain.entities.user.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class Page {
    private UUID id;
    private UUID ownerId;
    private String title;
    private String text;
    private PageStatus status;
    private String changeCode;
    private boolean isPrivate;
    private Timestamp uploadDate;
    private List<User> members;

    public Page(UUID id, UUID ownerId, String title, String text, PageStatus status,
                String changeCode, boolean isPrivate, Timestamp uploadDate, List<User> members) {
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.text = text;
        this.status = status;
        this.changeCode = changeCode;
        this.isPrivate = isPrivate;
        this.uploadDate = uploadDate;
        this.members = members;
    }

    public Page(UUID ownerId, String title, PageStatus status, String changeCode, boolean isPrivate, Timestamp uploadDate) {
        this.ownerId = ownerId;
        this.title = title;
        this.status = status;
        this.changeCode = changeCode;
        this.isPrivate = isPrivate;
        this.uploadDate = uploadDate;
    }

    public Page(UUID ownerId, String title, PageStatus status, String changeCode, boolean isPrivate, Timestamp uploadDate, ArrayList<User> members) {
        this.ownerId = ownerId;
        this.title = title;
        this.status = status;
        this.changeCode = changeCode;
        this.isPrivate = isPrivate;
        this.uploadDate = uploadDate;
        this.members = members;
    }

    public static Page createForDB(UUID ownerId, String title, PageStatus status, String changeCode,
                                   boolean isPrivate, Timestamp uploadDate, ArrayList<User> members) {
        return new Page(ownerId, title, status, changeCode, isPrivate, uploadDate, members);
    }

    public static Page createFull(UUID id, UUID ownerId, String title, String text,
                                  PageStatus status, String changeCode, boolean isPrivate,
                                  Timestamp uploadDate, List<User> members) {
        return new Page(id, ownerId, title, text, status, changeCode, isPrivate, uploadDate, members);
    }

    public static Page createForResultSet(UUID id, UUID ownerId, String title, String text,
                                          PageStatus status, String changeCode, boolean isPrivate,
                                          Timestamp uploadDate) {
        return new Page(id, ownerId, title, text, status, changeCode, isPrivate, uploadDate, null);
    }

    public Page getNewInstanceWithId(UUID id) {
        return new Page(id, ownerId, title, text, status, changeCode, isPrivate, uploadDate, members);
    }

    public static String generateChangeCode(String title) {
        try {
            String inputString = title + System.currentTimeMillis();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(inputString.getBytes(StandardCharsets.UTF_8));
            String encodedHash = Base64.getEncoder().encodeToString(hashBytes);
            return encodedHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
