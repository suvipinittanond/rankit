<template>
  <div id='create' class='container'>
    <form v-on:submit.prevent="createIssue" class='form'>
      <h1>Issue Management Form</h1>
      <div role="alert" v-if="creationErrors">
        {{ creationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="name">Issue ID</label>
        <input type="text" id="id" style='width:190px' v-model="issue.id" placeholder=' leave blank if creating an issue'/>
      </div>
      <div class="form-input-group">
        <label for="name">Name * </label>
        <input type="text" id="name"  v-model="issue.name" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="description">Description *</label>
        <input type="description" id="description" style='height:40px'  v-model="issue.description" required />
      </div>
      <div class="form-input-group">
        <label for="start_time">Start Time *</label>
        <input type="datetime-local" id="start_time" v-model="issue.start_time" required />
      </div>
      <div class="form-input-group">
        <label for="end_time">End Time *</label>
        <input type="datetime-local" id="end_time" v-model="issue.end_time" required />
      </div>
      <div class="form-input-group">
        <label for="option1">Option 1 *</label>
        <input type="text" id="option1"  v-model="issue.option1" required />
      </div>
      <div class="form-input-group">
        <label for="option2">Option 2 *</label>
        <input type="text" id="option2" v-model="issue.option2" required />
      </div>
      <div class="form-input-group">
        <label for="option3">Option 3</label>
        <input type="text" id="option3" v-model="issue.option3" />
      </div>
      <div class="form-input-group">
        <label for="option4">Option 4</label>
        <input type="text" id="option4"  v-model="issue.option4" />
      </div>
      <div class='button-group'>
      <button type="submit">Create</button>
      <button type="button" v-on:click="updateIssue">Update</button>
      <button type="button" v-on:click="confirmDelete">Delete</button>
      <button type="button" v-on:click="resetForm">Reset Form</button>
      </div>
    </form>
  </div>
</template>
<script>
import IssueService from '../services/IssueService';
export default {
  computed: {
    isAdmin() {
      return this.$store.state.user.authorities[0].name === 'ADMIN_USER';
    }
  },
  data() {
    return {
      issue: {
        id: '',
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
  mounted() {
    this.loadIssueData();
  },
  methods: {
    loadIssueData() {
      const issueId = this.$route.params.issueId;
      IssueService.getIssue(issueId)
      .then((response) => {
        this.issue = response.data;
      })
    },
    createIssue() {
      IssueService.createIssue(this.issue)
        .then((response) => {
          if (response.status === 201) {
            this.$router.push({
              path: '/createissue',
              query: { registration: 'success' },
            });
            this.resetForm();
          }
        })
        .catch((error) => {
          const response = error.response;
          this.creationErrors = true;
          if (response.status === 400) {
            this.creationErrorMsg = 'Bad Request: Validation Errors';
          }
        });
    },
  updateIssue() {
  const issueId = this.issue.id;
  IssueService.updateIssue(issueId, this.issue)
    .then((response) => {
      if (response.status === 200) {
        this.$router.push({
          path: '/createissue',
          query: { registration: 'success' },
        });
        this.resetForm();
      }
    })
    .catch((error) => {
      const response = error.response;
      this.creationErrors = true;
      if (response.status === 400) {
        this.creationErrorMsg = 'Bad Request: Validation Errors';
      }
    });
},
    deleteIssue(issue) {
      const issueId = this.issue.id;
      IssueService.deleteIssue(issueId, this.issue)
        .then((response) => {
          if (response.status === 201) {
            this.$router.push({
              path: '/createissue',
              query: { registration: 'success' },
            });
            this.resetForm();
          }
        })
        .catch((error) => {
          const response = error.response;
          this.creationErrors = true;
          if (response.status === 400) {
            this.creationErrorMsg = 'Bad Request: Validation Errors';
          }
        });
    },
    confirmDelete() {
      if (confirm('Are you sure you want to delete this issue?')) {
        this.deleteIssue();
      }
    },
    resetForm() {
      this.issue = {
        name: '',
        description: '',
        start_time: '',
        end_time: '',
        option1: '',
        option2: '',
        option3: '',
        option4: '',
      };
      this.creationErrors = false;
      this.creationErrorMsg = 'There were problems creating the issue.';
    }
  }
};
</script>
<style scoped>.container {
  text-align: center;
  padding-bottom: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #E4E4E4;
  height: 100vh;
  font-family: Arial, Helvetica, sans-serif;
}

.form {
  background-color: #c9c9c6;
  box-shadow: 0 2px 10px #020301;
  padding: 20px;
  width: 100%;
  max-width: 500px;
  border-radius: 25px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-input-group {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  margin-bottom: 15px;
}

label {
  flex: 0 0 120px;
  text-align: left;
  margin-right: 5px;
}

input {
  flex: 1;
  padding: 4px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>