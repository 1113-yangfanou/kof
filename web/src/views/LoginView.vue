<template>
  <ContentField>
    <div class="row justify-content-md-center">
      <div class="col-3">
        <form @submit.prevent="UserLogin">
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
          </div>
          <div class="error-message">{{ error_message }}</div>
          <div> <router-link :to="{name:'register'}"><span>还没有账号？点我注册</span></router-link> </div>
          <button type="submit" class="btn btn-primary" style="margin-top: 5px;">提交</button>
        </form>
      </div>
    </div>
  </ContentField>
</template>

<script setup lang="ts">
import ContentField from '../components/ContentField.vue'
import { ref } from 'vue'
import {defineComponent} from "vue";
import { login } from '../api/user'
import router from '../router/index'
import {userStore} from "../store/";
    let username = ref('');
    let password = ref('');
    let error_message = ref('');
    const store = userStore();
    const UserLogin = () => {
      login({
        username: username.value,
        password: password.value,
      }).then((response) => {
        if(response.data.msg === '登录成功') {
          console.log(response.data);
          const data = JSON.parse(response.data.user);
          store.setUserInfo({
            name: data.name,
            photo: data.photo,
            rating: data.rating,
            is_login: true
          });
          router.push({name: 'mineface'});
        } else {
          error_message.value = response.data.msg;
          console.log(response);
        }
      })
    }


</script>

<style scoped>
button {
  width: 100%;
}
div.error-message {
  color: red;
}
</style>