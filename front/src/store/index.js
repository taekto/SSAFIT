import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";
import router from "@/router";
// import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user:[],
    videos: [],
    reviews: [],
    review: {},
  },
  getters: {
  },
  mutations: {
    LOGOUT(state) {
      state.user = [];
      sessionStorage.setItem("access-token",null);
      // router.push({ name: "Home"});
      console.log("session 날림")
    },
    USER_LOGIN(state,loginuserId) {
      console.log(loginuserId);
      state.user = loginuserId;
    },

    SET_VIDEO: function (state, videos) {
      state.videos = videos;
    },
    SET_REVIEWS: function (state, reviews) {
      state.reviews = reviews;
    },
    UPDATE_REVIEW(state, review) {
      state.board = review;
    },
  },
  actions: {
    userLogin({ commit }, user) {
      const API_URL = `http://localhost:9999/api/login`;
      axios({
        url: API_URL,
        method: "POST",
        params: user,
      })
        .then((res) => {
          console.log(res);
          sessionStorage.setItem("access-token", res.data["access-token"]);
          commit("USER_LOGIN",res.data["loginUserId"]); //필요하다면 데이터도 같이 올려보내라
          router.push({ name: "Home"});
        })
        .catch((err) => {
          console.log(err);
        });
    },

    SetVideo: function ({ commit }, part) {
      const API_URL = `http://localhost:9999/api/video`;
      axios({
        url: API_URL,
        method: "POST",
        params : {
          part: part,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        
      })
        .then((res) => {
          commit("SET_VIDEO", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    GetReviews: function ({ commit }, youtubeid) {
      const API_URL = `http://localhost:9999/api/review`;
      axios({
        url: API_URL,
        method: "GET",
        params : {
          youtubeid: youtubeid,
        },
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        
      })
        .then((res) => {
          commit("SET_REVIEWS", res.data);
          console.log(youtubeid)
          console.log(res.data)
        })
        .catch((err) => {
          console.log(err);
        });
    },
    createReview({commit}, review) {
      const API_URL = `http://localhost:9999/api/review`;
      const youtubeid = review.youtubeid;
      axios({
        url : API_URL,
        method: "POST",
        params : review,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
      .then(()=>{
        commit
        console.log(youtubeid);
        router.push({ name: "VideoDetail", params: { id: youtubeid} });
      })
    },
    DeleteReview: function ({ commit }, review) {
      const API_URL = `http://localhost:9999/api/delete`;
      const youtubeid = review.youtubeid;
      axios({
        url: API_URL,
        method: "DELETE",
        params : {
          reviewid: review.id,
        },
        headers: {

          
          "access-token": sessionStorage.getItem("access-token"),
        },
        
      })
        .then(() => {
          router.push({ name: "VideoDetail", params: { id: youtubeid} });
          commit();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    UpdateReview: function ({ commit }, review) {
      const API_URL = `http://localhost:9999/api/update`;
      const youtubeid = review.youtubeid;
      axios({
        url: API_URL,
        method: "PUT",
        params : review,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
        
      })
        .then(() => {
          commit("UPDATE_REVIEW", review);
          console.log("ds");
          router.push({ name: "VideoDetail", params: { id: youtubeid} });
        })
        .catch((err) => {
          console.log(err);
        });
    }
  },
  modules: {
  },
  // plugins: [createPersistedState()],
})
