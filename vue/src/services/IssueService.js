import axios from "axios";

export default {

    createIssue(issue) {

      return axios.post('/createissue', issue);
    
    },

    getIssues() {

      return axios.get('/getissue');
    
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

      return axios.get(`/getvote/${issueId}`);
    }

}