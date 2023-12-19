<template>
  <audio controls ref="audio"
         :src="counter.music_url"
         @timeupdate="timeUpdate()"
  >
  </audio>
<!--  <a-typography-paragraph>-->
<!--    {{ this.time }}-->
<!--  </a-typography-paragraph>-->
  <a-button @click="qaq" style="display: none" ref="button"></a-button>
</template>

<script setup>
import {useCounterStore} from "@/store/store";

const counter = useCounterStore();
const qaq = () => {
  this.$refs.audio.src = counter.music_url;
  console.log(counter.music_lrc);
}

</script>
<script>
import {ref} from "vue";

export default {
  data() {
    return {
      music_url: ref(''),
      time: ref(0.0),
    }
  },
  methods: {
    timeUpdate() {
      this.time = this.$refs.audio.currentTime;
    }
  }
}
</script>
<style scoped>

</style>