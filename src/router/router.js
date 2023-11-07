// router.js

import {createRouter, createWebHistory} from 'vue-router';

// 导入你的组件
import LoginPage from '../views/LoginPage';
import HomePage from '../views/HomePage';
import RegisterPage from '../views/RegisterPage';

const routes = [
    {path: '/', redirect: '/auth/login'},
    {path: '/auth/login', component: LoginPage},
    {path: '/auth/register', component: RegisterPage},
    {path: '/homepage', component: HomePage},
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
