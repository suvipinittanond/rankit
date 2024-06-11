<template>
    <div class='container'>
  <h1>My Votes</h1>
  <ul class="vote-list">
      <li v-for="vote in votes" :key="vote.userId">
        <div class="vote-item">
          <h2>{{ vote.issueName }}</h2>
          <p>{{ vote.issueDescription }}</p>
          <p>You voted for: {{ vote.selectedOption }}</p>
        </div>
      </li>
  </ul>
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
  mounted() {
    this.loadVotes();
  },
  methods: {
    loadVotes(){
      const userId = this.$store.state.user.id;
      IssueService.getVotes(userId)
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

  .container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh; 
  background-color: #E4E4E4;
  }
h1 {
  color: black;
  font-family: Arial, Helvetica, sans-serif;
}
h2 {
  color: black;
  font-family: Arial, Helvetica, sans-serif;
}
  .vote-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  list-style: none;
  padding: 0;
  width: 100%;
  font-family: Arial, Helvetica, sans-serif;
}

.vote-item {
  background-color: #C9C9C9;
  height: auto;
  width: 350px;
  padding: 40px;
  margin-top: 30px;
  margin-bottom: 30px;
  border-radius: 50px;
  text-align: left;
  box-shadow: 0 5px 25px #63747A;
  position: relative;
}
  </style>
  