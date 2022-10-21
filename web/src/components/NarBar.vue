<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark overflow-hidden">
    <div class="container">
      <router-link class="navbar-brand"  :to="{name:'home'}">King Of Face</router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link :class="route_name === 'home' ? 'nav-link active' : 'nav-link'" :to="{name:'home'}">首页</router-link>
          </li>
          <li class="nav-item">
            <router-link :class="route_name === 'mineface' ? 'nav-link active' : 'nav-link'" :to="{name:'mineface'}">我的颜值</router-link>
          </li>
          <li class="nav-item">
            <router-link :class="route_name === 'ranklist' ? 'nav-link active' : 'nav-link'" :to="{name:'ranklist'}">颜值排行榜</router-link>
          </li>
        </ul>
        <ul class="navbar-nav" v-if="store.is_login">
          <li class="nav-item">
            <a class="nav-link" @click="logout">
              欢迎您{{ store.name }}, 退出
            </a>
          </li>
        </ul>
        <ul class="navbar-nav" v-else>
          <li class="nav-item">
            <router-link class="nav-link" :to="{name:'login'}">登录</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts">
import { useRoute } from "vue-router";
import { computed } from 'vue';
import { userStore } from "../store/";


let route = useRoute();
console.log(route);

const route_name = computed(() => route.name)

const store = userStore();
const logout = () => {
  store.logout();
}

</script>

<style scoped>

</style>