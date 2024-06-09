import { createRouter, createWebHistory } from "vue-router";

import HomePage from "../components/HomePage.vue";
import NovelDetail from "../components/NovelDetail.vue";
import NovelList from "../components/NovelList.vue";
import NovelChapter from "../components/NovelChapter.vue";

const routes = [
  {
    path: "/",
    name: "HomePage",
    component: HomePage,
  },
  {
    path: "/types/:type",
    name: "NovelListType",
    component: NovelList,
  },
  {
    path: "/genres/:genre",
    name: "NovelListGenre",
    component: NovelList,
  },
  {
    path: "/search",
    name: "NovelListSearch",
    component: NovelList,
  },
  {
    path: "/detail/:novelName",
    name: "NovelDetail",
    component: NovelDetail,
  },
  {
    path: "/detail/:novelName/:chapterNumber",
    name: "NovelChapter",
    component: NovelChapter,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
