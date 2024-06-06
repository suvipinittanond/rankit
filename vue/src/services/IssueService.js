import axios from "axios";

export default {

    createIssue(issue) {

      return axios.post('/createissue', issue);
    
    },

    getIssues() {

      return axios.get('/getissue');
    
    },

    submitVote(voteDTO) {
      return axios.post('/vote', voteDTO);
    }

}