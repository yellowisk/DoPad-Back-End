package br.com.dopad.usecases.page;

import br.com.dopad.domain.entities.page.Page;

import java.util.UUID;

public interface PageCRUD {
    Page addPage(UUID ownerId, String title, boolean isPrivate);

}
