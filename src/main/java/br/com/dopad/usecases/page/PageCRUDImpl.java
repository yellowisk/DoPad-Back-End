package br.com.dopad.usecases.page;

import br.com.dopad.domain.entities.page.Page;
import br.com.dopad.usecases.page.gateway.PageDAO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PageCRUDImpl implements PageCRUD {
    private final PageDAO pageDAO;

    public PageCRUDImpl(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }

    @Override
    public Page addPage(UUID ownerId, String title, boolean isPrivate) {
        Page page = Page.createWithOwnerTitleAndIsPrivate(ownerId, title, isPrivate);
        return pageDAO.savePage(page);
    }
}
