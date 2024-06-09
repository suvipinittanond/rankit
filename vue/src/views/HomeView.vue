<template>
  <div class="home">
    <div class='welcome-container'>
      <h1>Welcome to Rank It</h1>
      <div class='description'>  
        <h3>
          At Rank It, we believe every voice matters. We are dedicated to providing a platform where individuals can express their opinions, 
          make informed decisions, and bring about real change. Your Voice. Your Choice.
        </h3>
      </div>
    <div class='join-button'> 
      <button v-on:click='loginButton'>Join the Community Today!</button>
    </div>
    </div>
    <div>

    </div>
  </div>
</template>

<script>
export default {
  methods: {
    loginButton() {
      this.$router.push({ name: 'login'});
    }
  }
};
</script>
<style scoped>
.home {
  text-align: center;
  background-color: #E4E4E4;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-family: Arial, Helvetica, sans-serif;
}

.welcome-container {
}

h1 {

}

.description {

}
button {
  padding: 10px;
  border-radius: 10px;
  background-color: #FFDB15;
  font-size: 20px;
  font-family: Arial, Helvetica, sans-serif
}

</style>
