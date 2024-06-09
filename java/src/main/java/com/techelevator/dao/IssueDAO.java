package com.techelevator.dao;

import com.techelevator.model.Issue;
import com.techelevator.model.IssueDTO;

import java.util.List;

public interface IssueDAO {
    List<Issue> getIssuesByGroupId(String groupId);

    Issue getIssueById(int issueId);

    List<Issue> getIssue();

    Issue getIssueByName(String name);

    Issue createIssue(IssueDTO issue);


    Issue updateIssue(IssueDTO issue);

    Issue deleteIssue(int IssueId);
}
