<template>
    <div>
  <h1>My Votes:</h1>
   

    </div>

  </template>
  
  <script>
import IssueService from '../services/IssueService';

export default {
  data() {
    return {
      votes: [],

    };
  },
  methods: {
    loadVotes(){
      IssueService.getVotes()
      .then(response => {
        this.votes = response.data.map(votes => ({
          ...votes
        }))
      })
    }
  }
}

  </script>
  
  
  <style scoped>
  </style>
  