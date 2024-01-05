package br.com.dopad.usecases.membership.gateway;

import br.com.dopad.domain.entities.page.PageMembership;

public interface PageMembershipDAO {
    PageMembership addMembership(PageMembership membership);
    PageMembership getMembership(PageMembership membership);
}
