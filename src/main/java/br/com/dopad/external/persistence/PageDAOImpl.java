package br.com.dopad.external.persistence;

import br.com.dopad.domain.entities.line.Line;
import br.com.dopad.domain.entities.page.Page;
import br.com.dopad.domain.entities.page.PageStatus;
import br.com.dopad.domain.entities.util.JsonUtil;
import br.com.dopad.usecases.page.gateway.PageDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class PageDAOImpl implements PageDAO {
    private final JdbcTemplate jdbcTemplate;
    private final JsonUtil jsonUtil;
    private final ObjectMapper objectMapper;

    public PageDAOImpl(JdbcTemplate jdbcTemplate, JsonUtil jsonUtil, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.jsonUtil = jsonUtil;
        this.objectMapper = objectMapper;
    }

    @Value("${queries.sql.page-dao.insert.page}")
    private String insertPageQuery;

    @Value("${queries.sql.page-dao.select.page-by-id}")
    private String selectPageByIdQuery;

    @Transactional
    @Override
    public Page savePage(Page page) {
        UUID pageId = UUID.randomUUID();
        jdbcTemplate.update(insertPageQuery, pageId,  page.getOwnerId(), page.getTitle(),
                jsonUtil.writeListAsJsonArray(page.getLines()), PageStatus.SENT.name(),
                page.getChangeCode(), page.getUploadDate(), page.isPrivate());
        return page.getNewInstanceWithId(pageId);
    }

    @Override
    public Optional<Page> findById(UUID id) {
        try {
            Page page = jdbcTemplate.queryForObject(selectPageByIdQuery, this::mapperPageFromRs, id);
            return Optional.of(page);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Page mapperPageFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID ownerId = (UUID) rs.getObject("owner_id");
        String title = rs.getString("title");
        PageStatus status = PageStatus.valueOf(rs.getString("status"));
        String changeCode = rs.getString("change_code");
        boolean isPrivate = rs.getBoolean("is_private");
        Timestamp uploadDate = rs.getTimestamp("upload_date");
        List<Line> lines = jsonUtil.readListFromJsonArray(rs.getString("text"), Line.class);

        return Page.createFull(id, ownerId, title, lines, status, changeCode, isPrivate, uploadDate, null);
    }

}
