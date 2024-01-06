package br.com.dopad.usecases.page;

import br.com.dopad.domain.entities.page.Page;
import br.com.dopad.domain.entities.page.PageMembershipStatus;
import br.com.dopad.domain.entities.page.PageStatus;
import br.com.dopad.domain.entities.user.User;
import br.com.dopad.usecases.membership.PageMembershipCRUD;
import br.com.dopad.usecases.page.gateway.PageDAO;
import br.com.dopad.usecases.user.UserCRUD;
import br.com.dopad.web.model.page.request.PageRequest;
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
    public Page addPage(PageRequest request) {
        ArrayList<User> members = new ArrayList<>();
        Page page = request.toPage();

        members.add(userCRUD.getById(page.getOwnerId()));
        Page pageFromDB = pageDAO.savePage(Page.createForDB(page.getOwnerId(), page.getTitle(),
                page.getLines(), PageStatus.SENT, Page.generateChangeCode(page.getTitle()),
                page.isPrivate(), Timestamp.valueOf(LocalDateTime.now()), members));

        pageMembershipCRUD.addMembership(pageFromDB.getId(), pageFromDB.getOwnerId(), PageMembershipStatus.ACCEPTED);

        return pageFromDB;
    }

    @Override
    public Page getPageById(UUID id) {
        Page page = pageDAO.findById(id).get();
        page.setMembers(pageMembershipCRUD.getAllFromPage(page.getId())
                .stream().map(membership -> userCRUD.getById(membership.getUserId())).toList());
        return page;
    }
}
