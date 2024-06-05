package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Vote;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import com.techelevator.model.VoteDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcVoteDao implements VoteDao{


    private final JdbcTemplate template;

    public JdbcVoteDao(JdbcTemplate template) {
        this.template = template;
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
        String voteSql = "INSERT INTO votes (user_id, id, selected_option) VALUES (?, ?, ?) RETURNING vote_id ";
        try {
            int newVoteId = template.queryForObject(voteSql, int.class, voteDTO.getUserId(), voteDTO.getIssueId(), voteDTO.getSelectedOption());
            return getVoteById(newVoteId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }
    }

    @Override
    public List<Integer> getSelectedOptionsByIssueId(int issueId) {
        String sql = "SELECT selected_option FROM votes WHERE id = ?";
        List<Integer> selectedOptions = new ArrayList<>();

        try {
            SqlRowSet rs = template.queryForRowSet(sql, issueId);
            while (rs.next()) {
                int selectedOption = rs.getInt("selected_option");
                selectedOptions.add(selectedOption);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server", e);
        }

        return selectedOptions;
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
        vote.setVoteID(rs.getInt("vote_id"));
        vote.setSelectedOption(rs.getInt("selected_option"));
        vote.setUserId(rs.getInt("user_id"));
        vote.setIssueId(rs.getInt("id"));
        return vote;
    }


}
