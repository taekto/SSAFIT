<template>
  <div>
    <br>
    <div class="text-center">
      <h2>
        <b-icon icon="star-fill" animation="fade" font-scale="1"></b-icon>
        <b-icon icon="star-fill" animation="fade" font-scale="1"></b-icon>
        십련아 운동좀해라 ㅋ ㅋ
        <b-icon icon="star-fill" animation="fade" font-scale="1"></b-icon>
        <b-icon icon="star-fill" animation="fade" font-scale="1"></b-icon>
      </h2>
      <br>
      <v-btn
      
      @click="setVideo(part)"
      v-for="(part, i) in parts"
      :key="i"
      ><span>{{ part }}</span></v-btn
    >
    <br>
    <br>
      <input
        @keyup.enter="setVideo"
        type="text"
        v-model="part"
        placeholder="부위를 검색하세요"
      />
      <v-btn @click="setVideo2">검색</v-btn>
    </div>
    <br />
    <hr />
    <div style="display: flex;">
      <v-card
        v-for="(video, index) in videos"
        :key="index"
        class="mx-auto"
        max-width="400"
        style="margin-left: 3%;"
      >
        <v-img
          class="white--text align-end"
          height="200px"
          :src="`https://img.youtube.com/vi/${video.id}/0.jpg`"
        ></v-img>

        <v-card-subtitle class="pb-1">
          {{ video.title }}
        </v-card-subtitle>
        <v-card-title>{{ video.channelName }}</v-card-title>
        <v-card-text class="text--primary">
          <div>부위: {{ video.part }}</div>
          <div>조회수: 0</div>
        </v-card-text>

        <v-card-actions>
          <router-link :to="`/video/${video.id}`">
            <b-button variant="outline-warning">상세보기</b-button>
          </router-link>

          <b-icon
            v-if="!isLiked(video)"
            icon="suit-heart"
            font-scale="1.5"
            style="margin-left: 65%;"
            @click="toggleLike(video)"
          ></b-icon>
          <b-icon
            v-else
            icon="suit-heart-fill"
            color="red"
            font-scale="1.5"
            style="margin-left: 65%;"
            @click="toggleLike(video)"
          ></b-icon>
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "HomeView",
  data() {
    return {
      parts: ["전신", "상체", "하체", "복부"],
      part: "",
    };
  },
  methods: {
    setVideo(part) {
      this.$store.dispatch("SetVideo", part);
    },
    setVideo2() {
      this.$store.dispatch("SetVideo", this.part);
    },
    toggleLike(video) {
      if (!this.isLiked(video)) {
        this.$set(video, "liked", true);
      } else {
        this.$delete(video, "liked");
      }
    },
    isLiked(video) {
      return Object.prototype.hasOwnProperty.call(video, "liked");
    },
  },
  computed: {
    ...mapState(["videos"]),
  },
};
</script>

<style>
.first {
  margin-left: 4%;
}
.part {
  margin-right: 3%;
}
</style>