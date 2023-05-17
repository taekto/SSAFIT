<template>
  <v-card>
    <v-list>
      <v-list-item>
        <v-list-item-title class="font-weight-bold">번호:</v-list-item-title>
        <v-list-item-title>{{ review.id }}</v-list-item-title>
      </v-list-item>
      <v-list-item>
        <v-list-item-title class="font-weight-bold">제목:</v-list-item-title>
        <v-list-item-title>{{ review.title }}</v-list-item-title>
      </v-list-item>
      <v-list-item>
        <v-list-item-title class="font-weight-bold">글쓴이:</v-list-item-title>
        <v-list-item-title>{{ review.writer }}</v-list-item-title>
      </v-list-item>
      <v-list-item>
        <v-list-item-title class="font-weight-bold">내용:</v-list-item-title>
        <v-list-item-title>{{ review.content }}</v-list-item-title>
      </v-list-item>
      <v-list-item>
        <v-list-item-title class="font-weight-bold">조회수:</v-list-item-title>
        <v-list-item-title>{{ review.viewcount }}</v-list-item-title>
      </v-list-item>
    </v-list>

    <v-card-actions>
      <v-btn color="orange" text @click="updateReview">수정</v-btn>
      <v-btn color="orange" text @click="deleteReview">삭제</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data() {
    return {
      review: null,
    };
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
      this.$router.push({ name: 'ReviewUpdate', params: { id: this.review.id } });
    },
    deleteReview() {
      this.$store.dispatch('DeleteReview', this.review);
    },
  },
};
</script>
<style>
  .table-wrapper {
    max-width: 700px;
    margin: 20px auto;
    border: 1px solid #ccc;
    border-radius: 5px;
    overflow: hidden;
  }

  .table {
    width: 100%;
    table-layout: fixed;
    border-collapse: collapse;
  }

  .table thead th {
    background-color: #333;
    color: #fff;
    text-align: left;
    padding: 10px;
    font-weight: normal;
  }

  .table tbody tr:nth-child(odd) {
    background-color: #f5f5f5;
  }

  .table tbody tr td {
    padding: 10px;
    vertical-align: middle;
  }

  .table tbody tr td:last-child {
    text-align: right;
  }

  .table-btns {
    display: flex;
    justify-content: flex-end;
    margin: 20px;
  }

  .table-btn {
    margin-left: 10px;
  }
</style>