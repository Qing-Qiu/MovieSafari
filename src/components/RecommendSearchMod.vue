<template>
  <HomePage>
    <a-row>
      <a-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :xxl="8"></a-col>
      <a-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12" :xxl="12">
        <a-form
            :model="formState"
            name="basic"
            :label-col="{ span: 8 }"
            :wrapper-col="{ span: 16 }"
            autocomplete="off"
            class="login-form"
            @submit="submitForm"
        >
          <a-form-item
              label=""
              name="search"
              :rules="[]"
          >
            <a-input-search
                v-model:value="formState.search"
                placeholder="请输入搜索内容"
                size="large"
                @search="submitForm"
            />
            <!--        <a-radio-group v-model:value="val123">-->
            <!--          <a-radio :value="1">根据内容搜索</a-radio>-->
            <!--          <a-radio :value="2">根据影片名搜索</a-radio>-->
            <!--          <a-radio :value="3">根据人名搜索</a-radio>-->
            <!--        </a-radio-group>-->
          </a-form-item>
        </a-form>
      </a-col>
      <a-col :xs="2" :sm="2" :md="2" :lg="2" :xl="2" :xxl="2"></a-col>
    </a-row>
    <template v-if="recommendedImages.length && !detail && !search">
      <div class="guess-you-like">
        <h2>猜你喜欢</h2>
        <a-button @click="refresh()">换一批</a-button>
        <div class="image-grid">
          <a-card
              v-for="(item, itemIndex) in recommendedImages"
              :key="itemIndex"
              class="image-card"
              hoverable
              @click="watchDetail(item.id)"
          >
            <div class="card-content" v-if="recommendedImages.length">
              <img :src="item.image" :alt="item.title" referrerpolicy="no-referrer" @error="imgError(item)"/>
              <a-card-meta :title="item.title" :description="item.description"/>
            </div>
          </a-card>
        </div>
      </div>
    </template>
    <template v-else-if="search && !detail">
      <a-button @click="backward()">
        <ArrowLeftOutlined/>
      </a-button>
      <div v-if="movies===null">
        共查询到0部电影
      </div>
      <div v-else>
        以下展示{{ this.movies.length }}部电影。
      </div>
      <div v-for="(movie,index) in this.movies" :key="index">
        <a-card class="image-card2">
          <a-typography-title>{{ movie.name }}</a-typography-title>
          <a-typography>导演：{{ movie.director }}</a-typography>
          <a-typography>主演：{{ movie.actor }}</a-typography>
          <a-typography>类型：{{ movie.tag }}</a-typography>
          <a-typography>评分：{{ movie.rate }}</a-typography>
          <a-rate v-model:value="movie.rate" allow-half/>
          <a-typography>评价人数：{{ movie.popular }}</a-typography>
          <a-typography>年份：{{ movie.year }}</a-typography>
          <a-typography>制片国家/地区：{{ movie.region }}</a-typography>
          <a-typography>剧情简介：{{ movie.summary }}</a-typography>
          <img :src="movie.img" :alt="movie.name" referrerpolicy="no-referrer" @error="imgError2(movie)"/>
        </a-card>
      </div>
    </template>
  </HomePage>
</template>

<script setup>
import HomePage from "@/views/HomePage";
</script>
<script>
import axios from "axios";
import {ArrowLeftOutlined} from "@ant-design/icons-vue";
import router from "@/router/router";

export default {
  components: {
    ArrowLeftOutlined
  },
  data() {
    return {
      formState: {
        search: '',
      },
      detail: false,
      search: false,
      movies: [],
      movie_id: 0,
      movie_content: [],
      recommendedImages: [],
      person_list: [],
      comment_list: [],
      current1: 1,
      limit1: 4,
      offset1: 0,
      count1: 0,
      current2: 1,
      limit2: 8,
      offset2: 0,
      count2: 0,
    };
  },
  beforeMount() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const response = await axios.post('http://localhost:8080/movie/recommend',
            {}).then(
            response => {
              console.log(response.data);
              for (let i = 0; i < 8; i++) {
                this.recommendedImages.push({
                  title: response.data[i].name,
                  id: response.data[i].movieID,
                  image: response.data[i].img,
                  description: response.data[i].genre
                });
              }
              for (let i = 0; i < 8; i++) {
                if (this.recommendedImages[i].description !== null) {
                  if (this.recommendedImages[i].description.endsWith(',')) {
                    this.recommendedImages[i].description =
                        this.recommendedImages[i].description.slice(0, -1);
                  }
                }
              }
              console.log(this.recommendedImages);
            },
            error => {
            }
        )
      } catch (error) {

      }
    },

    refresh() {
      this.recommendedImages.splice(0, 8);
      this.fetchData();
    },

    backward() {
      this.detail = false;
      this.search = false;
      this.current1 = 1;
      this.offset1 = (this.current1 - 1) * 4;
    },

    imgError(item) {
      item.image = require('../assets/meow.jpg')
    },

    imgError2(item) {
      item.img = require('../assets/meow.jpg')
    },

    onChange1() {
      this.offset1 = (this.current1 - 1) * 4;
      this.watchDetail(this.movie_id);
    },

    async watchDetail(id) {
      await router.push('/movie/' + id);
    },

    async submitForm() {
      this.search = true;
      this.detail = false;
      try {
        const response = await axios.post('http://localhost:8080/movie/search',
            {content: this.formState.search}).then(
            response => {
              this.movies = response.data;
              console.log(this.movies);
            }, error => {

            }
        )

      } catch (error) {
      }
    },
  },
}
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
</style>
