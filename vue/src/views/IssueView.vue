<template>
<div class='issues'>
  <h1>Issues</h1>
  <ul class='issue-list'>
      <li v-for="issue in issues" :key="issue.id">
        <div class="issue-item">
          <h3>{{ issue.name }}</h3>
          <p>{{ issue.description }}</p>
          <p>{{ issue.option1 }}</p>
          <p>{{ issue.option2 }}</p>
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
      issues: []
    }
  },
  created() {
    IssueService.getIssues()
    .then(
      (response) => {
        this.issues = response.data;
      });
  }
};
</script>

<style>
.issue-list {
  display: flex;
  justify-content: center;
  align-items: center;
  list-style: none;
  padding: 0;
}

.issue-item {
  
  background-color: #c9c9c6;
  height: 400px;
  width: 350px;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
  text-align: center; 
  
}
</style>
