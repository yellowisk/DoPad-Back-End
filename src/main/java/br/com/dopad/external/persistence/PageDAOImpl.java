package br.com.dopad.external.persistence;

import br.com.dopad.domain.entities.page.Page;
import br.com.dopad.usecases.page.gateway.PageDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PageDAOImpl implements PageDAO {
    private final JdbcTemplate jdbcTemplate;

    public PageDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page savePage(Page page) {
        return Page.createFull(page.getPageId(), page.getOwnerId(), page.getTitle(),
                page.getStatus(), page.getChangeCode(), page.isPrivate(), page.getMembers());
    }
}
