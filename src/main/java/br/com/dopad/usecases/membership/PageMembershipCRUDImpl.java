package br.com.dopad.usecases.membership;

import br.com.dopad.domain.entities.page.PageMembership;
import br.com.dopad.domain.entities.page.PageMembershipStatus;
import br.com.dopad.usecases.membership.gateway.PageMembershipDAO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PageMembershipCRUDImpl implements PageMembershipCRUD {
    private final PageMembershipDAO pageMembershipDAO;
    public PageMembershipCRUDImpl(PageMembershipDAO pageMembershipDAO) {
        this.pageMembershipDAO = pageMembershipDAO;
    }

    @Override
    public PageMembership addMembership(UUID userId, UUID pageId, PageMembershipStatus status) {
        PageMembership pageMembership = PageMembership.createForDB(userId, pageId, status);
        return pageMembershipDAO.saveMembership(pageMembership);
    }

    @Override
    public PageMembership getById(UUID membershipId) {
        return pageMembershipDAO.findById(membershipId);
    }

    @Override
    public PageMembership getByUserAndPage(UUID userId, UUID pageId) {
        PageMembership pageMembership = PageMembership.createForDB(userId, pageId, PageMembershipStatus.PENDING);
        return pageMembershipDAO.findByPageIdAndUserId(pageMembership);
    }

    @Override
    public List<PageMembership> getAllFromPage(UUID pageId) {
        return pageMembershipDAO.findAllByPageId(pageId);
    }

    @Override
    public PageMembership updateMembership(UUID membershipId, String status) {
        PageMembership pageMembership = PageMembership.createWithIdAndStatus(membershipId, PageMembershipStatus.valueOf(status));
        return pageMembershipDAO.changeStatus(pageMembership);
    }

    @Override
    public PageMembership deleteById(UUID membershipId) {
        return pageMembershipDAO.removeMembership(getById(membershipId));
    }
}
