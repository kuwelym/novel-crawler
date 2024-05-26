<template>
  <div class="container">
    <NovelItem
      v-for="(novel, index) in novelList"
      :key="index"
      :novel="novel"
    />
    <h2 v-if="!novelList" class="not-found-text">
      Không có truyện nào được tìm thấy
    </h2>

    <PaginationComponent v-if="totalPages > 1" />
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import NovelItem from "./NovelItem";
import PaginationComponent from "./Pagination";

export default {
  name: "NovelList",
  components: {
    NovelItem,
    PaginationComponent,
  },
  computed: {
    ...mapState({
      novelList: (state) => state.novelList,
      totalPages: (state) => state.totalPages,
    }),
  },
  methods: {
    ...mapActions(["fetchNovelList"]),
  },
  async mounted() {
    await this.fetchNovelList();
  },
};
</script>

<style>
.not-found-text {
  text-align: center;
  margin-top: 30px;
}
</style>
