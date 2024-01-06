package br.com.dopad.usecases.membership.gateway;

import br.com.dopad.domain.entities.page.PageMembership;

import java.util.*;

public interface PageMembershipDAO {
    PageMembership saveMembership(PageMembership membership);
    PageMembership findById(UUID id);
    PageMembership findByPageIdAndUserId(PageMembership membership);
    List<PageMembership> findAllByPageId(UUID pageId);
    PageMembership changeStatus(PageMembership membership);
    PageMembership removeMembership(PageMembership membership);
}
