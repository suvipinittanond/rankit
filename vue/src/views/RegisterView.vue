<template>
  <div id="register" class="text-center">
    <img src='../assets/RankItSloganNoBG.png' alt='Image' class='register-logo'/>
    <form v-on:submit.prevent="register" class='register-form'>
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <input type="text" id="username" v-model="user.username"  placeholder='Username' class='input-box' required autofocus />
      </div>
      <div class="form-input-group">
        <input type="password" id="password" v-model="user.password" placeholder='Password' class='input-box' required />
      </div>
      <div class="form-input-group">
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" placeholder='Confirm Password' class='input-box' required />
      </div>
      <button type="submit">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}

h1 {
  text-align: center;
  padding-bottom: 10px;

}

.text-center {
  text-align: center;
  padding-bottom: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #E4E4E4;
  height: 100vh;
  font-family: Arial, Helvetica, sans-serif;
}

.register-form {
  background-color: #c9c9c6;
  box-shadow: 0 2px 10px #020301;
  height: 400px;
  width: 350px;
  border-radius: 40px;
}

label {
  display: block;
  font-weight: bold
}

button {
  padding: 10px;
  border-radius: 10px;
  background-color: #8FA9B1;
  font-size: 15px;
  font-family: Arial, Helvetica, sans-serif
}

button:hover {
  background-color: #FFDB15;
}
.input-box {
  border-radius: 15px;
  padding: 12px;
}

</style>
