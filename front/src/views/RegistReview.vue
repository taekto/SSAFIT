<template>
  <div>
    <h2>리뷰작성</h2>
    <fieldset>
      <legend>등록</legend>
      <label for="title">제목 : </label>
      <input type="text" id="title" v-model="title" /><br />
      <label for="writer">글쓴이 : </label>
      <input type="text" id="writer" v-model="writer" /><br />
      <label for="content">내용 : </label>
      <textarea id="content" cols="30" rows="10" v-model="content" /><br />
      <button @click="createReview">등록</button>
    </fieldset>
  </div>
</template>

<script>
export default {
  name: 'RegistReview',
  data() {
    return {
        title: '',
        writer: '',
        content: '',
    };
  },
  methods: {
    createReview() {
        const pathName = new URL(document.location).pathname.split("/");
        const id = pathName[pathName.length - 1];
        let review = {
            id: '0',
            youtubeid : id,
            title: this.title,
            writer: this.writer,
            content: this.content,
            viewcount: 0
        };
        this.$store.dispatch('createReview', review);
        
    }
  }
};
</script>

<style>
</style>