<template>
    <div class = "results">
      <h1>Results for Issue #1 </h1>
      <ul class="result-list">
    
      
           
           Selected Option:  {{ results.selectedOption}} <br>
           Vote Count: {{ results.voteCount}}
     
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
  