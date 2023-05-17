<template>
    <header>
    <nav class="header-nav">
      <div>
        <router-link to="/" class="logo">ssafit</router-link>
      </div>
      <div>
        <button @click="logout" v-if="this.user>0">로그아웃</button>
        <router-link v-else to="/login">로그인</router-link>
        <div>{{ token }}</div>
      </div>
    </nav>
  </header>

</template>

<script>
import { mapState } from "vuex";
export default {
 name: "HeaderNav",
 data() {
  return {token:''}
 },
  methods: {
    logout() {
      this.$store.commit("LOGOUT");
    },
  },
  computed: {
    ...mapState(["user"]),

  },
  created() {
    this.token = sessionStorage.getItem("access-token");
    console.log(this.token);
  }
};
</script>

<style>
header {
  height: 70px;
  background-color: #9f98d4;
  line-height: 70px;
  padding: 0px 30px;
}

header a {
  margin: 10px;
  text-decoration: none;
  color: white;
}

.header-nav {
  display: flex;
  justify-content: space-between;
}

.logo {
  display: inline-block;
  font-size: 2rem;
  font-weight: bold;
  color: white;
  margin: 0;
}
</style>