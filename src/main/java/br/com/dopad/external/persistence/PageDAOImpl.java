package br.com.dopad.external.persistence;

import br.com.dopad.domain.entities.page.Page;
import br.com.dopad.domain.entities.page.PageStatus;
import br.com.dopad.domain.entities.user.User;
import br.com.dopad.usecases.page.gateway.PageDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PageDAOImpl implements PageDAO {
    private final JdbcTemplate jdbcTemplate;

    public PageDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.page-dao.insert.page}")
    private String insertPageQuery;
    
    @Value("${queries.sql.page-membership-dao.insert.page-membership}")
    private String insertPageMembershipQuery;

    @Override
    public Page savePage(Page page) {
        UUID pageId = UUID.randomUUID();
        jdbcTemplate.update(insertPageMembershipQuery, pageId, page.getOwnerId());
        jdbcTemplate.update(insertPageQuery, pageId,  page.getOwnerId(), page.getTitle(), null,
                PageStatus.SENT, page.getChangeCode(), page.isPrivate(), page.getUploadDate());
        return page.getNewInstanceWithId(pageId);
    }
}
