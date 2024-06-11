package com.techelevator.model;

public class Vote {

    private int userId;
    private int issueId;

    private int voteID;

    private String issueName;

    public String getIssueDescription() {
        return issueDescription;
    }

    private String issueDescription;


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



    public int getVoteID() {
        return voteID;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
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


    public void setSelectedOptionText(String selectedOptionText) {
    }

    public void setIssueDescription(String issueDescription) {
    }
}
