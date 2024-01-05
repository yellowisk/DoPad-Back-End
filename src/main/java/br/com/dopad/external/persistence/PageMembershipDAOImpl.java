package br.com.dopad.external.persistence;

import br.com.dopad.domain.entities.page.PageMembership;
import br.com.dopad.domain.entities.page.PageMembershipStatus;
import br.com.dopad.usecases.membership.gateway.PageMembershipDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class PageMembershipDAOImpl implements PageMembershipDAO {

    private final JdbcTemplate jdbcTemplate;

    public PageMembershipDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.page-membership-dao.insert.page-membership}")
    private String insertPageMembershipQuery;

    @Value("${queries.sql.page-membership-dao.select.page-membership-by-page-id-and-user-id}")
    private String selectPageMembershipByIdQuery;

    @Override
    public PageMembership addMembership(PageMembership membership) {
        UUID membershipId = UUID.randomUUID();
        jdbcTemplate.update(insertPageMembershipQuery, membershipId, membership.getPageId(),
                membership.getUserId(), membership.getStatus().toString());
        return membership.getNewInstanceWithId(membershipId);
    }

    @Override
    public PageMembership getMembership(PageMembership membership) {
        return jdbcTemplate.queryForObject(selectPageMembershipByIdQuery,
                this::mapperPageMembershipFromRs, membership.getPageId(), membership.getUserId());
    }

    public PageMembership mapperPageMembershipFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID pageId = (UUID) rs.getObject("page_id");
        UUID userId = (UUID) rs.getObject("user_id");
        PageMembershipStatus status = PageMembershipStatus.valueOf(rs.getString("status"));
        return PageMembership.createFull(id, pageId, userId, status);
    }

}
