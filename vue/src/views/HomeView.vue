<template>
  <div class="home">
    <div class='welcome-container'>
      <h1>Welcome to Rank It</h1>
      <h2>Your Voice. Your Choice.</h2>
      <div class='description'>  
        <h3>
          At Rank It, we believe every voice matters. We are dedicated to providing a platform where individuals can express their opinions, 
          make informed decisions, and bring about real change. Making an account is free, so what are you waiting for?
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
  margin: 20%;
}

h1 {
  margin: 5%;
}

.description {

  margin: 2%;

}
button {
  padding: 10px;
  border-radius: 10px;
  background-color: #FFDB15;
  font-size: 20px;
  font-family: Arial, Helvetica, sans-serif
}

button:hover {
  background-color: #8FA9B1;
}

</style>
