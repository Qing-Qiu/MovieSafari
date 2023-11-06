<template>
  <div class="login-container">
    <a-form
        :model="formState"
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        autocomplete="off"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
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
    <template v-if="currentPage==='recommend'">
      <movie-recommend/>
    </template>
    <template v-else-if="currentPage==='search'">
      <movie-search/>
    </template>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import {SearchOutlined} from "@ant-design/icons-vue";
import axios from "axios";
import MovieRecommend from "@/components/MovieRecommend";
import MovieSearch from "@/components/MovieSearch";

const formState = reactive({
  search: '',
});
let currentPage = 'recommend';
const submitForm = async () => {
  try {
    const response = await axios.post('http://localhost:8080/movie/search',
        {content: formState.search});
    console.log('Frontend sent data:', formState.search);
  } catch (error) {
    console.error("error:", error);
  }
}

const onFinish = values => {
  console.log('Success:', values);
  currentPage = 'search';
};
const onFinishFailed = errorInfo => {
  console.log('Failed:', errorInfo);
};
</script>

<style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  height: 100vh;
  background-color: #f0f0f0;
}

.login-form {
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center; /* Center horizontally */
}

.input-item {
  margin-right: -50px; /* Remove the margin */
}

.submit-button {
  margin-left: 10px; /* Add margin to the left for spacing */
}

</style>