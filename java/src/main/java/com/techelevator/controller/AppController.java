package com.techelevator.controller;

import com.techelevator.dao.IssueDAO;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Issue;
import com.techelevator.model.IssueDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class AppController {

    private IssueDAO issueDAO;

    public AppController (IssueDAO issueDAO) {
        this.issueDAO = issueDAO;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/createissue", method = RequestMethod.POST)
    public void createIssue(@RequestBody IssueDTO newIssue) {
        try {
            if (issueDAO.getIssueByName(newIssue.getName()) !=null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Issue already exists");
               } else {
                issueDAO.createIssue(newIssue);
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Issue Registration has failed");
        }
    }
}
