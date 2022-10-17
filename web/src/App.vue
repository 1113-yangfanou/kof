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
body{
  overflow: hidden;
  background-image: url("https://image20221016.oss-cn-shanghai.aliyuncs.com/%E6%BC%82%E4%BA%AE%E5%B0%91%E6%95%B0%E6%B0%91%E6%97%8F%E7%BE%8E%E5%A5%B34k%E5%A3%81%E7%BA%B8_%E5%BD%BC%E5%B2%B8%E5%9B%BE%E7%BD%91.jpg");
  background-size: cover;
}

</style>