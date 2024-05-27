import { createStore } from "vuex";
import { getAllNovels, searchNovel } from "@/services/apiService";
import { scrollToTopSmooth } from "@/utils";

const initialCurrenPage = 1;

export default createStore({
  state: {
    novelList: [],
    filter: "truyen-hot",
    searchText: "",
    totalPages: 1,
    currrentPage: initialCurrenPage,
  },
  mutations: {
    SET_NOVEL_LIST(state, novelList) {
      state.novelList = novelList;
    },
    SET_FILTER(state, filter) {
      state.filter = filter;
    },
    SET_SEARCH_TEXT(state, searchText) {
      state.searchText = searchText;
    },
    SET_TOTAL_PAGES(state, totalPages) {
      state.totalPages = totalPages;
    },
    SET_CURRENT_PAGE(state, currentPage) {
      state.currrentPage = currentPage;
    },
  },
  actions: {
    async updateFilter({ commit, dispatch }, filter) {
      commit("SET_FILTER", filter);
      commit("SET_CURRENT_PAGE", initialCurrenPage);

      await dispatch("fetchNovelList");
    },
    async updateSearchText({ commit, dispatch }, searchText) {
      commit("SET_SEARCH_TEXT", searchText);
      commit("SET_CURRENT_PAGE", initialCurrenPage);

      if (this.state.searchText !== "") {
        await dispatch("searchNovelList");
      } else {
        await dispatch("fetchNovelList");
      }
    },
    async fetchNovelList({ commit }) {
      commit("SET_SEARCH_TEXT", "");

      const respose = await getAllNovels(
        this.state.filter,
        this.state.currrentPage
      );
      const novelList = respose.data.content;
      const totalPages = respose.data.totalPages;

      commit("SET_NOVEL_LIST", novelList);
      commit("SET_TOTAL_PAGES", totalPages);

      scrollToTopSmooth();
    },
    async searchNovelList({ commit }) {
      const respose = await searchNovel(
        this.state.searchText,
        this.state.currrentPage
      );
      const novelList = respose.data.content;
      const totalPages = respose.data.totalPages;

      commit("SET_NOVEL_LIST", novelList);
      commit("SET_TOTAL_PAGES", totalPages);

      scrollToTopSmooth();
    },
    updateTotalPages({ commit }, totalPages) {
      commit("SET_TOTAL_PAGES", totalPages);
    },
    async updateCurrentPage({ commit, dispatch }, currentPage) {
      commit("SET_CURRENT_PAGE", currentPage);

      if (this.state.searchText !== "") {
        await dispatch("searchNovelList");
      } else {
        await dispatch("fetchNovelList");
      }
    },
  },
  getters: {
    filter: (state) => state.filter,
    searchText: (state) => state.searchText,
    novelList: (state) => state.novelList,
    totalPages: (state) => state.totalPages,
    currentPage: (state) => state.currrentPage,
  },
});
