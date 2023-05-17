import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import VideoDetail from '../views/VideoDetail.vue'
import ReviewDetail from '../views/ReviewDetail.vue'
import ReviewUpdate from '../views/ReviewUpdate.vue'
import RegistReview from '../views/RegistReview.vue'
import LoginView from '../views/LoginView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: "Home",
    component: HomeView
  },
  {
    path: '/login',
    component: LoginView
  },
  {
    path: '/video/:id',
    name: "VideoDetail",
    component: VideoDetail
  },
  {
    path: '/registreview/:id',
    component: RegistReview,
  },
  {
    path: '/review/:id',
    component: ReviewDetail
  },
  {
    path: '/review/update/:id',
    name: "ReviewUpdate",
    component: ReviewUpdate
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
