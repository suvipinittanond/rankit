import axios from "axios";
export default {
  createIssue(issue) {
    return axios.post('/createissue', issue);
  },
  getIssues() {
    return axios.get('/getissue');
  },

  // Get a specific issue by ID
  getIssue(id) {
    return axios.get(`/getissue/${id}`);
  },
  updateIssue(issueId, issue) {
    return axios.put(`/updateissue/${issueId}`, issue); // Pass the issue id in the URL
  },
    
  deleteIssue(issueId, issue) {
    return axios.delete(`/deleteissue/${issueId}`, issue); 
  },

  submitVote(issueId, selectedOption, userId) { 
      let voteDTO = {
      issueId : issueId,
      selectedOption: selectedOption,
      userId: userId
    }
    return axios.post('/vote', voteDTO);
  },

    getResults(issueId) {
      return axios.get(`getvote/${issueId}`);
    },

    getIssuesByGroupId(groupId) {
      return axios.get(`/group/${groupId}`);
    },
    getGroups() {
      return axios.get('/groups');
    },
    getVotes(userId) {
      return axios.get(`/votes/user/${userId}`);
    },
  }