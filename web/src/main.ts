import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap'
import router from "./router/index"
import { createPinia } from "pinia";

const pinia = createPinia();


const app = createApp(App)
app.use(router)
app.use(pinia)
app.mount('#app')
