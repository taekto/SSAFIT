<template>
  <div>
    <div style="display: flex; justify-content: center;">
    <iframe width="800" height="500" :src="`https://www.youtube.com/embed/${videoid}`" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
    </div>
    <hr>
    <v-simple-table class="table-width">
    <template v-slot:default>
      <thead>
        <tr>
          <th>
            번호
          </th>
          <th>
            제목
          </th>
          <th>
            글쓴이
          </th>
          <th>
            조회수
          </th>
        </tr>
      </thead>
      <tbody class="text-center">
        <tr
          v-for="review in reviews"
          :key="review.id"
        ><td style="text-align: center;">{{ review.id }}</td>
        <router-link :to="`/review/${review.id}`"><td style="text-align: center;">{{ review.title }}</td></router-link>
          <td style="text-align: center;">{{ review.writer }}</td>
          <td style="text-align: center;">{{ review.viewcount }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
  <router-link :to="`/registreview/${videoid}`"
      ><v-btn depressed> 글작성 </v-btn></router-link
    >
  </div>
  
</template>

<script>
import { mapState } from 'vuex';
export default {
    data() {
        return {
         
            videoid:""
        }
    },
  computed: {
    ...mapState(['reviews']),
  },
  created() {
    const pathName = new URL(document.location).pathname.split('/');
    const id = pathName[pathName.length - 1];
    this.$store.dispatch('GetReviews', id);
    this.videoid=id
  },
//   methods: {
//     moveUpdate() {
//       this.$router.push({ name: 'boardUpdate' });
//     },
//     deleteBoard() {
//       this.$store.dispatch('deleteBoard', this.board.id);
//     },
//   },
}
</script>

<style>
.table-width {
  max-width: 1000px;
  margin: 0 auto; 
}
</style>