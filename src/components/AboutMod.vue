<template>
  <div class="login-container">
    <a-space direction="vertical">
      <a-typography-title level="5">你好！我是秋刀鱼</a-typography-title>
      <GithubFilled spin="spin" />
    </a-space>
  </div>
</template>

<script setup>
import {GithubFilled} from "@ant-design/icons-vue";

</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  height: 100vh;
  background-color: #f0f0f0;
}

</style>