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
body {
  background-color: #E4E4E4;
}
h1 {
  font-size: 60px;
}

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
  padding: 0; width: 100%; 
} 
.issue-item { 
  background-color: #C9C9C6; 
  height: 400px; 
  width: 350px; 
  padding: 40px;
  margin-top: 30px; 
  margin-bottom: 30px; 
  border-radius: 50px; 
  text-align: center; 
} 

div {
  font-family: Arial, Helvetica, sans-serif;
}
</style>
