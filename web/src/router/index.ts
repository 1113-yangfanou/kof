import {createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw} from "vue-router";
import Login from "../views/Login.vue"

const routes: Array<RouteRecordRaw> = [{
        path: '/login',
        component:Login,
        name:'login',
}]

const router = createRouter({
    history:createWebHistory(),
    routes,
})

export default router;