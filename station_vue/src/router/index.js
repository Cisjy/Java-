import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../views/Login.vue'
import home from '../views/Home.vue'
import user from '../views/User.vue'
import station from "@/views/Station.vue";
import train from "@/views/Train.vue";
import order from "@/views/Order.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        name: "login",
        component: login
    },
    {
        path: '/home',
        name: "home",
        component: home
    },
    {
        path: '/user',
        name: "user",
        component: user
    }
    ,
    {
        path: '/station',
        component: station
    },
    {
        path: '/train',
        component: train
    },
    {
        path: '/order',
        component: order
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: function () {
            return import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
        }
    }
]

const router = new VueRouter({
    routes
})

export default router
