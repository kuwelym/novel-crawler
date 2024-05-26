<template>
  <div class="row my-3">
    <v-pagination
      :model-value="currentPage"
      :pages="totalPages"
      :range-size="1"
      active-color="#DCEDFF"
      @update:modelValue="handleChangePage"
    />
  </div>
</template>

<script>
import VPagination from "@hennge/vue3-pagination";
import "@hennge/vue3-pagination/dist/vue3-pagination.css";
import { mapState, mapActions } from "vuex";

export default {
  name: "PaginationComponent",
  components: {
    VPagination,
  },
  computed: {
    ...mapState({
      currentPage: (state) => state.currrentPage,
      totalPages: (state) => state.totalPages,
    }),
  },
  methods: {
    ...mapActions(["updateCurrentPage"]),
    handleChangePage(newPage) {
      this.updateCurrentPage(newPage);
    },
  },
};
</script>

<style scoped>
.Pagination {
  justify-content: center;
}
</style>
