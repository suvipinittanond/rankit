import axios from "axios";

export default {

    createIssue(issue) {

      return axios.post('/createissue', issue);
    
    },

    getIssues() {

      return axios.get('/getissue');
    
    },

    submitVote(issueId, selectedOption) {
      let voteDTO = {
        issueId : issueId,
        selectedOption: selectedOption,
        userId: 1
      }
      return axios.post('/vote', voteDTO);
    }

}