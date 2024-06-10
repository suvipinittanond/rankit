package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.IssueTime;
import com.techelevator.model.IssueTimeMapper;
import com.techelevator.model.Vote;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.model.VoteDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcVoteDao implements VoteDao{


    private final JdbcTemplate template;

    public JdbcVoteDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Vote> getVotesByUserId(int userId) {
        String sql = "SELECT v.vote_id, v.user_id, v.id as issue_id, v.selected_option, i.name as issue_name, " +
                "CASE v.selected_option " +
                "    WHEN 1 THEN i.option1 " +
                "    WHEN 2 THEN i.option2 " +
                "    WHEN 3 THEN i.option3 " +
                "    WHEN 4 THEN i.option4 " +
                "END as selected_option_text " +
                "FROM votes v " +
                "JOIN issue i ON v.id = i.id " +
                "WHERE v.user_id = ?";
        List<Vote> votes = new ArrayList<>();
        SqlRowSet rs = template.queryForRowSet(sql, userId);
        while (rs.next()) {
            Vote vote = mapRowToVote(rs);
            votes.add(vote);
        }
        return votes;
    }


    @Override
    public Vote getVoteById(int voteId) {
        Vote vote = null;
        String sql = "SELECT * FROM votes WHERE id = ?";
        try {
            SqlRowSet rs = template.queryForRowSet(sql, voteId);
            if (rs.next()) {
                vote = mapRowToVote(rs);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }
        return vote;
    }

    @Override
    public Vote createVote(VoteDTO voteDTO) {
        int issueId = voteDTO.getIssueId();

        // Fetch start and end times for the current issue from the database
        String sql = "SELECT start_time, end_time FROM issue WHERE id = ?";
        IssueTime issueTime = template.queryForObject(sql, new Object[]{issueId}, new IssueTimeMapper());

        LocalDateTime votingStartTime = issueTime.getStartTime();
        LocalDateTime votingEndTime = issueTime.getEndTime();

        LocalDateTime currentTime = LocalDateTime.now();

        // Check if the current time is within the allowed voting period for the current issue
        if (currentTime.isBefore(votingStartTime) || currentTime.isAfter(votingEndTime)) {
            throw new IllegalStateException("Voting is not allowed for this issue at this time.");
        }

        // Proceed with inserting the vote into the database
        String voteSql = "INSERT INTO votes (user_id, id, selected_option) VALUES (?, ?, ?) RETURNING vote_id ";
        try {
            int newVoteId = template.queryForObject(voteSql, int.class, voteDTO.getUserId(), issueId, voteDTO.getSelectedOption());
            return getVoteById(newVoteId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }
    }

    @Override
    public Map<String, Integer> getSelectedOptionsByIssueId(int issueId) {
        String sql = "SELECT selected_option, COUNT(*) AS vote_count FROM votes WHERE id = ? GROUP BY selected_option";
        Map<String, Integer> voteCounts = new HashMap<>();

        try {
            SqlRowSet rs = template.queryForRowSet(sql, issueId);
            while (rs.next()) {
                int selectedOption = rs.getInt("selected_option");
                int count = rs.getInt("vote_count");
                voteCounts.put("option" + selectedOption, count); // Adjusting the key pattern
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }

        return voteCounts;
    }


    @Override
    public boolean hasVoteByUserAndIssue(int userId, int issueId) {
        String sql = "SELECT COUNT(*) FROM votes WHERE user_id = ? AND id = ?";
        // if the length of the results is greater than 0, then no action
        try {
            int count = template.queryForObject(sql, int.class, userId, issueId);
            return count > 0;
                // if we got to this line, then the person has voted before...
                // throw the Dao Exception, you can't vote twice, no return

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }
        // you want to give the controller the green light to process the vote

      }


    private Vote mapRowToVote(SqlRowSet rs) {
        Vote vote = new Vote();
        vote.setVoteId(rs.getInt("vote_id"));
        vote.setUserId(rs.getInt("user_id"));
        vote.setIssueId(rs.getInt("issue_id"));
        vote.setSelectedOption(rs.getInt("selected_option"));
        vote.setIssueName(rs.getString("issue_name"));
        vote.setSelectedOptionText(rs.getString("selected_option_text"));
        return vote;
    }

}
