import {createRouter, createWebHistory} from 'vue-router';
import LoginPage from '../views/LoginPage';
import HomePage from '../views/HomePage';
import RegisterPage from '../views/RegisterPage';
import DynamicMovie from '../components/DynamicMovie';
import AboutMod from '../components/AboutMod'
import MovieCenterMod from "../components/MovieCenterMod";
import RecommendSearchMod from "../components/RecommendSearchMod";
import ModelMod from "../components/ModelMod";
import ChartMod from "../components/ChartMod";
import PreferMod from "../components/PreferMod";

const routes = [
    {path: '/', redirect: '/auth/login'},
    {path: '/auth/login', component: LoginPage},
    {path: '/auth/register', component: RegisterPage},
    {path: '/homepage', component: RecommendSearchMod},
    {path: '/about', component: AboutMod},
    {path: '/center', component: MovieCenterMod},
    {path: '/model', component: ModelMod},
    {path: '/chart', component: ChartMod},
    {path: '/prefer', component: PreferMod},
    {
        path: '/movie/:movie_id',
        name: 'DynamicMovie',
        component: DynamicMovie,
        props: true
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
