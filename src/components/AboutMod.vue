<template>
  <HomePage>
    <div style="
    display:flex;
    align-items:center;
    justify-content:center;
    /*width:200px;*/
    height:500px;
">
      <a-avatar :size="100" @click="redirectTo" style="cursor: pointer;">
        <GithubFilled spin="spin" style="font-size: 100px"/>
      </a-avatar>
    </div>
  </HomePage>
</template>
<script setup>
import {GithubFilled} from "@ant-design/icons-vue";
import HomePage from "@/views/HomePage";

const redirectTo = () => {
  window.open('https://github.com/Qing-Qiu/MovieSafari', '_blank');
}
</script>

<style scoped>

</style>