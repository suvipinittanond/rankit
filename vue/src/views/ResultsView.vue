<template>
    <div class = "results">

        
      <h1>
        Results
      </h1>
      <ul class="result-list">
    
        
           Vote Count: {{ results.voteCount}} :
           Selected Option:  {{ results.selectedOption}}
     
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
  
  </style>
  