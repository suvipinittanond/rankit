<template>
  <div id='create'>
    <form v-on:submit.prevent="createIssue">
      <h1>Create An Issue</h1>
      <div role="alert" v-if="creationErrors">
        {{ creationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="name">name</label>
        <input type="text" id="name" v-model="issue.name" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="description">Description</label>
        <input type="description" id="description" v-model="issue.description" required />
      </div>
      <div class="form-input-group">
        <label for="start_time">Start Time</label>
        <input type="start_time" id="start_time" v-model="issue.start_time" required />
      </div>
      <div class="form-input-group">
        <label for="end_time">End Time</label>
        <input type="end_time" id="end_time" v-model="issue.end_time" required />
      </div>
      <div class="form-input-group">
        <label for="option1">option1</label>
        <input type="text" id="option1" v-model="issue.option1" required />
      </div>
      <div class="form-input-group">
        <label for="option2">option2</label>
        <input type="text" id="option2" v-model="issue.option2" required />
      </div>
      <div class="form-input-group">
        <label for="option3">option3</label>
        <input type="text" id="option3" v-model="issue.option3" />
      </div>
      <div class="form-input-group">
        <label for="option4">option4</label>
        <input type="text" id="option4" v-model="issue.option4" />
      </div>
      <button type="submit">Create Issue</button>
    </form>

  </div>
</template>

<script>

import IssueService from '../services/IssueService';

export default {
    data() {
        return {
            issue: {
                name: '',
                description: '',
                start_time: '',
                end_time: '',
                option1: '',
                option2: '',
                option3: '',
                option4: '',
            },
            creationErrors: false,
            creationErrorMsg: 'There were problems creating the issue.'
        };
    },
    methods: {
        createIssue() {
            IssueService
            .createIssue(this.issue)
            .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/createissue',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.creationErrors = true;
            if (response.status === 400) {
              this.creationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
        }
    }
}
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>