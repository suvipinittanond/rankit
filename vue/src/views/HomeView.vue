<template>
  <div class="home">
      <div class="content-container">
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
        <div class="slideshow-container">
          <div class="mySlides fade" v-for="(slide, index) in slides" :key="index" v-show="slideIndex === index">
            <div class="numbertext">{{ index + 1 }} / {{ slides.length }}</div>
            <img :src="slide.imgSrc" style="width:100%">
            <div class="text">{{ slide.caption }}</div>
          </div>
        </div>
      </div>
    </div>
  </template>
  <script>
  export default {
    data() {
      return {
        slideIndex: 0,
        slides: [
          { imgSrc: 'src/assets/LogoblackNoBG.png'},
          { imgSrc: 'src/assets/LogoblackNoBG.png'},
          { imgSrc: 'src/assets/LogoblackNoBG.png' }
        ]
      };
    },
    mounted() {
      this.showSlides();
    },
    methods: {
      loginButton() {
        this.$router.push({ name: 'login' });
      },
      showSlides() {
        let i;
        const slides = document.getElementsByClassName("mySlides");
        for (i = 0; i < slides.length; i++) {
          slides[i].style.display = "none";
        }
        this.slideIndex++;
        if (this.slideIndex > slides.length) {
          this.slideIndex = 1;
        }
        slides[this.slideIndex - 1].style.display = "block";
        setTimeout(this.showSlides, 2000);
      }
    }
  };
  </script>
  <style scoped>
  .home {
    background-color: #E4E4E4;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    font-family: Arial, Helvetica, sans-serif;
  }
  .content-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    max-width: 1200px;
    padding: 0 20px;
  }
  .welcome-container {
    max-width: 50%;
    padding: 20px;
  }
  .slideshow-container {
    max-width: 50%;
    position: relative;
  }
  .welcome-container h1,
  .welcome-container h2 {
    margin: 0;
  }
  .welcome-container .description {
    margin-top: 20px;
  }
  .button-container {
    margin-top: 20px;
  }
  button {
    padding: 10px 20px;
    border-radius: 10px;
    background-color: #FFDB15;
    font-size: 20px;
    font-family: Arial, Helvetica, sans-serif;
    cursor: pointer;
    border: none;
  }
  button:hover {
    background-color: #8FA9B1;
  }
  .slideshow-container img {
    width: 100%;
  }
  .numbertext,
  .text {
    color: #F2F2F2;
    font-size: 15px;
    padding: 8px 12px;
    position: absolute;
    bottom: 8px;
    width: 100%;
    text-align: center;
  }
  .dot {
    height: 15px;
    width: 15px;
    margin: 0 5px;
    background-color: #c9c9c6;
;
    border-radius: 50%;
    display: inline-block;
    transition: background-color 0.6s ease;
  }
  .active {
    background-color: #c9c9c6;
;
  }
  .fade {
    animation-name: fade;
    animation-duration: 1.5s;
  }
  @keyframes fade {
    from {
      opacity: 0.4;
    }
    to {
      opacity: 1;
    }
  }
  </style>