<template>
  <div id="capstone-app">
    <div id="topnav">
      <a href="#"><img src="../assets/RankItWhiteNoBG.png" alt="Logo" id="logo"></a>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'createissue' }">Create Issue</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'login' }">Sign up or Log In</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>
<style scoped>
#topnav {
  overflow: hidden;
  background-color: #020301;
}
#topnav a {
  float: left;
  display: block;
  color: #E4E4E4;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}
#topnav a:hover {
  background-color: #020301;
  color: #8FA9B1;
}
#topnav a.active {
  background-color: #E4E4E4;
  color: 8FA9B1;
}
</style>
