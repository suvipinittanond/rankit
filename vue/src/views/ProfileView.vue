<template>
  <div class='container'>
    <h1>My Votes</h1>
    <ul class="vote-list">
      <li v-for="vote in votes" :key="vote.userId">
        <div class="vote-item">
          <h2>{{ vote.issueName }}</h2>
          <p>{{ vote.issueDescription }}</p>
          <!-- Access vote.optionName here -->
          <p>You voted for: {{ vote.optionName }}</p>
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
      issues: [],
      userOption: null
    };
  },
  computed: {
  isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    }
  },
  mounted() {
    this.loadVotes();
    this.loadIssues();
    this.voteToIssue();
  },
  methods: {
    selectUserOption(vote) {
      this.userOption = vote.issueId;
    },
    loadVotes(){
      const userId = this.$store.state.user.id;
      IssueService.getVotes(userId)
      .then(response => {
        this.votes = response.data.map(votes => ({
          ...votes
        }))
      })
    },
    loadIssues() {
      IssueService.getIssues()
        .then(response => {
          this.issues = response.data.map(issue => ({
            ...issue,
            minimized: true
          }));
        })
        .catch(error => {
          console.error('Error loading issues:', error);
        });
    },
    async voteToIssue() {
        // Fetch votes data
        const votesResponse = await IssueService.getVotes(this.$store.state.user.id);
        const votesData = votesResponse.data;
        console.log("Votes data:", votesData);

        // Fetch issues data
        const issuesResponse = await IssueService.getIssues();
        const issuesData = issuesResponse.data;
        console.log("Issues data:", issuesData);

        // Iterate over each vote
        this.votes = votesData.map(vote => {
            // Find the corresponding issue
            const issue = issuesData.find(issue => issue.id === vote.id);

            // Determine the option name based on selected_option
            let optionName = "";
            switch (vote.selected_option) {
                case 1:
                    optionName = issue.option1;
                    break;
                case 2:
                    optionName = issue.option2;
                    break;
                case 3:
                    optionName = issue.option3;
                    break;
                case 4:
                    optionName = issue.option4;
                    break;
                default:
                    optionName = "Unknown Option";
            }

            // Assign the option name to the vote object
            return {
                ...vote,
                optionName: optionName
            };
        });
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
  