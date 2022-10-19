import {defineStore} from "pinia";

export const userStore = defineStore('main', {
    state: () => {
        return{
            name: '',
            photo: '',
            rating: 0,
            is_login: false,
            is_analyze: false,
            description: '',
            score:0,
        }
    },
    actions: {
        setUserInfo(user: User) {
            this.name = user.name;
            this.photo = user.photo;
            this.rating = user.rating;
            this.is_login = user.is_login;
        },
        logout() {
            this.name = '';
            this.photo = '';
            this.rating = 0;
            this.is_login = false;
            this.is_analyze = false;
            this.description = '';
        }
    }
})

interface User {
    name: string,
    photo: string,
    rating: number,
    is_login: boolean
}