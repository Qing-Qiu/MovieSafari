<template>
  <div>
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
    <template v-else-if="detail && !search">
      <a-button @click="backward()">
        <ArrowLeftOutlined/>
      </a-button>
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
      console.log(id);
      this.detail = true;
      this.search = false;
      this.movie_id = id;
      console.log(this.current1);
      console.log(this.limit1);
      console.log(this.offset1);
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
