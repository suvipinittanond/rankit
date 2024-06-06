<template>
  <div id="capstone-app">
    <div id="topnav">
      <a href="/">
        <img src="src/assets/RankItWhiteNoBG.png" alt="Logo" id="logo">
      </a>
      <div id="right-links">
        <!-- <router-link v-bind:to="{ name: 'createissue' }">Create Issue</router-link> -->
        <router-link v-bind:to="{ name: 'issues' }">Issues</router-link>
        
      </div>

      <div id="left-links">
        <router-link v-bind:to="{ name: 'login' }">Sign up or Log In</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>

    </div>
    <router-view />
  </div>
</template>

<style scoped>
#topnav {
  overflow: hidden;
  background-color: #020301;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#topnav a {
  color: #E4E4E4;
  text-decoration: none;
  padding: 14px 16px;
}

#topnav a:hover {
  background-color: #020301;
  color: #8FA9B1;
  text-decoration: underline;
}

#logo {
  height: 50px;
  margin-left: 175px; 
}


#left-links {
  margin-right: 200px;
}

</style>
