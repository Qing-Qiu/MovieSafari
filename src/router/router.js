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
import DynamicPerson from "@/components/DynamicPerson";

const routes = [
    {
        path: '/auth/login',
        component: LoginPage,
        meta: {
            dependent: true,
        }
    },
    {
        path: '/auth/register',
        component: RegisterPage,
        meta: {
            dependent: true,
        }
    },
    {
        path: '/',
        redirect: '/auth/login',
    },
    {
        path: '/home',
        name: 'Home',
        component: () => import('../components/RecommendSearchMod'),
        meta: {
            dependent: false,
        }
    },
    {
        path: '/about',
        name: 'About',
        component: AboutMod,
        meta: {
            dependent: false,
        }
    },
    {
        path: '/center',
        component: MovieCenterMod,
        meta: {
            dependent: false,
        }
    },
    {
        path: '/model',
        component: ModelMod,
        meta: {
            dependent: false,
        }
    },
    {
        path: '/chart',
        component: ChartMod,
        meta: {
            dependent: false,
        }
    },
    {
        path: '/prefer',
        component: PreferMod,
        meta: {
            dependent: false,
        }
    },
    {
        path: '/movie/:movie_id',
        name: 'DynamicMovie',
        component: DynamicMovie,
        props: true,
        meta: {
            dependent: false,
        }
    },
    {
        path: '/person/:person_id',
        name: 'DynamicPerson',
        component: DynamicPerson,
        props: true,
        meta: {
            dependent: false,
        }
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
