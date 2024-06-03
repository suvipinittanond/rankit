package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Issue;
import com.techelevator.model.IssueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Component
public class IssueJdbcDAO implements IssueDAO {
    private final JdbcTemplate template;

    public IssueJdbcDAO(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Issue getIssueById(int issueId) {
        Issue issue = null;
        String sql = "SELECT issue_id, name, description, start_time, end_time FROM issue WHERE issue_id = ?";
        try {
            SqlRowSet rs = template.queryForRowSet(sql, issueId);
            if (rs.next()) {
                issue = mapRowToIssue(rs);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }
        return issue;
    }

    @Override
    public List<Issue> getIssue() {
        List<Issue> issues = new ArrayList<>();
        String sql = "SELECT issue_id, name, description, start_time, end_time FROM issue";
        try {
            SqlRowSet rs = template.queryForRowSet(sql);
            while (rs.next()) {
                Issue issue = mapRowToIssue(rs);
                issues.add(issue);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }
        return issues;
    }

    @Override
    public Issue getIssueByName(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        Issue issue = null;
        String sql = "SELECT issue_id, name, description, start_time, end_time FROM issue WHERE name = LOWER(TRIM(?))";
        try {
            SqlRowSet rs = template.queryForRowSet(sql, name);
            if (rs.next()) {
                issue = mapRowToIssue(rs);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }
        return issue;
    }

    @Override
    public Issue createIssue(IssueDTO issue) {
        Issue newIssue = null;
        String insertIssueSql = "INSERT INTO issue (name, description, start_time, end_time) values (?, ?, ?, ?) RETURNING issue_id";
        try {
            int newIssueId = template.queryForObject(insertIssueSql, int.class, issue.getName(), issue.getDescription(), issue.getStart_time(), issue.getEnd_time());
            newIssue = getIssueById(newIssueId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }
        return newIssue;
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



}
