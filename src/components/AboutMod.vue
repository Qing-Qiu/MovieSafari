<template>
  <div class="about-page">
    <header>
      <h1>关于</h1>
    </header>
    <section class="content">
      <div class="team-info">
        <h2>团队</h2>
        <p>
          秋刀鱼
        </p>
      </div>
    </section>
    <section class="contact-info">
      <h2>联系我们</h2>
      <div class="contact-details">
        <p>Email: 202103151211@zjut.edu.cn</p>
      </div>
    </section>
  </div>
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

</template>
<script setup>
import {GithubFilled} from "@ant-design/icons-vue";
import HomePage from "@/views/HomePage";

const redirectTo = () => {
  window.open('https://github.com/Qing-Qiu/MovieSafari', '_blank');
}
</script>

<style scoped>
.about-page {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

header {
  text-align: center;
  margin-bottom: 20px;
}

.content {
  margin-bottom: 40px;
}

.team-info {
  margin-bottom: 30px;
}

.contact-info {
  text-align: center;
}

.contact-details {
  margin-top: 15px;
}
</style>