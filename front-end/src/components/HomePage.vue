<template>
  <div class="container mt-4 intro-index" id="intro-index">
    <div class="title-list d-flex">
      <h2>
        <router-link class="text-title" :to="{ name: 'HomePage' }">
          Truyện hot
        </router-link>
      </h2>
    </div>

    <div class="index-intro">
      <div v-for="(novel, index) in hotNovelList" :key="index" class="item">
        <router-link
          :to="{ name: 'NovelDetail', params: { novelName: novel.nameUrl } }"
        >
          <span v-if="novel.isFull" class="full-label"></span>
          <img
            :src="novel.coverUrl"
            :alt="novel.title"
            class="img-responsive item-img"
          />
          <div class="title">
            <h3>{{ novel.title }}</h3>
          </div>
        </router-link>
      </div>
    </div>
  </div>
  <HomePagePart2 :newNovelList="newNovelList" :novelGenres="novelGenres" />
</template>

<script>
import { getHomeData } from "@/services/apiService";
import HomePagePart2 from "./HomePagePart2.vue";
import { mapMutations } from "vuex";

export default {
  name: "HomePage",
  components: {
    HomePagePart2,
  },
  data() {
    return {
      hotNovelList: [],
      newNovelList: [],
      novelGenres: [],
    };
  },
  methods: {
    ...mapMutations(["SET_SEARCH_TEXT"]),
  },
  async mounted() {
    this.SET_SEARCH_TEXT("");
    const response = await getHomeData();

    // Set response to state
    this.hotNovelList = response.data.hotNovelList;
    this.newNovelList = response.data.newNovelList;
    this.novelGenres = response.data.novelGenres;
  },
};
</script>

<style>
.form-select {
  --bs-form-select-bg-img: url(data:image/svg+xml,%3csvg xmlns=%27http://www.w3.org/2000/svg%27 viewBox=%270 0 16 16%27%3e%3cpath fill=%27none%27 stroke=%27%23343a40%27 stroke-linecap=%27round%27 stroke-linejoin=%27round%27 stroke-width=%272%27 d=%27m2 5 6 6 6-6%27/%3e%3c/svg%3e);

  background-image: var(--bs-form-select-bg-img),
    var(--bs-form-select-bg-icon, none) !important;
  /* Các thuộc tính khác */
  display: block;
  width: 100%;
  padding: 0.375rem 2.25rem 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: var(--bs-body-color);
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: var(--bs-body-bg);
  background-repeat: no-repeat;
  background-position: right 0.75rem center;
  background-size: 16px 12px;
  border: var(--bs-border-width) solid var(--bs-border-color);
  border-radius: var(--bs-border-radius);
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.container {
  margin-right: auto;
  margin-left: auto;
  padding-left: 15px;
  padding-right: 15px;
}

.container {
  width: 1170px !important;
}

.intro-index {
  margin-bottom: 15px;
  height: 448px;
  overflow: hidden;
}

.title-list {
  text-align: left;
  height: 40px;
  text-transform: uppercase;
  border-bottom: 1px solid #ccc;
  position: relative;
}

.title-list > h2 {
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  font-size: 20px !important;
  height: 40px;
  line-height: 40px;
  max-width: 100%;
  /* white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; */
  border-bottom: 1px solid #4e4e4e;
}

.text-title {
  color: #4e4e4e;
  text-decoration: none;
}

/* #intro-index h2, h3 {
  margin: 0;
  padding: 0;
  font-size: inherit!important;
  display: inline-block;
} */

.new-select {
  line-height: 40px !important;
  height: 40px !important;
  width: 150px !important;
  border-radius: 0 !important;
  position: absolute !important;
  top: 0 !important;
  right: 0 !important;
  padding: 3px !important;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  text-transform: uppercase !important;
  background-color: transparent !important;
}

.new-select {
  font-size: 20px !important;
  color: #4e4e4e !important;
  border-color: transparent !important;
  border-bottom-color: #4e4e4e !important;
  border: 2px solid !important;
  background-color: #d2d2d2 !important;
}

.form-select:focus {
  border: 2px solid #86b7fe;
  border-color: #86b7fe;
  outline: 0;
  box-shadow: none;
}

.index-intro .item {
  float: left;
  margin: 12px 0 0 12px;
  height: 192px;
  width: 129px;
  position: relative;
  -webkit-transition: all 0.1s ease-in-out;
  transition: all 0.1s ease-in-out;
}

.index-intro .item:hover {
  -webkit-transform: scale(1.05);
  -ms-transform: scale(1.05);
  transform: scale(1.05);
}

.index-intro .item .item-img {
  box-shadow: 0 1px 2px #b5b5b5;
  width: 129px;
  height: 192px;
}

.img-responsive {
  display: block;
  max-width: 100%;
  height: auto;
}

.index-intro div.title {
  position: absolute;
  bottom: 0;
  width: 100%;
  color: #f4f4f4;
  background-color: rgba(0, 0, 0, 0.6);
  padding: 5px 0;
  text-shadow: 1px 2px 2px #000;
  font-size: 12px;
  display: block;
  margin-top: 2px;
  text-align: center;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
}

.full-label {
  width: 34px;
  height: 50px;
  position: absolute;
  display: block;
  margin: 140px 0 0 -7px;
  z-index: 1;
  background: transparent url(//static.8cache.com/img/full-label.png) no-repeat;
}
</style>
