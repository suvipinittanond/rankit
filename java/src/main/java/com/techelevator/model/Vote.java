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

    private String issueOption1;
    private String issueOption2;
    private String issueOption3;
    private String issueOption4;

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    private String optionName;

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getIssueOption1() {
        return issueOption1;
    }

    public void setIssueOption1(String issueOption1) {
        this.issueOption1 = issueOption1;
    }

    public String getIssueOption2() {
        return issueOption2;
    }

    public void setIssueOption2(String issueOption2) {
        this.issueOption2 = issueOption2;
    }

    public String getIssueOption3() {
        return issueOption3;
    }

    public void setIssueOption3(String issueOption3) {
        this.issueOption3 = issueOption3;
    }

    public String getIssueOption4() {
        return issueOption4;
    }

    public void setIssueOption4(String issueOption4) {
        this.issueOption4 = issueOption4;
    }

    public void setSelectedOptionText(String selectedOptionText) {
    }


}
