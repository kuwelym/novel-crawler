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
