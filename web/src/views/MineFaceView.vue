<template>
  <ContentField>
    <div class="row">
      <div class="col-2" style="text-align: center; margin: 100px 60px; padding: 0">
        <label for="fileInput"><p style="margin-top: 6px;" type="button" class="btn btn-primary">更换图片</p></label>
        <input type="file" id="fileInput" class="fileinput"  @change="getFile($event)" style="margin-top: 6px;"/> <br>
        <button type="button" class="btn btn-outline-success" style="margin: 5px auto" @click="analyzeFace">颜值评测</button>
        <p class="fw-bold" style="margin-top: 6px;" v-if="store.is_analyze">您的颜值分数为：{{store.score}}</p>
      </div>
      <div class="col-3">
        <img :src="store.photo" class="img-fluid" alt="我的图片"> <br>
        <div style="color:red; margin-top: 5px ;">{{msg}}</div>
      </div>
      <div class="col-5" v-if="store.is_analyze" style="margin-top: 20px; font-size: medium">
        <span v-html="store.description">
        </span>
      </div>
      <div class="col-5" v-else style="margin-top: 10px;">
        <p class="fw-bold"> 这里将会展示您的颜值评测结果 </p>
      </div>
    </div>
  </ContentField>
</template>

<script lang="ts">
import ContentField from "../components/ContentField.vue"
import {userStore} from "../store";
import {analyze, getToken, upload} from "../api/user";
import {defineComponent, ref } from "vue";


export default defineComponent({
  components: {ContentField},
  setup() {
    const store = userStore();
    let msg = ref('');
    const getFile = (e:any) =>{
      const file = e.target.files[0];
      if(!file) return;
      const formData = new FormData();
      formData.append("file", file);
      console.log(formData);
      upload(formData, {
        headers: {
        'Content-Type': 'multipart/form-data'}
      }).then((response) => {
        store.is_analyze = false;
        store.photo = response.data.url;
        console.log(response);
      })
    }

    const analyzeFace = () => {
      getToken({
        url:store.photo
      }).then((response) => {
        if(response.status == 200) {
          if(response.data.msg != null) {
            msg.value = response.data.msg;
            return ;
          }
          let face_token = response.data.face_token;
          analyze({
            face_token: face_token,
            username: store.name,
            photo:store.photo
          }).then((response) => {
            store.is_analyze = true;
            if(response.data.msg === '评测成功') {
              store.description = response.data.description;
              store.score = response.data.score;
            } else {
              console.log(response);
              store.description = response.data.msg;
            }
          })
        }
      })
    }

    return {
      store,
      msg,
      getFile,
      analyzeFace
    }
  }
})
</script>

<style scoped>
.fileinput{
  display: none;
}
</style>