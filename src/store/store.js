import {defineStore} from 'pinia'
import {ref} from "vue";

export const useCounterStore = defineStore('counter', {
    state: () => {
        return {
            music_url: ref(''),
        }
    },
    actions: {
        change(url) {
            this.music_url = url;
            console.log(this.music_url);
        }
    }
})
