package com.techelevator.dao;

import com.techelevator.model.Issue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.List;

public class IssueJdbcDAO implements IssueDAO {
    private JdbcTemplate template;

    public IssueJdbcDAO(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    private Issue mapRowToIssue(SqlRowSet rowSet) {
        Issue issue = new Issue();

        issue.setId(rowSet.getInt("id"));
        issue.setName(rowSet.getString("name"));
        issue.setDescription(rowSet.getString("description"));
        issue.setStartTime(rowSet.getTimestamp("start_time").toLocalDateTime());
        issue.setEndTime(rowSet.getTimestamp("end_time").toLocalDateTime());

        return issue;
    }


    public List<Issue> getIssue() {
        return null;

    }
}
