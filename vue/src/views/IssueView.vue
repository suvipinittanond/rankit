<template>
  <div class="issues">
    <h1>Issues</h1>
    <ul class="issue-list">
      <li v-for="issue in issues" :key="issue.id">
        <div class="issue-item">
          <h3 @click="toggleIssue(issue.id)">{{ issue.name }}</h3>
          <div v-if="!issue.minimized">
            <p>{{ issue.description }}</p>
            <form @submit.prevent="submitVote(issue.id)" class="vote-form">
              <div class="options">
                <label class="option">
                  <input type="radio" v-model="selectedOption[issue.id]" value="1" class="radio-input">
                  <span class="option-text">{{ issue.option1 }}</span>
                </label>
                <label class="option">
                  <input type="radio" v-model="selectedOption[issue.id]" value="2" class="radio-input">
                  <span class="option-text">{{ issue.option2 }}</span>
                </label>
              </div>
              <button type="submit" class="submit-button">Submit Vote</button>
            </form>
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
      selectedOption: {}
    }
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
      const selectedOption = this.selectedOption[issueId];
      IssueService.submitVote(issueId, selectedOption)
        .then(() => {
          console.log('Vote submitted successfully');
          this.loadIssues();
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
    }
  }
};
</script>

<style scoped>
.issues {
  display: flex;
  flex-direction: column;
  align-items: center;
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
  text-align: center;
}

.vote-form {
  display: flex;
  flex-direction: column;
  align-items: center;
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
  background-color: #45a049;
}

.option-text {
  font-size: 16px;
}

.issue-item h3 {
  cursor: pointer;
}
</style>
