<template>
    <div class="explore"> <!-- Add the explore class to the main div -->
      <h1>Explore Issues:</h1>
      <div>
        <!-- Selectable groups dropdown -->
        <select v-model="selectedGroup" @change="loadIssuesByGroup">
          <option value="">Select a group</option>
          <option v-for="group in groups" :key="group.id" :value="group.id">{{ group.name }}</option>
        </select>
      </div>
      <div v-if="selectedGroup">
        <!-- Display issues for the selected group -->
        <ul>
          <li v-for="issue in issues" :key="issue.id">{{ issue.name }}</li>
        </ul>
      </div>
    </div>
  </template>
  
  <script>
  import IssueService from '../services/IssueService';
  
  export default {
    data() {
      return {
        groups: [], // Array to store selectable groups
        selectedGroup: '', // Name of the selected group
        issues: [] // Array to store issues for the selected group
      };
    },
    created() {
      this.loadGroups(); // Load selectable groups when the component is created
    },
    methods: {
      loadGroups() {
        // Fetch selectable groups from the backend
        IssueService.getGroups()
          .then(response => {
            this.groups = response.data;
          })
          .catch(error => {
            console.error('Error loading groups:', error);
          });
      },
      loadIssuesByGroup() {
        // Fetch issues for the selected group from the backend
        IssueService.getGroupsByGroupName(this.selectedGroup)
          .then(response => {
            this.issues = response.data;
          })
          .catch(error => {
            console.error('Error loading issues by group:', error);
          });
      }
    }
  };
</script>
  