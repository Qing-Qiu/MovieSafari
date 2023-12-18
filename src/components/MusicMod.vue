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
            @submit="submitForm"
        >
          <a-form-item
              label=""
              name="search"
              :rules="[]"
          >
            <a-input
                v-model:value="formState.search"
                placeholder="请输入搜索内容"
                size="large"
            />
          </a-form-item>
        </a-form>
      </a-col>
      <a-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4" :xxl="4"></a-col>
    </a-row>
    <br/><br/>
    <a-row>
      <a-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :xxl="8"></a-col>
      <a-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :xxl="8">
        <a-card style="height: 100vh; overflow: auto">
          <a-list item-layout="horizontal" :data-source="song_list">
            <template #renderItem="{ item }">
              <a-list-item>
                <template #actions>
                  <a-button @click="playSong(item.rid)">
                    播放
                  </a-button>
                </template>
                <a-list-item-meta style="text-align: left">
                  <template #title>
                    {{ item.name }}
                  </template>
                  <template #description>
                    {{ item.artist }}
                  </template>
                </a-list-item-meta>
              </a-list-item>
            </template>
          </a-list>
        </a-card>
      </a-col>
      <a-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8" :xxl="8"></a-col>
    </a-row>
  </div>
</template>

<script setup>
import axios from "axios";
import {useCounterStore} from "@/store/store";
import {reactive, ref} from "vue";

const formState = reactive({
  search: '',
});
let song_list = ref([]);
let page_num = ref(1);
let music_url = ref('');
const counter = useCounterStore();

const submitForm = async () => {
  try {
    const response = await axios.get(
        'http://localhost:5000/search?key=' + formState.search + '&pn=' + page_num.value,
        {}).then(
        response => {
          song_list.value = response.data;
          console.log(song_list);
        },
        error => {
        }
    )
  } catch (error) {

  }
}

const playSong = async (rid) => {
  try {
    console.log(rid);
    const response = await axios.get(
        'http://localhost:5000/mp3?rid=' + rid,
        {}).then(
        response => {
          music_url = response.data;
          counter.change(music_url);
          console.log(counter.music_url);
        },
        error => {
        }
    )
  } catch (error) {
    console.log(error);
  }
}
</script>

<style scoped>

</style>