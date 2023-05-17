<template>

  
  <div>
    <div>
    <h2>게시글 수정</h2>
    <fieldset>
      <legend>수정</legend>
      <label for="title">제목 : </label>
      <input type="text" id="title" v-model="review.title" /><br />
      <label for="writer">쓴이 : </label>
      <input type="text" id="writer" readonly v-model="review.writer" /><br />
      <label for="content">내용 : </label>
      <textarea
        id="content"
        cols="30"
        rows="10"
        v-model="review.content"
      ></textarea>
      <button @click="updateReview">수정</button>
    </fieldset>
  </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
    data() {
        return {
          review:null
        }
    },
  computed: {
    ...mapState(['reviews']),
  },
  created() {
    const pathName = new URL(document.location).pathname.split('/');
    const reviewId = pathName[pathName.length - 1];
    this.review = this.reviews.find(review => review.id.toString() === reviewId);
  },
  methods: {
    updateReview() {
      let updateReview = {
        id: this.review.id,
        title: this.review.title,
        writer: this.review.writer,
        content: this.review.content,
        youtubeid: this.review.youtubeid,
        viewcount: this.review.viewcount
      };
      this.$store.dispatch('UpdateReview', updateReview);
    },
  },
}
</script>

<style>
</style>