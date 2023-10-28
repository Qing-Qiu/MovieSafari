<template>
  <div class="login-container">
    <a-form
        ref="formRef"
        :model="formState"
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        :rules="rules"
        autocomplete="off"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
        class="login-form"
        @validate="handleValidate"
    >
      <h1 class="login-title">用户注册</h1>
      <a-form-item
          label="用户名"
          name="username"
          class="input-item"
          style="text-align: left;"
      >
        <a-input
            v-model:value="formState.username"
            placeholder="用户名"
        />
      </a-form-item>

      <a-form-item
          label="密码"
          name="password"
          class="input-item"
          style="text-align: left;"
      >
        <a-input-password
            v-model:value="formState.password"
            placeholder="密码"
        />
      </a-form-item>

      <a-form-item
          label="确认密码"
          name="password2"
          class="input-item"
          style="text-align: left;"
      >
        <a-input-password
            v-model:value="formState.password2"
            placeholder="确认密码"
        />
      </a-form-item>

      <div class="center-content">
        <a-form-item>
          <a-button type="primary" html-type="submit" class="submit-button">
            注册
          </a-button>
        </a-form-item>
      </div>
      <a-form-item style="margin-left: 110px;">
        <a-typography style="display: inline;">已经有账号了，赶紧去</a-typography>
        <a-typography-link style="display:inline;" @click="login()">
          登录
        </a-typography-link>
      </a-form-item>
    </a-form>
  </div>
</template>
<script setup>
import {reactive, ref} from 'vue';
import {useRouter} from 'vue-router';
import axios from "axios";

const router = useRouter();
const formRef = ref();

const formState = reactive({
  username: '',
  password: '',
  password2: '', //在前端验证是否和password一致
});

const checkPwd = async (_rule, value) => {
  if (value === '')
    return Promise.reject('请再次输入密码');
  else if (value !== formState.password)
    return Promise.reject('两次密码不一致');
  return Promise.resolve();
}

const rules = {
  username: [
    {
      required: true,
      message: '请输入用户名',
      trigger: 'change'
    }
  ],
  password: [
    {
      required: true,
      message: '请输入6-20位字母和数字组成的密码',
      pattern: '^[A-Za-z0-9]{6,20}$',
      trigger: 'change'
    }
  ],
  password2: [
    {
      required: true,
      trigger: 'change',
      validator: checkPwd,
    }
  ]
}

const handleValidate = (...args) => {
  console.log(args);
}

const login = () => {
  router.push('/auth/login');
}

const onFinish = async values => {
  console.log('Success:', values);
  const response = await axios.post('http://localhost:8080/auth/register',
      {
        username: formState.username,
        password: formState.password
      });
  if (response.data === "success") {
    alert("注册成功");
    await router.push('/auth/login');
  } else if (response.data === "existed") {
    alert("用户名已注册");
    window.location.reload();
  } else {
    alert("错误");
    window.location.reload();
  }

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

.center-content {
  display: flex;
  flex-direction: column; /* 显示为两列 */
  align-items: center;
}

.input-item {
  margin-left: -100px; /* 调整左边距 */
}

.remember-checkbox {
  margin-top: -10px;
  display: flex;
  align-items: center;
  white-space: nowrap;
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
