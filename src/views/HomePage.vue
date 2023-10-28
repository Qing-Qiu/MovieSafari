<template>
  <a-layout style="height: 100vh;">
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible style="height: 100%;">
      <div class="logo">秋秋影视</div>
      <div class="avatar-container">
        <a-avatar :src="LogoImage" :size="50" @click="handleMenuClick('5')" style="cursor: pointer"/>
      </div>
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline" style="height: calc(100%);">
        <a-menu-item key="1" @click="handleMenuClick('1')">
          <PlayCircleOutlined/>
          <span>推荐</span>
        </a-menu-item>
        <a-menu-item key="2" @click="handleMenuClick('2')">
          <video-camera-outlined/>
          <span>类型</span>
        </a-menu-item>
        <a-menu-item key="3" @click="handleMenuClick('3')">
          <smile-outlined/>
          <span>其他功能</span>
        </a-menu-item>
        <a-menu-item key="4" @click="handleMenuClick('4')">
          <InfoOutlined/>
          <span>关于</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
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
        <template v-if="currentPage === 'recommendPage'">
          <recommend-mod/>
        </template>
        <template v-else-if="currentPage === 'typePage'">
          <type-mod/>
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
const currentPage = ref('recommendPage');
const handleMenuClick = (key) => {
  switch (key) {
    case '1':
      currentPage.value = 'recommendPage';
      break;
    case '2':
      currentPage.value = 'typePage';
      break;
    case '3':
      currentPage.value = 'extFuncPage';
      break;
    case '4':
      currentPage.value = 'aboutPage';
      break;
    case '5':
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
  MenuFoldOutlined
} from "@ant-design/icons-vue";
// export default defineComponent({
//   name: "HomePage",
//   components: {}, setup() {
//   }
// })
import LogoImage from '@/assets/meow.jpg';
import RecommendMod from "@/components/RecommendMod";
import TypeMod from "@/components/TypeMod";
import ExtFuncMod from "@/components/ExtFuncMod";
import AboutMod from "@/components/AboutMod";
import UserSetMod from "@/components/UserSetMod";
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
  font-size: 24px;
  color: #1890ff; /* 设置字体颜色 */
  text-align: center; /* 文本居中 */
  padding: 16px; /* 内边距 */
}

.avatar-container {
  margin-bottom: 20px;
}
</style>