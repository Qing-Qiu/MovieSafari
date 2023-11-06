<template>
  <div style="overflow: scroll" v-if="this.recommendedImages.length && !detail">
    <div v-for="(category, index) in categories" :key="index" class="guess-you-like">
      <h2>{{ category.name }}</h2>
      <a-button @click="refresh(index)">换一批</a-button>
      <div class="image-grid">
        <a-card
            v-for="(item, itemIndex) in recommendedImages.slice(index * 8, index * 8 + 8)"
            :key="itemIndex"
            class="image-card"
            hoverable
            @click="watchDetail(item.id)"
        >
          <div class="card-content" v-if="this.recommendedImages.length">
            <img :src="item.image" :alt="item.title" referrerpolicy="no-referrer"/>
            <a-card-meta :title="item.title" :description="item.description"/>
          </div>
        </a-card>
      </div>
    </div>
  </div>
  <div v-else-if="detail">
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
      <img :src="this.movie_content.img" :alt="this.movie_content.name" referrerpolicy="no-referrer"/>
    </a-card>
  </div>
</template>

<script>
import axios from "axios";
import {ArrowLeftOutlined} from "@ant-design/icons-vue";

export default {
  components: {
    ArrowLeftOutlined
  },
  data() {
    return {
      detail: false,
      movie: 0,
      movie_content: [],
      recommendedImages: [],
      categories: [
        {name: "猜你喜欢", items: "123"},
        {name: "剧情片", items: "123"},
        {name: "爱情片", items: "123"},
        {name: "动作片", items: "123"},
      ],
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
              // console.log(response.data[0]);
              for (let i = 0; i < 32; i++) {
                this.recommendedImages.push({
                  title: response.data[i].name,
                  id: response.data[i].movieID,
                  image: response.data[i].img
                });
              }
              // console.log(response.data[0], response.data[1]);
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
    },

    async watchDetail(id) {
      console.log(id);
      this.detail = true;
      this.movie = id;
      try {
        const response = await axios.post('http://localhost:8080/movie/detail',
            {movie: this.movie}).then(
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
    }
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
