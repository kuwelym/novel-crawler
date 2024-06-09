import { createStore } from "vuex";
import {
  getNovelByGenre,
  getNovelByType,
  searchNovel,
} from "@/services/apiService";
import localStoragePlugin from "./localStoragePlugin";

const initialCurrenPage = 1;
const initialTextStyle = {
  fontSize: "22px",
  fontFamily: "'Palatino Linotype', serif",
  color: "black",
  lineHeight: "140%",
};

export default createStore({
  state: {
    novelList: [],
    pagination: {
      totalPages: 1,
      currentPage: initialCurrenPage,
    },
    filters: {
      type: "",
      genre: "",
      searchText: "",
    },
    textStyle: { ...initialTextStyle },
    isLoading: false,
    readingState: [],
  },
  mutations: {
    SET_NOVEL_LIST(state, novelList) {
      state.novelList = novelList;
    },
    SET_TYPE(state, type) {
      state.filters.type = type;
    },
    SET_GENRE(state, genre) {
      state.filters.genre = genre;
    },
    SET_SEARCH_TEXT(state, searchText) {
      state.filters.searchText = searchText;
    },
    SET_TOTAL_PAGES(state, totalPages) {
      state.pagination.totalPages = totalPages;
    },
    SET_CURRENT_PAGE(state, currentPage) {
      state.pagination.currentPage = currentPage;
    },
    SET_IS_LOADING(state, isLoading) {
      state.isLoading = isLoading;
    },
    RESET_TEXT_STYLE(state) {
      state.textStyle = { ...initialTextStyle };
    },
    SET_FONT_SIZE(state, fontSize) {
      state.textStyle.fontSize = fontSize;
    },
    SET_FONT_FAMILY(state, fontFamily) {
      state.textStyle.fontFamily = fontFamily;
    },
    SET_FONT_COLOR(state, color) {
      state.textStyle.color = color;
    },
    SET_LINE_HEIGHT(state, lineHeight) {
      state.textStyle.lineHeight = lineHeight;
    },
    SET_READING_STATE(state, novelState) {
      const { novelName, novelNameUrl, coverUrl, chapterNumber } = novelState;

      const existingNovelIndex = state.readingState.findIndex(
        (novel) => novel.novelName === novelName
      );

      if (existingNovelIndex !== -1) {
        // If novel is found, update the existing entry
        if (chapterNumber) {
          state.readingState[existingNovelIndex] = {
            ...state.readingState[existingNovelIndex],
            chapterNumber: chapterNumber,
          };
        }
      } else {
        // If novel is not found, add a new entry
        state.readingState.push({
          novelName,
          novelNameUrl,
          coverUrl,
          chapterNumber,
        });
      }
    },
    REMOVE_READING_STATE(state, novelNameUrl) {
      state.readingState = state.readingState.filter(
        (novel) => novel.novelNameUrl !== novelNameUrl
      );
    },
  },
  actions: {
    async updateType({ commit, dispatch }, type) {
      commit("SET_TYPE", type);
      commit("SET_GENRE", "");
      commit("SET_SEARCH_TEXT", "");
      commit("SET_CURRENT_PAGE", initialCurrenPage);

      await dispatch("fetchNovelList");
    },
    async updateGenre({ commit, dispatch }, genre) {
      commit("SET_TYPE", "");
      commit("SET_GENRE", genre);
      commit("SET_SEARCH_TEXT", "");
      commit("SET_CURRENT_PAGE", initialCurrenPage);

      await dispatch("fetchNovelList");
    },
    async updateSearchText({ commit, dispatch }, searchText) {
      commit("SET_NOVEL_LIST", []);
      commit("SET_SEARCH_TEXT", searchText);
      commit("SET_CURRENT_PAGE", initialCurrenPage);

      if (this.state.filters.searchText !== "") {
        await dispatch("searchNovelList");
      } else {
        await dispatch("fetchNovelList");
      }
    },
    async fetchNovelList({ commit, dispatch }) {
      if (this.state.filters.searchText) {
        await dispatch("searchNovelList");
        return;
      }

      commit("SET_IS_LOADING", true);
      commit("SET_SEARCH_TEXT", "");

      let response = null;
      if (this.state.filters.type) {
        response = await getNovelByType(
          this.state.filters.type,
          this.state.pagination.currentPage
        );
      } else {
        response = await getNovelByGenre(
          this.state.filters.genre,
          this.state.pagination.currentPage
        );
      }

      const novelList = response.data.content;
      const totalPages = response.data.totalPages;

      commit("SET_NOVEL_LIST", novelList);
      commit("SET_TOTAL_PAGES", totalPages);
      commit("SET_IS_LOADING", false);
    },
    async searchNovelList({ commit }) {
      commit("SET_IS_LOADING", true);

      const response = await searchNovel(
        this.state.filters.searchText,
        this.state.pagination.currentPage
      );
      const novelList = response.data.content;
      const totalPages = response.data.totalPages;

      commit("SET_NOVEL_LIST", novelList);
      commit("SET_TOTAL_PAGES", totalPages);
      commit("SET_GENRE", "");
      commit("SET_TYPE", "");
      commit("SET_IS_LOADING", false);
    },
    updateTotalPages({ commit }, totalPages) {
      commit("SET_TOTAL_PAGES", totalPages);
    },
    async updateCurrentPage({ commit, dispatch }, currentPage) {
      commit("SET_CURRENT_PAGE", currentPage);

      if (this.state.filters.searchText !== "") {
        await dispatch("searchNovelList");
      } else {
        await dispatch("fetchNovelList");
      }
    },
    resetTextStyle({ commit }) {
      commit("RESET_TEXT_STYLE");
    },
    updateFontSize({ commit }, fontSize) {
      commit("SET_FONT_SIZE", fontSize);
    },
    updateFontFamily({ commit }, fontFamily) {
      commit("SET_FONT_FAMILY", fontFamily);
    },
    updateFontColor({ commit }, color) {
      commit("SET_FONT_COLOR", color);
    },
    updateLineHeight({ commit }, lineHeight) {
      commit("SET_LINE_HEIGHT", lineHeight);
    },
    updateReadingState({ commit }, novelState) {
      commit("SET_READING_STATE", novelState);
    },
    removeReadingState({ commit }, novelNameUrl) {
      commit("REMOVE_READING_STATE", novelNameUrl);
    },
  },
  getters: {
    type: (state) => state.filters.type,
    genre: (state) => state.filters.genre,
    searchText: (state) => state.filters.searchText,
    novelList: (state) => state.novelList,
    totalPages: (state) => state.pagination.totalPages,
    currentPage: (state) => state.pagination.currentPage,
    isLoading: (state) => state.isLoading,
    fontSize: (state) => state.textStyle.fontSize,
    fontFamily: (state) => state.textStyle.fontFamily,
    color: (state) => state.textStyle.color,
    lineHeight: (state) => state.textStyle.lineHeight,
    readingState: (state) => state.readingState,
  },
  plugins: [localStoragePlugin],
});
