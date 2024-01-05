package br.com.dopad.usecases.membership;

import br.com.dopad.domain.entities.page.PageMembership;
import br.com.dopad.domain.entities.page.PageMembershipStatus;
import br.com.dopad.usecases.membership.gateway.PageMembershipDAO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PageMembershipCRUDImpl implements PageMembershipCRUD {
    private final PageMembershipDAO pageMembershipDAO;

    public PageMembershipCRUDImpl(PageMembershipDAO pageMembershipDAO) {
        this.pageMembershipDAO = pageMembershipDAO;
    }

    @Override
    public PageMembership addMembership(UUID userId, UUID pageId, PageMembershipStatus status) {
        PageMembership pageMembership = PageMembership.createForDB(userId, pageId, status);
        return pageMembershipDAO.addMembership(pageMembership);
    }

    @Override
    public PageMembership getMembership(UUID userId, UUID pageId) {
        PageMembership pageMembership = PageMembership.createForDB(userId, pageId, PageMembershipStatus.PENDING);
        return pageMembershipDAO.getMembership(pageMembership);
    }
}
