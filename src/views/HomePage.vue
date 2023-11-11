<template>
  <a-layout style="height: auto">
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible style="height: auto; position: fixed">
      <div class="logo">Movie Safari</div>
      <div class="avatar-container">
        <a-avatar :src="LogoImage" :size="50" @click="handleMenuClick('6')" style="cursor: pointer"/>
      </div>
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline" style="height: 100vh;">
        <a-menu-item key="1" @click="handleMenuClick('1')">
          <home-outlined/>
          <span>首页</span>
        </a-menu-item>
        <a-menu-item key="2" @click="handleMenuClick('2')">
          <PlayCircleOutlined/>
          <span>电影中心</span>
        </a-menu-item>
        <a-menu-item key="3" @click="handleMenuClick('3')">
          <smile-outlined/>
          <span>好友列表</span>
        </a-menu-item>
        <a-menu-item key="4" @click="handleMenuClick('4')">
          <video-camera-outlined/>
          <span>其他功能</span>
        </a-menu-item>
        <a-menu-item key="5" @click="handleMenuClick('5')">
          <InfoOutlined/>
          <span>关于</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout :style="{ marginLeft:(collapsed?'80px':'200px')}">
      <a-layout-header style="background: #fff; padding: 0;display: flex;align-items: center">
        <menu-unfold-outlined
            v-if="collapsed"
            class="trigger"
            @click="() => (collapsed = !collapsed)"
        />
        <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)"/>
      </a-layout-header>
      <a-layout-content
          :style="{ margin:'0', padding: '0', background: '#fff', minHeight: '100vh' }"
      >
        <template v-if="currentPage === 'mainPage'">
          <recommend-search-mod/>
        </template>
        <template v-else-if="currentPage === 'moviePage'">
          <movie-center-mod/>
        </template>
        <template v-else-if="currentPage === 'friendPage'">
          <friend-mod/>
        </template>
        <template v-else-if="currentPage === 'extFuncPage'">
          <ext-func-mod/>
        </template>
        <template v-else-if="currentPage === 'aboutPage'">
          <about-mod/>
        </template>
        <template v-else-if="currentPage === 'userSetPage'">
          <user-set-mod/>
        </template>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script setup>
import {ref} from 'vue';

const selectedKeys = ref(['1']);
const collapsed = ref(false);
const currentPage = ref('mainPage');
const handleMenuClick = (key) => {
  switch (key) {
    case '1':
      currentPage.value = 'mainPage';
      break;
    case '2':
      currentPage.value = 'moviePage';
      break;
    case '3':
      currentPage.value = 'friendPage';
      break;
    case '4':
      currentPage.value = 'extFuncPage';
      break;
    case '5':
      currentPage.value = 'aboutPage';
      break;
    case '6':
      currentPage.value = 'userSetPage';
      break;
  }
};
import {
  PlayCircleOutlined,
  VideoCameraOutlined,
  SmileOutlined,
  InfoOutlined,
  MenuUnfoldOutlined,
  MenuFoldOutlined,
  HomeOutlined
} from "@ant-design/icons-vue";
// export default defineComponent({
//   name: "HomePage",
//   components: {}, setup() {
//   }
// })
import LogoImage from '@/assets/meow.jpg';
import FriendMod from "@/components/FriendMod";
import ExtFuncMod from "@/components/ExtFuncMod";
import AboutMod from "@/components/AboutMod";
import UserSetMod from "@/components/UserSetMod";
import RecommendSearchMod from "@/components/RecommendSearchMod";
import MovieCenterMod from "@/components/MovieCenterMod";
</script>

<style scoped>
.trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

.trigger:hover {
  color: #1890ff;
}

.logo {
  /*height: 32px;*/
  /*background: rgba(255, 255, 255, 0.3);*/
  /*margin: 16px;*/

  font-family: cursive; /* 使用一个手写艺术字体 */
  font-size: 20px;
  color: #1890ff; /* 设置字体颜色 */
  text-align: center; /* 文本居中 */
  padding: 16px; /* 内边距 */
}

.avatar-container {
  margin-bottom: 20px;
}
</style>