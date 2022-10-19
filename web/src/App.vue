<template>
  <NarBar></NarBar>
  <router-view></router-view>
</template>

<script lang="ts">
import NarBar from "./components/NarBar.vue";

export default {
  components: { NarBar },
  setup() {
    
  }
}

</script>

<style>
body {
  background-image: url("./assets/background.png");
  background-size: cover;
}

</style>