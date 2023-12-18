<template>
  <audio controls ref="audio"
         :src="counter.music_url">
  </audio>
  <a-button @click="qaq" style="display: none">123</a-button>
</template>

<script setup>
import {useCounterStore} from "@/store/store";

const counter = useCounterStore();
const qaq = () => {
  console.log(counter.music_url);
  this.$refs.audio.src = counter.music_url;
  this.$refs.audio.play();
}
</script>

<style scoped>

</style>