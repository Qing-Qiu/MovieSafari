<template>
  <HomePage>
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
          style="text-align: center; margin-left: 350px"
      >
        <a-input-search
            v-model:value="formState.search"
            placeholder="请输入问题"
            size="large"
            @search="submitForm"
        />
        <a-radio-group v-model:value="value">
          <a-radio :value="1">常规</a-radio>
          <a-radio :value="2">影片</a-radio>
          <a-radio :value="3">影人</a-radio>
        </a-radio-group>
      </a-form-item>
    </a-form>
    <a-list :loading="loading" style="margin-top:200px;margin-left: 200px;margin-right: 200px;">
      {{ this.result }}
    </a-list>
  </HomePage>
</template>
<script setup>
import HomePage from "@/views/HomePage";
</script>
<script>
import axios from "axios";

export default {
  data() {
    return {
      formState: {
        search: '',
      },
      value: 1,
      result: null,
      loading: false,
      msg: '',
    }
  },
  methods: {
    async submitForm() {
      try {
        this.loading = true;
        console.log(this.formState.search);
        if (/^\s*$/.test(this.formState.search)) {
          this.result = "请输入有效字符。"
          this.loading = false;
          return;
        }
        this.msg = this.formState.search;
        if (this.value === 2) {
          this.msg = "影片《" + this.msg + "》";
        } else if (this.value === 3) {
          this.msg = "影人 " + this.msg;
        }
        console.log(this.msg);
        const response = await axios.post('http://localhost:8080/api/chat',
            {msg: this.msg}).then(
            response => {
              this.result = response.data.data;
              console.log(this.result);
              this.loading = false;
            }, error => {

            }
        )

      } catch (error) {
      }
    },
  }
}
</script>

<style scoped>
</style>