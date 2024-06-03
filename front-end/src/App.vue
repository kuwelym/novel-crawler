<template>
  <div id="app">
    <Header class="mb-3" />
    <router-view></router-view>
  </div>
</template>

<script>
import Header from "./components/Header";
export default {
  name: 'App',
  components: {
    Header
  }
};

</script>

<style>

body {
    background: url(//static.8cache.com/img/bg.jpg) top center repeat-x #F4F4F4!important;
    color: #4E4E4E;
}

html, body {
    height: 100%;
}

body {
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 14px!important;
    line-height: 1.42857143;
    color: #333;
    background-color: #fff;
}

body {
    margin: 0;
}

* {
    margin: 0;
}

#app {
    min-height: 100%;
    min-height: calc(100% - 117px);
    height: auto !important;
    height: 100%;
}

h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6 {
    font-family: inherit;
    font-weight: 500;
    line-height: 1.1;
    color: inherit;
}

h1, .h1, h2, .h2, h3, .h3, h4, .h4 {
    margin: 0!important;
    padding: 0;
    font-size: inherit!important;
    display: inline-block;
}
</style>