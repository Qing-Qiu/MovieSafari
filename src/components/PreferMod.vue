<template>
  <HomePage>
    <div class="login-container">
      <a-form
          :model="formState"
          name="basic"
          :label-col="{ span: 8 }"
          :wrapper-col="{ span: 16 }"
          autocomplete="off"
          class="login-form"
      >
        <h1 class="login-title">设置偏好</h1>
      </a-form>
    </div>
  </HomePage>
</template>
<script setup>
import HomePage from "@/views/HomePage";
</script>
<script>

export default {
  data() {
    return {
      formState: {},
    }
  },
  methods: {},
}
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

.input-item {
  margin-left: 100px; /* 调整左边距 */
}

</style>