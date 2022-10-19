<template>
  <ContentField>
    <table class="table">
      <thead>
      <tr>
        <th scope="col">昵称</th>
        <th scope="col">图片</th>
        <th scope="col">颜值分数</th>
        <th scope="col">分析</th>
      </tr>
      </thead>
      <tbody>
        <tr v-for="record in records" key="record.id">
          <td>{{record.username}}</td>
          <td>
            <div>
              <img :src="record.photo" alt=""  class="record-img">
            </div>
          </td>
          <td>{{record.score}}</td>
          <td v-html="record.description"></td>
        </tr>
      </tbody>
    </table>
    <nav aria-label="...">
      <ul class="pagination" style="float: right;">
        <li class="page-item" @click="click_page(-2)">
          <a class="page-link" href="#">前一页</a>
        </li>
        <li :class="'page-item ' + page.is_active" v-for="page in pages" key="page.number" @click="click_page(page.number)">
          <a class="page-link" href="#">{{ page.number }}</a>
        </li>
        <li class="page-item" @click="click_page(-1)">
          <a class="page-link" href="#">后一页</a>
        </li>
      </ul>
    </nav>
  </ContentField>
</template>

<script lang="ts">
import ContentField from "../components/ContentField.vue"
import {defineComponent, ref} from "vue";
import {getRankList} from "../api/user";

export default defineComponent({
  components: {ContentField},
  setup() {
    let current_page = 1;
    let total = 0;
    let records = ref([]);
    // let pages = ref(<{number:number, is_active: string}[]>[]);
    let pages = ref<{number:number, is_active:string}[]>([]);
    const click_page = (page: number) => {
      if(page === -2) page = current_page - 1;
      else if(page === -1) page = current_page + 1;
      let max_pages = Math.ceil(total / 5);
      if(page >= 1 && page <= max_pages) {
        pull_page(page);
      }
    }
    const udpate_pages = () => {
      let max_pages = Math.ceil(total / 5);
      let new_pages: {number: number, is_active: string}[] = [];
      for (let i = current_page - 2; i <= current_page + 2; i ++ ) {
        if (i >= 1 && i <= max_pages) {
          new_pages.push({number:i, is_active:i === current_page ? "active" : "",})
        }
      }
      pages.value = new_pages;
    }

    const pull_page = (page: number) => {
      current_page = page;
      getRankList({
        page:page
      }).then((response) => {
        console.log(response);
        records.value=response.data.record;
        total = response.data.count;
        udpate_pages();
      })
    }
    pull_page(current_page);
    return {
      records,
      pages,
      click_page
    }
  }

})
</script>

<style scoped>
.record-img{
  width: 100%;
}
</style>