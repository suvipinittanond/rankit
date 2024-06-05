package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Issue;
import com.techelevator.model.IssueDTO;
import com.techelevator.model.Vote;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
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
        String sql = "SELECT * FROM issue WHERE id = ?";
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
        String sql = "SELECT name, description, start_time, end_time FROM issue";
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
        String sql = "SELECT * FROM issue WHERE name = LOWER(TRIM(?))";
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
        String insertIssueSql = "INSERT INTO issue (name, description, start_time, end_time, option1, option2, option3, option4) values (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try {
            int newIssueId = template.queryForObject(insertIssueSql, int.class, issue.getName(), issue.getDescription(),
                    issue.getStart_time(), issue.getEnd_time(), issue.getOption1(), issue.getOption2(), issue.getOption3(), issue.getOption4());
            newIssue = getIssueById(newIssueId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }
        return newIssue;
    }

    @Override
    public Issue updateIssue(IssueDTO issue) {
        Issue updatedIssue = null;
        String updatedIssueSql = "UPDATE issue SET name = ?, description = ?, start_time = ?, end_time = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ? WHERE id = ?";
        try {

        int newUpdateId = template.update(updatedIssueSql, issue.getName(), issue.getDescription(), issue.getStart_time(), issue.getEnd_time(), issue.getOption1(), issue.getOption2(), issue.getOption3(), issue.getOption4(), issue.getId());

        if (newUpdateId > 0) {
            updatedIssue = getIssueById(issue.getId());
        }
        } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to server", e);
            }
        return updatedIssue;
    }

    @Override
    public Issue deleteIssue(int IssueId) {
        String deletesql = "DELETE FROM issue WHERE id = ?";
        try {
            int deleteID = template.update(deletesql, IssueId);
            if (deleteID == 0) {
                throw new DaoException("Issue ID not found " + IssueId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }
        return null;
    }




    private Issue mapRowToIssue(SqlRowSet rowSet) {
        Issue issue = new Issue();
        issue.setId(rowSet.getInt("id"));
        issue.setName(rowSet.getString("name"));
        issue.setDescription(rowSet.getString("description"));
        issue.setStartTime(rowSet.getString("start_time"));
        issue.setEndTime(rowSet.getString("end_time"));
        issue.setOption1(rowSet.getString("option1"));
        issue.setOption2(rowSet.getString("option2"));
        issue.setOption3(rowSet.getString("option3"));
        issue.setOption4(rowSet.getString("option4"));
        return issue;
    }



}
