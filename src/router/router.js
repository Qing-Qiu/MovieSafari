// router.js

import {createRouter, createWebHistory} from 'vue-router';

// 导入你的组件
import LoginPage from '../views/LoginPage';
import HomePage from '../views/HomePage';
import RegisterPage from '../views/RegisterPage';
import MovieTemp from '@/components/MovieTemp'
import PersonDetail from "@/components/PersonDetail";

const routes = [
    {path: '/', redirect: '/auth/login'},
    {path: '/auth/login', component: LoginPage},
    {path: '/auth/register', component: RegisterPage},
    {path: '/homepage', component: HomePage},
    {path: '/movie', component: MovieTemp},
    {path: '/person', component: PersonDetail},
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
