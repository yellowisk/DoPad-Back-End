package br.com.dopad.usecases.membership;

import br.com.dopad.domain.entities.page.PageMembership;
import br.com.dopad.domain.entities.page.PageMembershipStatus;

import java.util.*;

public interface PageMembershipCRUD {
    PageMembership addMembership(UUID userId, UUID pageId, PageMembershipStatus status);
    PageMembership getMembershipByUserAndPage(UUID userId, UUID pageId);
    List<PageMembership> getAllFromPage(UUID pageId);
}
