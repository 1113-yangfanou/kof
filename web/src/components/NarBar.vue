<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark overflow-hidden">
    <div class="container">
      <a class="navbar-brand" href="#">King Of Face</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="">首页</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="myface">我的颜值</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="rankList">颜值排行榜</a>
          </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/login">登录</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script lang="ts">

export default {
  name: "NarBar"
}
</script>

<style scoped>
</style>