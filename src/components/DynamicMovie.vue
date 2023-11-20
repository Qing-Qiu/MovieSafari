<template>
  <HomePage>
    <a-card class="image-card2">
      <a-row>
        <a-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" :xxl="4"></a-col>
        <a-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :xxl="8">
          <img :src="this.movie_content.img" :alt="this.movie_content.name" referrerpolicy="no-referrer"
               @error="imgError2(this.movie_content)"
               style="display: block;width: auto"
          />
        </a-col>
        <a-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :xxl="8">
          <a-typography>
            <a-typography-title style="text-align: left">{{ this.movie_content.name }}</a-typography-title>
            <a-typography-paragraph style="text-align: left">
              <a-typography-text strong>导演：</a-typography-text>
              {{ this.movie_content.director }}
            </a-typography-paragraph>
            <a-typography-paragraph style="text-align: left">
              <a-typography-text strong>主演：</a-typography-text>
              {{ this.movie_content.actor }}
            </a-typography-paragraph>
            <a-typography-paragraph style="text-align: left">
              <a-typography-text strong>类型：</a-typography-text>
              {{ this.movie_content.tag }}
            </a-typography-paragraph>
            <a-typography-paragraph style="text-align: left">
              <a-typography-text strong>评分：</a-typography-text>
              {{ this.movie_content.rate }}
            </a-typography-paragraph>
            <a-typography-paragraph style="text-align: left">
              <a-typography-text strong>评价人数：</a-typography-text>
              {{ this.movie_content.popular }}
            </a-typography-paragraph>
            <a-typography-paragraph style="text-align: left">
              <a-typography-text strong>年份：</a-typography-text>
              {{ this.movie_content.year }}
            </a-typography-paragraph>
            <a-typography-paragraph style="text-align: left">
              <a-typography-text strong>制片国家/地区：</a-typography-text>
              {{ this.movie_content.region }}
            </a-typography-paragraph>
            <a-typography-paragraph style="text-align: left">
              <a-typography-text strong>剧情简介：</a-typography-text>
              {{ this.movie_content.summary }}
            </a-typography-paragraph>
          </a-typography>
        </a-col>
        <a-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" :xxl="4"></a-col>
      </a-row>
    </a-card>
    <div class="image-grid">
      <a-card
          v-for="(item, itemIndex) in person_list"
          :key="itemIndex"
          class="image-card"
          hoverable
      >
        <div class="card-content" v-if="this.person_list.length">
          <img :src="item.img" :alt="item.name" referrerpolicy="no-referrer" @error="imgError(item)"/>
          <a-card-meta :title="item.name" :description="item.role"/>
        </div>
      </a-card>
      <div
          v-for="(item,itemIndex) in new Array((4 - person_list.length % 4) % 4)"
          :key="itemIndex"
          class="image-card"
      >
      </div>
    </div>
    <div>
      <a-pagination show-less-items v-model:current="current1" show-quick-jumper :total="this.count1"
                    :default-page-size="4" :show-size-changer="false" @change="onChange1"/>
    </div>
    <a-row>
      <a-col :lg="4"></a-col>
      <a-col :lg="16">
        <a-list
            class="comment-list"
            item-layout="horizontal"
            :data-source="comment_list"
        >
          <template #renderItem="{ item }">
            <a-list-item>
              <a-comment :author="item.nickname" :avatar="item.avatar">
                <template #content>
                  <a-typography-paragraph style="text-align: left">
                    {{ item.comment }}
                  </a-typography-paragraph>
                </template>
              </a-comment>
            </a-list-item>
          </template>
        </a-list>
      </a-col>
      <a-col :lg="4"></a-col>
    </a-row>
  </HomePage>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      movie_content: [],
      movie_list: [],
      person_list: [],
      comment_list: [],
      current1: 1,
      count1: 0,
      limit1: 4,
      offset1: 0,
      current2: 0,
      count2: 0,
      limit2: 8,
      offset2: 0,
      username: '游客',
    }
  },
  beforeMount() {
    this.fetchData();
  },
  methods: {

    imgError(item) {
      item.image = require('../assets/meow.jpg')
    },

    imgError2(item) {
      item.img = require('../assets/meow.jpg')
    },

    onChange1() {
      this.offset1 = (this.current1 - 1) * 4;
      this.fetchData();
    },

    async fetchData() {
      try {
        const response = await axios.post('http://localhost:8080/movie/detail',
            {movie: this.movie_id}).then(
            response => {
              this.movie_content = response.data;
              console.log(response.data);
              console.log(this.movie_content);
            },
            error => {
            }
        )
      } catch (error) {
      }
      try {
        const response = await axios.post('http://localhost:8080/person/count',
            {id: this.movie_id}).then(
            response => {
              this.count1 = response.data;
              console.log(this.count1);
            },
            error => {
            }
        )
      } catch (error) {
      }
      try {
        const response = await axios.post('http://localhost:8080/person/relevant',
            {id: this.movie_id, limit: this.limit1, offset: this.offset1}).then(
            response => {
              this.person_list = response.data;
              console.log(this.person_list);
            },
            error => {
            }
        )
      } catch (error) {
      }
      try {
        const response = await axios.post('http://localhost:8080/comment/count',
            {id: this.movie_id}).then(
            response => {
              this.count2 = response.data;
              console.log(this.comment_list);
            },
            error => {
            }
        )
      } catch (error) {
      }
      try {
        const response = await axios.post('http://localhost:8080/comment/comment',
            {id: this.movie_id, limit: this.limit2, offset: this.offset2}).then(
            response => {
              this.comment_list = response.data;
              console.log(this.comment_list);
            },
            error => {
            }
        )
      } catch (error) {
      }
    }
  },
  props: {
    movie_id: {
      type: String,
      required: true
    }
  },
  created() {
    console.log(this.movie_id);
  }
}
</script>
<script setup>
import HomePage from "@/views/HomePage";
</script>

<style scoped>

.image-grid {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.image-card {
  width: 20%; /* Adjust this to control the card width */
  margin: 20px 25px 20px 30px;
}

.card-content {
  display: flex;
  flex-direction: column;
}

img {
  max-width: 100%;
}

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