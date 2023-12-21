<template>
  <audio controls :ref="audio"
         :src="counter.music_url"
         @timeupdate="timeUpdate()"
  >
  </audio>
</template>

<script setup>
import {useCounterStore} from "@/store/store";

const counter = useCounterStore();
const audio = (el) => {
}
</script>
<script>
export default {
  data() {
    return {}
  },
  methods: {
    timeUpdate() {

    }
  }
}
</script>
<style scoped>

</style>