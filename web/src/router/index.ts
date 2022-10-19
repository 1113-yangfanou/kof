import {createRouter, createWebHistory, RouteRecordRaw} from "vue-router";
import LoginView from "../views/LoginView.vue";
import IndexView from "../views/IndexView.vue";
import MineFaceView from "../views/MineFaceView.vue";
import RankListView from "../views/RankListView.vue";
import RegisterView from "../views/RegisterView.vue";
import {userStore} from "../store";

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        component: IndexView,
        name:'home1',
        meta: {
            requestAuth: false,
        }
    },
    {
        path: '/login',
        component:LoginView,
        name:'login',
        meta: {
            requestAuth: false,
        }
    },
    {
        path: '/register',
        component: RegisterView,
        name: 'register',
        meta: {
            requestAuth: false,
        }
    },
    {
        path: '/home',
        component: IndexView,
        name: 'home',
        meta: {
            requestAuth: false,
        }
    },
    {
        path:'/mineface',
        component:MineFaceView,
        name:'mineface',
        meta: {
            requestAuth: true,
        }
    },
    {
        path:'/ranklist',
        component:RankListView,
        name:'ranklist',
        meta: {
            requestAuth: false,
        }
    }

]

const router = createRouter({
    history:createWebHistory(),
    routes
})

router.beforeEach((to, from, next)=>{
    if(to.meta.requestAuth && !userStore().is_login) {
        next({name:'login'});
    } else {
        next();
    }
})

export default router;