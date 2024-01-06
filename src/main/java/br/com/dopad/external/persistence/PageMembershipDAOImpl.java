package br.com.dopad.external.persistence;

import br.com.dopad.domain.entities.page.PageMembership;
import br.com.dopad.domain.entities.page.PageMembershipStatus;
import br.com.dopad.usecases.membership.gateway.PageMembershipDAO;
import br.com.dopad.web.exception.GenericResourceException;
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

    @Value("${queries.sql.page-membership-dao.select.page-membership-by-id}")
    private String selectPageMembershipByIdQuery;

    @Value("${queries.sql.page-membership-dao.select.page-membership-by-page-id-and-user-id}")
    private String selectPageMembershipByPageIdAndUserIdAndQuery;

    @Value("${queries.sql.page-membership-dao.select.page-membership-by-page-id}")
    private String selectPageMembershipByPageIdQuery;

    @Value("${queries.sql.page-membership-dao.update.page-membership-status-by-id}")
    private String updatePageMembershipStatusByIdQuery;

    @Value("${queries.sql.page-membership-dao.delete.page-membership-by-id}")
    private String deletePageMembershipByIdQuery;

    @Transactional
    @Override
    public PageMembership saveMembership(PageMembership membership) {
        UUID membershipId = UUID.randomUUID();
        jdbcTemplate.update(insertPageMembershipQuery, membershipId, membership.getPageId(),
                membership.getUserId(), membership.getStatus().toString());
        return membership.getNewInstanceWithId(membershipId);
    }

    @Override
    public PageMembership findById(UUID membershipId) {
        return jdbcTemplate.queryForObject(selectPageMembershipByIdQuery,
                this::mapperPageMembershipFromRs, membershipId);
    }

    @Override
    public PageMembership findByPageIdAndUserId(PageMembership membership) {
        return jdbcTemplate.queryForObject(selectPageMembershipByPageIdAndUserIdAndQuery,
                this::mapperPageMembershipFromRs, membership.getPageId(), membership.getUserId());
    }

    @Override
    public List<PageMembership> findAllByPageId(UUID pageId) {
        return jdbcTemplate.query(selectPageMembershipByPageIdQuery, this::mapperPageMembershipFromRs, pageId);
    }

    @Override
    public PageMembership changeStatus(PageMembership membership) {
        jdbcTemplate.update(updatePageMembershipStatusByIdQuery, membership.getStatus().name(), membership.getId());
        return membership;
    }

    @Transactional
    @Override
    public PageMembership removeMembership(PageMembership membership) {
        if(jdbcTemplate.update(deletePageMembershipByIdQuery, membership.getId()) != 1) {
            throw new GenericResourceException("Unexpected error when trying to delete membership with id "+membership.getId(), "Exclusion error");
        }
        return membership;
    }

    public PageMembership mapperPageMembershipFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID pageId = (UUID) rs.getObject("page_id");
        UUID userId = (UUID) rs.getObject("user_id");
        PageMembershipStatus status = PageMembershipStatus.valueOf(rs.getString("status"));
        return PageMembership.createFull(id, pageId, userId, status);
    }

}
