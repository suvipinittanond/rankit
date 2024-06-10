package com.techelevator.dao;

import com.techelevator.model.Vote;
import com.techelevator.model.VoteDTO;

import java.util.List;
import java.util.Map;

public interface VoteDao {

    List<Vote> getVotesByUserId(int userId);

    Vote getVoteById(int voteId);

    Vote createVote(VoteDTO voteDTO);

    Map<String, Integer> getSelectedOptionsByIssueId(int issueId);

    boolean hasVoteByUserAndIssue(int userId, int issueId);
}
