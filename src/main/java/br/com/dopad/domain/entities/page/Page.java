package br.com.dopad.domain.entities.page;

import br.com.dopad.domain.entities.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Setter
@Getter
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
}
