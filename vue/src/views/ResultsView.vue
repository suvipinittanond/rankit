<template>
    <div class = "results">
      <h1>Results</h1>
      <ul class="result-list">
  
          <h4>Issue Id #{{ issueId }} </h4>
          <h4>Vote Count: {{ results.voteCount }} </h4>
          <h4>Selected Option: {{ results.selectedOption }} </h4>
     
      </ul>
        
    </div>

  </template>
  
  <script>
import IssueService from '../services/IssueService';

  export default {
    created() {
            IssueService.getResults(this.issueId)
            .then(response => {
            this.results = response.data
          
         })
    },
    data() {
      return {
        results: {}, 
        issueId : 1
        
      };
    },

    methods: {

    }
  };
  </script>
  
  <style scoped>
  .results {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-family: Arial, Helvetica, sans-serif
}
  
  </style>
  