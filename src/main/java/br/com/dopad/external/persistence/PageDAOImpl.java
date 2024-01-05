package br.com.dopad.external.persistence;

import br.com.dopad.domain.entities.line.Line;
import br.com.dopad.domain.entities.page.Page;
import br.com.dopad.domain.entities.page.PageStatus;
import br.com.dopad.domain.entities.user.User;
import br.com.dopad.domain.entities.util.JsonUtil;
import br.com.dopad.usecases.page.gateway.PageDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PageDAOImpl implements PageDAO {
    private final JdbcTemplate jdbcTemplate;
    private final JsonUtil jsonUtil;

    public PageDAOImpl(JdbcTemplate jdbcTemplate, JsonUtil jsonUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.jsonUtil = jsonUtil;
    }

    @Value("${queries.sql.page-dao.insert.page}")
    private String insertPageQuery;

    @Value("${queries.sql.page-dao.select.page-by-id}")
    private String selectPageByIdQuery;

    @Override
    public Page savePage(Page page) {
        UUID pageId = UUID.randomUUID();
        jdbcTemplate.update(insertPageQuery, pageId,  page.getOwnerId(), page.getTitle(), null,
                PageStatus.SENT, page.getChangeCode(), page.isPrivate(), page.getUploadDate());
        return page.getNewInstanceWithId(pageId);
    }

    @Override
    public Optional<Page> getPageById(UUID id) {
        try {
            Page page = jdbcTemplate.queryForObject(selectPageByIdQuery, this::mapperPageFromRs, id);
            return Optional.of(page);
        } catch (Exception e) {
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

        List<Line> content = Collections.singletonList(Line.createInstanceFromList(
                Arrays.stream(rs.getString("text").split(",")).map(String::trim)
                        .collect(Collectors.toList())));
        List<Map<String, Object>> jsonContentList = content.stream()
                .map(Line::toMap)
                .collect(Collectors.toList());
        String text = jsonUtil.writeListAsJsonArray(jsonContentList);

        return Page.createForResultSet(id, ownerId, title, text, status, changeCode, isPrivate, uploadDate);
    }

}
