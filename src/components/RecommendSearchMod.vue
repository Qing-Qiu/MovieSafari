<template>
  <div class="login-container">
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
          class="input-item"
          style="text-align: center; flex: 1;"
      >
        <a-input
            v-model:value="formState.search"
            placeholder="输入影人或电影名称来搜索"
            size="large"
            style="width: 100%"
        />
      </a-form-item>
      <a-form-item class="search-button-item">
        <a-button type="primary" html-type="submit" class="submit-button" size="large">
          <SearchOutlined/>
        </a-button>
      </a-form-item>
    </a-form>
    <div style="overflow: scroll" v-if="this.recommendedImages.length && !detail && !search">
      <div class="guess-you-like">
        <h2>猜你喜欢</h2>
        <a-button @click="refresh(0)">换一批</a-button>
        <div class="image-grid">
          <a-card
              v-for="(item, itemIndex) in recommendedImages.slice(0, 8)"
              :key="itemIndex"
              class="image-card"
              hoverable
              @click="watchDetail(item.id)"
          >
            <div class="card-content" v-if="this.recommendedImages.length">
              <img :src="item.image" :alt="item.title" referrerpolicy="no-referrer" @error="imgError(item)"/>
              <a-card-meta :title="item.title" :description="item.description"/>
            </div>
          </a-card>
        </div>
      </div>
    </div>
    <div v-else-if="detail && !search">
      <a-button @click="backward()">
        <ArrowLeftOutlined/>
      </a-button>
      <a-card class="image-card2">
        <a-typography-title>{{ this.movie_content.name }}</a-typography-title>
        <a-typography>导演：{{ this.movie_content.director }}</a-typography>
        <a-typography>主演：{{ this.movie_content.actor }}</a-typography>
        <a-typography>类型：{{ this.movie_content.tag }}</a-typography>
        <a-typography>评分：{{ this.movie_content.rate }}</a-typography>
        <a-rate v-model:value="this.movie_content.rate" allow-half/>
        <a-typography>评价人数：{{ this.movie_content.popular }}</a-typography>
        <a-typography>年份：{{ this.movie_content.year }}</a-typography>
        <a-typography>制片国家/地区：{{ this.movie_content.country }}</a-typography>
        <a-typography>剧情简介：{{ this.movie_content.summary }}</a-typography>
        <img :src="this.movie_content.img" :alt="this.movie_content.name" referrerpolicy="no-referrer"
             @error="imgError2(this.movie_content)"/>
      </a-card>
    </div>
    <div v-else-if="search">
      <a-button @click="backward()">
        <ArrowLeftOutlined/>
      </a-button>
      <div v-if="this.movies===null">
        共查询到0部电影
      </div>
      <div v-else>
        以下展示{{ Math.min(20, this.movies.length) }}部电影。
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
          <a-typography>制片国家/地区：{{ movie.country }}</a-typography>
          <a-typography>剧情简介：{{ movie.summary }}</a-typography>
          <img :src="movie.img" :alt="movie.name" referrerpolicy="no-referrer" @error="imgError2(movie)"/>
        </a-card>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {ArrowLeftOutlined, SearchOutlined} from "@ant-design/icons-vue";

export default {
  components: {
    ArrowLeftOutlined, SearchOutlined
  },
  data() {
    return {
      formState: {
        search: '',
      },
      detail: false,
      search: false,
      movies: {},
      movie_id: 0,
      movie_content: [],
      recommendedImages: [],
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
              for (let i = 0; i < 32; i++) {
                this.recommendedImages.push({
                  title: response.data[i].name,
                  id: response.data[i].movieID,
                  image: response.data[i].img
                });
              }
              console.log(this.recommendedImages);
            },
            error => {
            }
        )
      } catch (error) {

      }
    },

    refresh(index) {
      this.recommendedImages.splice(index * 8, 8);
      this.fetchData();
    },

    backward() {
      this.detail = false;
      this.search = false;
    },

    imgError(item) {
      item.image = require('../assets/meow.jpg')
    },

    imgError2(item) {
      item.img = require('../assets/meow.jpg')
    },

    async watchDetail(id) {
      console.log(id);
      this.detail = true;
      this.movie_id = id;
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
    },

    async submitForm() {
      this.search = true;
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

.image-card2 {
  width: 50%; /* Adjust this to control the card width */
  /*margin: 100px 10px 100px 10px;*/
  margin: auto;
}

.card-content {
  display: flex;
  flex-direction: column;
}

img {
  max-width: 100%;
}
</style>
