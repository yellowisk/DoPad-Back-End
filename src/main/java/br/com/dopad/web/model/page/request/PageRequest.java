package br.com.dopad.web.model.page.request;

import br.com.dopad.domain.entities.page.Page;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class PageRequest {
    private UUID ownerId;
    private String title;
    private Set<LineDTO> lines;
    private boolean isPrivate;

    public PageRequest() {
    }

    public PageRequest(UUID ownerId, String title, Set<LineDTO> lines, boolean isPrivate) {
        this.ownerId = ownerId;
        this.title = title;
        this.lines = lines;
        this.isPrivate = isPrivate;
    }

    public Page toPage() {
        return new Page(ownerId, title, lines.stream()
                .map(LineDTO::convertToLine)
                .collect(Collectors.toList()), isPrivate);
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

    public Set<LineDTO> getLines() {
        return lines;
    }

    public void setLines(Set<LineDTO> lines) {
        this.lines = lines;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
