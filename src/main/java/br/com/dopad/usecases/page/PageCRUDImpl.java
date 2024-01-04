package br.com.dopad.usecases.page;

import br.com.dopad.domain.entities.page.Page;
import br.com.dopad.domain.entities.page.PageStatus;
import br.com.dopad.domain.entities.user.User;
import br.com.dopad.usecases.page.gateway.PageDAO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class PageCRUDImpl implements PageCRUD {
    private final PageDAO pageDAO;

    public PageCRUDImpl(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }

    @Override
    public Page addPage(UUID ownerId, String title, boolean isPrivate) {
//        ArrayList<User> members = new ArrayList<>();
        Page page = Page.createForDB(ownerId, title, PageStatus.SENT, Page.generateChangeCode(title),
                isPrivate, Timestamp.valueOf(LocalDateTime.now()));
        return pageDAO.savePage(page);
    }
}
