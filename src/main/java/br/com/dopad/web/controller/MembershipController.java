package br.com.dopad.web.controller;

import br.com.dopad.domain.entities.page.PageMembership;
import br.com.dopad.domain.entities.page.PageMembershipStatus;
import br.com.dopad.usecases.membership.PageMembershipCRUD;
import br.com.dopad.web.model.membership.response.PageMembershipResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/memberships")
public class MembershipController {
    private PageMembershipCRUD pageMembershipCRUD;

    public MembershipController(PageMembershipCRUD pageMembershipCRUD) {
        this.pageMembershipCRUD = pageMembershipCRUD;
    }

    @PostMapping("/add")
    public ResponseEntity<PageMembershipResponse> addMembership(@PathVariable UUID pageId,
                                                                @PathVariable UUID userId) {
        return ResponseEntity.ok(PageMembershipResponse.fromPageMembership(pageMembershipCRUD
                .addMembership(pageId, userId, PageMembershipStatus.PENDING)));
    }

    @GetMapping("/{membershipId}")
    public ResponseEntity<PageMembershipResponse> getMembershipById(@PathVariable UUID membershipId) {
        return ResponseEntity.ok(PageMembershipResponse.fromPageMembership(pageMembershipCRUD.getById(membershipId)));
    }

    @GetMapping("/page/{pageId}/user/{userId}")
    public ResponseEntity<PageMembershipResponse> getMembershipByUserAndPage(@PathVariable UUID pageId,
                                                                             @PathVariable UUID userId) {
        return ResponseEntity.ok(PageMembershipResponse.fromPageMembership(pageMembershipCRUD.getByUserAndPage(userId, pageId)));
    }

    @GetMapping("/page/{pageId}")
    public ResponseEntity<List<PageMembershipResponse>> getAllMembershipsFromPage(@PathVariable UUID pageId) {
        List<PageMembership> memberships = pageMembershipCRUD.getAllFromPage(pageId);
        return ResponseEntity.ok(memberships.stream().map(PageMembershipResponse::fromPageMembership).toList());
    }

    @PutMapping("/{membershipId}/status/{status}")
    public ResponseEntity<PageMembershipResponse> updateMembership(@PathVariable UUID membershipId,
                                                                   @PathVariable String status) {
        return ResponseEntity.ok(PageMembershipResponse
                .fromPageMembership(pageMembershipCRUD.updateMembership(membershipId, status)));
    }

    @DeleteMapping("/{membershipId}")
    public ResponseEntity<PageMembershipResponse> deleteMembership(@PathVariable UUID membershipId) {
        return ResponseEntity.ok(PageMembershipResponse
                .fromPageMembership(pageMembershipCRUD.deleteById(membershipId)));
    }

}
