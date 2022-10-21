<template>
  <ContentField>
    <span style="text-align: center;display:block; font-size: xxx-large"> 欢迎来到颜值PK系统</span> <br>
    <div class="tableTitle"><span class="midText">系统简介</span></div><br>
    <h4><span>这是一个可以评测你的人脸的系统！你可以登录然后上传你的图片，进行评测。评测的结果仅供参考哦！</span></h4>
    <p>效果如下图：</p>
    <div style="text-align: center;">
      <img src="../assets/bc.png" alt="这是一个图片">
      <img src="../assets/b.png" alt="安全" style="margin-top: 40px;">
    </div>
  </ContentField>
</template>

<script setup lang="ts">
import ContentField from "../components/ContentField.vue"

</script>

<style scoped>
.tableTitle {
  position: relative;
  margin: 0 auto;
  width: 800px;
  height: 1px;
  background-color: #d4d4d4;
  text-align: center;
  font-size: 16px;
  color: rgba(101, 101, 101, 1);
}
.midText {
  position: absolute;
  left: 50%;
  background-color: #ffffff;
  padding: 0 15px;
  transform: translateX(-50%) translateY(-50%);
}

</style>