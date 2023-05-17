<template>
  <div>
    <header-nav :user="user"></header-nav>
    <router-view></router-view>
    <div>asdf</div>
  </div>
</template>

<script>
import HeaderNav from './components/common/HeaderNav.vue';
import { mapState } from "vuex";

export default {
  name: 'App',

  data: () => ({
    //
  }),
    computed: {
    ...mapState(["user"]),

  },
  components: {
    HeaderNav
  }
};
</script>
