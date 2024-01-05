package br.com.dopad.usecases.page;

import br.com.dopad.domain.entities.page.Page;
import br.com.dopad.domain.entities.page.PageMembershipStatus;
import br.com.dopad.domain.entities.page.PageStatus;
import br.com.dopad.domain.entities.user.User;
import br.com.dopad.usecases.membership.PageMembershipCRUD;
import br.com.dopad.usecases.page.gateway.PageDAO;
import br.com.dopad.usecases.user.UserCRUD;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PageCRUDImpl implements PageCRUD {
    private final PageDAO pageDAO;
    private final PageMembershipCRUD pageMembershipCRUD;
    private final UserCRUD userCRUD;

    public PageCRUDImpl(PageDAO pageDAO, PageMembershipCRUD pageMembershipCRUD, UserCRUD userCRUD) {
        this.pageDAO = pageDAO;
        this.pageMembershipCRUD = pageMembershipCRUD;
        this.userCRUD = userCRUD;
    }

    @Override
    public Page addPage(UUID ownerId, String title, boolean isPrivate) {
        ArrayList<User> members = new ArrayList<>();
        members.add(userCRUD.getById(ownerId));

        Page newPage = Page.createForDB(ownerId, title, PageStatus.SENT, Page.generateChangeCode(title),
                isPrivate, Timestamp.valueOf(LocalDateTime.now()), members);
        Page page = pageDAO.savePage(newPage);

        pageMembershipCRUD.addMembership(page.getId(), ownerId, PageMembershipStatus.ACCEPTED);

        return page;
    }

    @Override
    public Page getPageById(UUID id) {
        return pageDAO.getPageById(id).get();
    }
}
