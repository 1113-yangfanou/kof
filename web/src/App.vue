<template>
  <NarBar></NarBar>
  <router-view></router-view>
</template>

<script setup lang="ts">
import NarBar from "./components/NarBar.vue";

</script>

<style>
body {
  background-image: url("./assets/background.png");
  background-size: cover;
}

</style>