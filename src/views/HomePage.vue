<template>
  <a-layout style="height: auto">
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible style="height: auto; position: fixed">
      <img :src="(collapsed ? require('../assets/logo2.svg') : require('../assets/logo.svg'))"
           style="cursor: pointer; stroke-opacity: 0" alt="movie safari"/>
      <a-menu v-model:selectedKeys="selectedKeys" :openKeys="openKeys" theme="dark" mode="inline"
              style="height: 100vh;">
        <a-menu-item key="1" @click="handleMenuClick('1')">
          <home-outlined/>
          <span>首页</span>
        </a-menu-item>
        <a-menu-item key="2" @click="handleMenuClick('2')">
          <PlayCircleOutlined/>
          <span>电影中心</span>
        </a-menu-item>
        <a-sub-menu key="3" title="其他功能">
          <a-menu-item key="3-1" @click="handleMenuClick('3-1')">
            <SettingOutlined/>
            <span>大模型嵌套</span>
          </a-menu-item>
          <a-menu-item key="3-2" @click="handleMenuClick('3-2')">
            <BarChartOutlined/>
            <span>可视化展示</span>
          </a-menu-item>
        </a-sub-menu>
        <a-menu-item key="4" @click="handleMenuClick('4')">
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
        <a-dropdown class="avatar-container">
          <a-avatar :src="UserImage" :size="50" style="cursor: pointer;"/>
          <template #overlay>
            <a-menu>
              <a-menu-item :key="5" @click="handleMenuClick('5')">
                设置偏好
              </a-menu-item>
              <a-menu-item :key="6" @click="handleMenuClick('6')">
                登出
              </a-menu-item>
            </a-menu>
          </template>
        </a-dropdown>
      </a-layout-header>
      <a-layout-content
          :style="{ margin:'0', padding: '0', background: '#fff', minHeight: '100vh' }"
      >
        <slot/>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
export default {
  data() {
    return {
      username: '游客',
    }
  },
  // beforeMount() {
  //   this.username = sessionStorage.getItem('username');
  //   //获取当前用户的id和昵称
  //   console.log(this.username);
  // }
}
</script>
<script setup>
import {ref} from 'vue';

const collapsed = ref(false);
const selectedKeys = ref(['1']);
const openKeys = ref(['3']);
const handleMenuClick = (key) => {
  switch (key) {
    case '1':
      router.push({path: '/homepage', query: {collapsed: collapsed.value.toString()}});
      break;
    case '2':
      router.push({path: '/center', query: {collapsed: collapsed.value.toString()}});
      break;
    case '3-1':
      router.push({path: '/model', query: {collapsed: collapsed.value.toString()}});
      break;
    case '3-2':
      router.push({path: '/chart', query: {collapsed: collapsed.value.toString()}});
      break;
    case '4':
      router.push({path: '/about', query: {collapsed: collapsed.value.toString()}});
      break;
    case '5':
      router.push({path: '/prefer', query: {collapsed: collapsed.value.toString()}});
      break;
    case '6':
      sessionStorage.clear();
      router.push('/auth/login');
      break;
  }
};
import {
  PlayCircleOutlined,
  VideoCameraOutlined,
  SettingOutlined,
  BarChartOutlined,
  SmileOutlined,
  InfoOutlined,
  MenuUnfoldOutlined,
  MenuFoldOutlined,
  HomeOutlined
} from "@ant-design/icons-vue";
import UserImage from '@/assets/meow.jpg';
import ModelMod from "@/components/ModelMod";
import ChartMod from "@/components/ChartMod";
import AboutMod from "@/components/AboutMod";
import RecommendSearchMod from "@/components/RecommendSearchMod";
import MovieCenterMod from "@/components/MovieCenterMod";
import router from "@/router/router";
import PreferMod from "@/components/PreferMod";
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
  margin-bottom: -15px;
  margin-right: 20px;
  margin-left: auto;
}
</style>