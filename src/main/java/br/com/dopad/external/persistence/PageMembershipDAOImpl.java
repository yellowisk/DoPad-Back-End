package br.com.dopad.external.persistence;

import br.com.dopad.domain.entities.page.PageMembership;
import br.com.dopad.domain.entities.page.PageMembershipStatus;
import br.com.dopad.usecases.membership.gateway.PageMembershipDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class PageMembershipDAOImpl implements PageMembershipDAO {

    private final JdbcTemplate jdbcTemplate;

    public PageMembershipDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.page-membership-dao.insert.page-membership}")
    private String insertPageMembershipQuery;

    @Value("${queries.sql.page-membership-dao.select.page-membership-by-page-id-and-user-id}")
    private String selectPageMembershipByPageIdAndUserIdAndQuery;

    @Value("${queries.sql.page-membership-dao.select.page-membership-by-page-id}")
    private String selectPageMembershipByPageIdQuery;

    @Transactional
    @Override
    public PageMembership saveMembership(PageMembership membership) {
        UUID membershipId = UUID.randomUUID();
        jdbcTemplate.update(insertPageMembershipQuery, membershipId, membership.getPageId(),
                membership.getUserId(), membership.getStatus().toString());
        return membership.getNewInstanceWithId(membershipId);
    }

    @Override
    public PageMembership findByPageIdAndUserId(PageMembership membership) {
        return jdbcTemplate.queryForObject(selectPageMembershipByPageIdAndUserIdAndQuery,
                this::mapperPageMembershipFromRs, membership.getPageId(), membership.getUserId());
    }

    @Override
    public List<PageMembership> findAllByPageId(PageMembership membership) {
        return jdbcTemplate.query(selectPageMembershipByPageIdQuery, this::mapperPageMembershipFromRs, membership.getPageId());
    }

    public PageMembership mapperPageMembershipFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID pageId = (UUID) rs.getObject("page_id");
        UUID userId = (UUID) rs.getObject("user_id");
        PageMembershipStatus status = PageMembershipStatus.valueOf(rs.getString("status"));
        return PageMembership.createFull(id, pageId, userId, status);
    }

}
