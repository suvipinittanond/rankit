package com.techelevator.model;

public class Vote {

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    private int userId;
    private int issueId;

    private int voteID;

    public int getVoteID() {
        return voteID;
    }

    public void setVoteID(int voteID) {
        this.voteID = voteID;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }

    private int selectedOption;

    public Vote() {}

    public void setVoteId(int voteId) {
    }

    

    public void setIssueName(String issueName) {
    }

    public void setSelectedOptionText(String selectedOptionText) {
    }
}
