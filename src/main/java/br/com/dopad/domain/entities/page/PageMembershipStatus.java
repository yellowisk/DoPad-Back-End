package br.com.dopad.domain.entities.page;

public enum PageMembershipStatus {
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    private String pageMembershipStatus;

    PageMembershipStatus(String pageMembershipStatus) {
        this.pageMembershipStatus = pageMembershipStatus;
    }

    @Override
    public String toString() {
        return pageMembershipStatus;
    }
}
