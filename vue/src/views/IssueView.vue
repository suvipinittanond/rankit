<template>
<div class='issues'>
  <h1>Issues</h1>
  <p>{{ issues }}</p>
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
    IssueService.getIssues
    .then(
      (response) => {
        this.issues = response.data;
      });
  }
};
</script>

<style>

</style>
