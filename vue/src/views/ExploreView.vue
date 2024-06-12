<template>
  <div class="explore">
    <h1>Explore Issues:</h1>
    <div>
      <select v-model="selectedGroup" @change="loadIssuesByGroup">
        <option value="">Select a group</option>
        <option v-for="group in groups" :key="group.groupId" :value="group.groupId">{{ group.groupId }}</option>
      </select>
    </div>
    <div v-if="selectedGroup">
      <h2>Issues for {{ selectedGroup }}</h2>
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
                <div class="timelimit"><strong>VOTING ENDS {{ formatEndTime(issue.endTime) }}</strong></div>
                <button type="submit" class="submit-button">Submit Vote</button>
                <div class='issue-number'>ID#: {{ issue.id }} </div>
                <div class ='issue-cat'>Category: {{  issue.groupId}}  </div>
             
              </form>
              <div v-if="getResult(issue.id)">
                <h4>Results:</h4>
                <div v-for="(votes, option) in getResult(issue.id)" :key="option" class="bar-graph">
                  <div class="bar" :style="{ width: (votes * 10) + 'px' }"></div>
                  <span class="option-text">{{ option }}: {{ votes }} votes</span>
                </div>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import IssueService from '../services/IssueService.js';

export default {
  data() {
    return {
      groups: [],
      selectedGroup: '',
      issues: [],
      selectedOption: { issueID: '' },
      results: []
    };
  },
  created() {
    this.loadGroups();
  },
  methods: {
    loadGroups() {
      IssueService.getIssues()
        .then(response => {
          const uniqueGroups = response.data.filter((group, index, self) =>
            index === self.findIndex((g) => g.groupId === group.groupId)
          );
          this.groups = uniqueGroups;
        })
        .catch(error => {
          console.error('Error loading groups:', error);
        });
    },
    loadIssuesByGroup() {
      IssueService.getIssuesByGroupId(this.selectedGroup)
        .then(response => {
          this.issues = response.data.map(issue => ({
            ...issue,
            minimized: true
          }));
        })
        .catch(error => {
          console.error('Error loading issues by group:', error);
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
          const newResult = { issueId, data: response.data };
          if (resultIndex !== -1) {
            this.$set(this.results, resultIndex, newResult);
          } else {
            this.results.push(newResult);
          }
        })
        .catch(error => {
          console.error('Error loading results:', error);
        });
    },
    getResult(issueId) {
      const result = this.results.find(result => result.issueId === issueId);
      return result ? result.data : null;
    },
    formatOption(issue, option) {
      return issue[option];
    },
    formatEndTime(endTime) {
      const date = new Date(Date.parse(endTime));
      return date.toLocaleString();
    }
  }
};
</script>

<style scoped>

.explore {
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
.issue-view {
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
  font-family: Arial, Helvetica, sans-serif;
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
  position: relative;
}

h2 {
  color: black;
  font-family: Arial, Helvetica, sans-serif;
}

h2:hover{
  color: black;
  text-shadow: 0 0 15px lightblue, 0 0 15px lightblue;
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

.issue-number {
  position: absolute;
  top: 15px;
  right: 25px;
  padding: 18px;
  border-radius: 5px;
}

.bar-graph {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.bar {
  height: 20px;
  background-color: #007BFF; /* Adjust color as needed */
  margin-right: 5px;
}

.option-text {
  margin-left: 10px;
  font-size: 14px;
}

h1 {
  font-family: Arial, Helvetica, sans-serif;
}

.timelimit {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-top: 20px;
}

.issue-cat {
  position: absolute;
  top: 15px;
  left: 25px;
  padding: 18px;
  border-radius: 5px;
}
</style>




  