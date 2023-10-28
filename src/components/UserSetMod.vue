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
      <h1 class="login-title">用户设置</h1>
      <!--      <a-form-item-->
      <!--          label="用户名"-->
      <!--          name="username"-->
      <!--          :rules="[{ required: true, message: '请输入用户名'}]"-->
      <!--          class="input-item"-->
      <!--          style="text-align: left;"-->
      <!--      >-->
      <!--        <a-input-->
      <!--            v-model:value="formState.username"-->
      <!--            placeholder="用户名"-->
      <!--        />-->
      <!--      </a-form-item>-->

      <!--      <a-form-item-->
      <!--          label="密码"-->
      <!--          name="password"-->
      <!--          :rules="[{ required: true, message: '请输入密码'}]"-->
      <!--          class="input-item"-->
      <!--          style="text-align: left;"-->
      <!--      >-->
      <!--        <a-input-password-->
      <!--            v-model:value="formState.password"-->
      <!--            placeholder="密码"-->
      <!--        />-->
      <!--      </a-form-item>-->
      <br/>
      <a-form-item style="margin-left: 118px">
        <a-button type="primary" html-type="submit" class="submit-button" style="background-color:deepskyblue">
          修改密码
        </a-button>
        <br/><br/>
        <a-button type="primary" html-type="submit" class="submit-button">
          设置偏好
        </a-button>
        <br/><br/>
        <a-button type="primary" class="submit-button" @click="logout">
          登出
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>
<script setup>
import {reactive} from 'vue';
import {useRouter} from 'vue-router';
import axios from "axios";

const router = useRouter();

const formState = reactive({
  username: '',
  password: '',
  remember: true,
});

const submitForm = async () => {
  // try {
  //   const response = await axios.post('http://localhost:8080/auth/login',
  //       {
  //         username: formState.username,
  //         password: formState.password
  //       });
  //   if (response.data === "success") {
  //     await router.push('/homepage');
  //   } else {
  //
  //   }
  // } catch (error) {
  //   console.error(error);
  // }
}
const logout = () => {
  router.push('/auth/login');
}
const onFinish = values => {
  console.log('Success:', values);
};
const onFinishFailed = errorInfo => {
  console.log('Failed:', errorInfo);
};
</script>
<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f0f0;
}

.login-form {
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  padding: 15px;
  background-color: #ffffff;
  width: 500px;
}

.submit-button {
  margin-top: -10px;
  background-color: #0070f3;
  border: none;
  color: #ffffff;
  border-radius: 5px;
  padding: 0 20px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-button:hover {
  background-color: #0052cc;
}
</style>
