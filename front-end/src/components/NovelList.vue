<template>
  <div class="container list-page" id="list-page">
    <div class="row">
      <div class="list list-truyen col-12">
        <div class="title-list">
          <h2>{{ titleList.replace(/-/g, " ") }}</h2>
        </div>
        <NovelItem
          v-for="(novel, index) in novelList"
          :key="index"
          :novel="novel"
        />
        <h2 v-if="novelList?.length === 0 && !isLoading" class="not-found-text">
          Không có truyện nào được tìm thấy
        </h2>
        <PaginationComponent v-if="totalPages > 1 && !isLoading" class="my-3" />
      </div>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions, mapMutations, mapGetters } from "vuex";
import NovelItem from "./NovelItem";
import { scrollToTopSmooth } from "@/utils";
import PaginationComponent from "./Pagination";

export default {
  name: "NovelList",
  components: {
    NovelItem,
    PaginationComponent,
  },
  computed: {
    ...mapGetters(["totalPages"]),
    ...mapState({
      novelList: (state) => state.novelList,
      isLoading: (state) => state.isLoading,
    }),
    titleList() {
      return this.$route.params.type || this.$route.params.genre || "SEARCH";
    },
  },
  methods: {
    ...mapActions(["fetchNovelList"]),
    ...mapMutations(["SET_SEARCH_TEXT"]),
  },
  async mounted() {
    if (this.$route.path !== "/search") {
      this.SET_SEARCH_TEXT("");
    }
    await this.fetchNovelList();
  },
  watch: {
    novelList() {
      scrollToTopSmooth();
    },
  },
};
</script>

<style>
.not-found-text {
  text-align: center;
  margin-top: 30px;
}
</style>
