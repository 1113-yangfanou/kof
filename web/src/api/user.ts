import axios from 'axios'
import qs from 'qs'

const instance = axios.create({
    baseURL: '/api',
    timeout: 10000,
});

// instance.interceptors.request.use((config) => {
//     if(config.method === 'post') {
//         config.data = {
//             ...config.data
//         }
//     } else if(config.method === 'get'){
//         config.params = {
//             ...config.params
//         }
//     }
//     return config;
// })

export function login(data: object) {
    return instance.post('/login', qs.stringify(data));
}

export function register(data: object) {
    return instance.post('/register', qs.stringify(data));
}

export function getToken(data: object) {
    return instance.post('/get/token', qs.stringify(data));
}
export function getRankList(data: object) {
    return instance({method: 'get', url: '/get/ranklist', params: data});
}

export function analyze(data: object) {
    return instance.post("/analyze", qs.stringify(data));
}

export function upload(data: object, config: object){
    return instance.post('/upload', data, config);
}


