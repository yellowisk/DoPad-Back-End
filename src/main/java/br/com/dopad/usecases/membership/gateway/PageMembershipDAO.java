package br.com.dopad.usecases.membership.gateway;

import br.com.dopad.domain.entities.page.PageMembership;

import java.util.*;

public interface PageMembershipDAO {
    PageMembership saveMembership(PageMembership membership);
    PageMembership findByPageIdAndUserId(PageMembership membership);
    List<PageMembership> findAllByPageId(PageMembership membership);
}
