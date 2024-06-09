package com.techelevator.controller;

import com.techelevator.dao.IssueDAO;
import com.techelevator.dao.VoteDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Issue;
import com.techelevator.model.IssueDTO;
import com.techelevator.model.Vote;
import com.techelevator.model.VoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AppController {

    private IssueDAO issueDAO;
    private VoteDao voteDAO;

    public AppController(IssueDAO issueDAO, VoteDao voteDAO) {
        this.issueDAO = issueDAO;
        this.voteDAO = voteDAO;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/getvote/{issueId}", method = RequestMethod.GET)
    public Map<Integer, Integer> getSelectedOptionsByIssueId(@PathVariable int issueId) {
        Map<Integer, Integer> voteCounts = voteDAO.getSelectedOptionsByIssueId(issueId);

        if (!voteCounts.isEmpty()) {
            return voteCounts;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No votes found for the specified issue");
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/group/{groupId}", method = RequestMethod.GET)
    public List<Issue> getIssuesByGroupId(@PathVariable String groupId) {
        List<Issue> issues = issueDAO.getIssuesByGroupId(groupId);
        if (!issues.isEmpty()) {
            return issues;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/vote", method = RequestMethod.POST)
    public Vote createVote(@RequestBody VoteDTO voteDTO) {
                   //check if they already voted?
           if (voteDAO.hasVoteByUserAndIssue(voteDTO.getUserId(), voteDTO.getIssueId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User has already voted");
            }
            Vote createdVoted = voteDAO.createVote(voteDTO);
            return createdVoted;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/createissue", method = RequestMethod.POST)
    public void createIssue(@RequestBody IssueDTO newIssue) {
        try {
            if (issueDAO.getIssueByName(newIssue.getName()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Issue already exists");
            } else {
                issueDAO.createIssue(newIssue);
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Issue Registration has failed");
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/updateissue/{id}", method = RequestMethod.PUT)
    public void updateIssue (@PathVariable int id, @RequestBody IssueDTO issueDTO){
        issueDTO.setId(id);
        try {
            Issue updatedIssue = issueDAO.updateIssue(issueDTO);
            if (updatedIssue == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Issue Update has failed");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/deleteissue/{id}", method = RequestMethod.DELETE)
    public void deleteIssue(@PathVariable int id) {
             try {
            Issue issueDelete = issueDAO.getIssueById(id);
            if (issueDelete == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                issueDAO.deleteIssue(id);
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Issue Delete has failed");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/getissue", method = RequestMethod.GET)
    public List<Issue> getIssue() {
       return issueDAO.getIssue();
    }
}