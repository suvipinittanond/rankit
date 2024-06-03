package com.techelevator.dao;

import com.techelevator.model.Issue;
import com.techelevator.model.IssueDTO;

import java.util.List;

public interface IssueDAO {
    Issue getIssueById(int issueId);

    List<Issue> getIssue();

    Issue getIssueByName(String name);

    Issue createIssue(IssueDTO issue);





}
