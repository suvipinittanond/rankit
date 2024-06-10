<template>
  <div class="issues">
    <h1>Active Issues</h1>
    <ul class="issue-list">
      <li v-for="issue in issues" :key="issue.id">
        <div class="issue-item">
          <h3 @click="toggleIssue(issue.id)">{{ issue.name }}</h3>
          <div v-if="!issue.minimized">
            <p>{{ issue.description }}</p>
            <form @submit.prevent="submitVote(issue.id)" class="vote-form">
              <div class="options">
                <label class="option" v-if="issue.option1">
                  <input type="radio" v-model="selectedOption.issueID" value="1" class="radio-input">
                  <span class="option-text">{{ issue.option1 }}</span>
                </label>
                <label class="option" v-if="issue.option2">
                  <input type="radio" v-model="selectedOption.issueID" value="2" class="radio-input">
                  <span class="option-text">{{ issue.option2 }}</span>
                </label>
                <label class="option" v-if="issue.option3">
                  <input type="radio" v-model="selectedOption.issueID" value="3" class="radio-input">
                  <span class="option-text">{{ issue.option3 }}</span>
                </label>
                <label class="option" v-if="issue.option4">
                  <input type="radio" v-model="selectedOption.issueID" value="4" class="radio-input">
                  <span class="option-text">{{ issue.option4 }}</span>
                </label>
              </div>
              <button type="submit" class="submit-button">Submit Vote</button>
            </form>
            <div v-if="getResult(issue.id)">
              <h4>Results:</h4>
              <p v-for="(result, index) in getResult(issue.id)" :key="index">
                Option {{ index + 1 }}: {{ result }} votes
              </p>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import IssueService from '../services/IssueService.js';

export default {
  data() {
    return {
      issues: [],
      selectedOption: { issueID: 0 },
      results: []
    };
  },
  created() {
    this.loadIssues();
  },
  methods: {
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
    submitVote(issueId) {
      const selectedOption = this.selectedOption.issueID;
      const userId = this.$store.state.user.id;
      IssueService.submitVote(issueId, selectedOption, userId)
        .then(() => {
          console.log('Vote submitted successfully');
          this.showResults(issueId);
        })
        .catch(error => {
          console.error('Error submitting vote:', error);
          alert('Failed to submit vote. Please try again later.');
        });
    },
    toggleIssue(issueId) {
      const issue = this.issues.find(issue => issue.id === issueId);
      if (issue) {
        issue.minimized = !issue.minimized;
      }
    },
    showResults(issueId) {
      IssueService.getResults(issueId)
        .then(response => {
          const resultIndex = this.results.findIndex(result => result.issueId === issueId);
          if (resultIndex !== -1) {
            this.results[resultIndex] = { issueId, data: response.data };
          } else {
            this.results.push({ issueId, data: response.data });
          }
        })
        .catch(error => {
          console.error('Error loading results:', error);
        });
    },
    getResult(issueId) {
      const result = this.results.find(result => result.issueId === issueId);
      return result ? result.data : null;
    }
  }
};
</script>

<style scoped>
.issues {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-family: Arial, Helvetica, sans-serif;
  
}


.issue-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  list-style: none;
  padding: 0;
  width: 100%;
}

.issue-item {
  background-color: #C9C9C6;
  height: auto;
  width: 350px;
  padding: 40px;
  margin-top: 30px;
  margin-bottom: 30px;
  border-radius: 50px;
  text-align: left;
  box-shadow: 0 5px 25px #63747A;

}
h3 {
  color: black;
}
h3:hover{
  color: black;
  text-shadow: 0 0 3px lightblue, 0 0 5px lightblue;
 
  
}

.vote-form {
  display: flex;
  flex-direction: column;
  align-items: left;
}

.options {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}


.option {
  margin-bottom: 10px;
}

.radio-input {
  margin-right: 5px;
}

.submit-button {
  background-color: #FFDB15;
  color: #020301;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin-top: 20px;
  border-radius: 10px;
  cursor: pointer;
}

.submit-button:hover {

  background-color: #8FA9B1;

}

.option-text {
  font-size: 16px;
}

.issue-item h3 {
  cursor: pointer;
}
</style>
