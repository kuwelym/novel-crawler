<template>
  <div class="container clearfix list-index" id="list-index">
    <div
      class="list list-truyen list-new col-xs-12 col-sm-12 col-md-8 col-truyen-main"
    >
      <div class="title-list">
        <h2>
          <router-link
            class="text-title"
            :to="{ name: 'NovelListType', params: { type: 'truyen-moi' } }"
          >
            Truyện mới cập nhật
          </router-link>
        </h2>
      </div>

      <div class="row" v-for="(novel, index) in newNovelList" :key="index">
        <div class="col-xs-9 col-sm-6 col-md-5 col-title">
          <h3>
            <router-link
              :to="{
                name: 'NovelDetail',
                params: { novelName: novel.nameUrl },
              }"
            >
              {{ novel.title }}
            </router-link>
          </h3>
          <span class="label-title label-full"></span>
          <span class="label-title label-hot"></span>
        </div>
        <div class="hidden-xs col-sm-3 col-md-3 col-cat">
          <router-link
            v-for="(genre, index) in novel.genres"
            :key="index"
            :to="{
              name: 'NovelListGenre',
              params: { genre: genre.url },
            }"
          >
            {{ genre.name }}
            <span v-if="index < novel.genres.length - 1">, </span>
          </router-link>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-2 col-chap text-info">
          <router-link
            :to="{
              name: 'NovelChapter',
              params: {
                novelName: novel.nameUrl,
                chapterNumber: novel.newestChapter,
              },
            }"
          >
            <span class="chapter-text">
              <span>Chương</span>
            </span>
            {{ novel.newestChapter }}
          </router-link>
        </div>
        <div class="hidden-xs hidden-sm col-md-2 col-time">
          {{ novel.timeOfNewEstChapter }}
        </div>
      </div>

      <div class="col-md-4 col-truyen-side">
        <div class="visible-md-block visible-lg-block text-center">
          <div class="list list-truyen list-history col-xs-12">
            <div class="title-list">
              <h2>Truyện đang đọc</h2>
            </div>
            <div
              class="row"
              v-for="(novel, index) in readingState"
              :key="index"
            >
              <div class="col-md-5 col-lg-8 text-edit">
                <span class="glyphicon glyphicon-chevron-right"> </span>
                <h3 itemprop="name">
                  <router-link
                    :to="{
                      name: 'NovelDetail',
                      params: {
                        novelName: novel.novelNameUrl,
                      },
                    }"
                  >
                    {{ novel.novelName }}
                  </router-link>
                </h3>
              </div>

              <div class="col-md-3 text-info">
                <router-link
                  :to="{
                    name: 'NovelChapter',
                    params: {
                      novelName: novel.novelNameUrl,
                      chapterNumber: novel.chapterNumber,
                    },
                  }"
                >
                  Chương {{ novel.chapterNumber }}
                </router-link>
              </div>

              <div
                class="col-md-1 text-info"
                @click="removeReadingState(novel.novelNameUrl)"
              >
                x
              </div>
            </div>
          </div>
          <div class="list list-truyen list-cat col-xs-12">
            <div class="title-list">
              <h4>Thể loại truyện</h4>
            </div>
            <div class="row1">
              <div
                class="col-xs-6"
                v-for="(genre, index) in novelGenres"
                :key="index"
              >
                <router-link
                  :to="{ name: 'NovelListGenre', params: { genre: genre.url } }"
                >
                  {{ genre.name }}
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "HomePagePart2",
  props: {
    newNovelList: Array,
    novelGenres: Array,
  },
  computed: {
    ...mapState(["readingState"]),
  },
  methods: {
    ...mapActions(["removeReadingState"]),
  },
};
</script>

<style>
.text-title:hover {
  text-decoration: none !important;
}

#list-index a {
  color: #4e4e4e !important;
  text-decoration: none;
}

#list-index a:hover {
  text-decoration: underline;
}

.container {
  margin-right: auto;
  margin-left: auto;
  padding-left: 15px;
  padding-right: 15px;
}

.list-index {
  position: relative;
}

.container {
  width: 1170px;
}

.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

.col-xs-12 {
  width: 100%;
}

.col-xs-6 {
  width: 50%;
}

.col-xs-6,
.col-xs-12 {
  float: left;
}

.list {
  display: table;
  text-align: left;
}

.list > .row {
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  font-size: 14px;
}

.intro-index {
  margin-bottom: 15px;
  height: 448px;
  overflow: hidden;
  position: relative;
}

.title-list {
  text-align: left;
  height: 40px;
  text-transform: uppercase;
  border-bottom: 1px solid #ccc;
  position: relative;
}

.text-edit {
  padding: 0px !important;
}

#list-index .col-truyen-side .list-history .text-info {
  padding: 0px 0px 0px 5px !important;
}

.title-list > h2 {
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  font-size: 20px;
  height: 40px;
  line-height: 40px;
  max-width: 100%;
  /* white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; */
  border-bottom: 1px solid #4e4e4e;
  margin: 0;
  display: inline-block;
  color: #4e4e4e;
}

.text-title {
  color: #4e4e4e;
  text-decoration: none;
}

.new-select {
  line-height: 40px;
  height: 40px;
  width: 150px;
  border-radius: 0;
  position: absolute;
  top: 0;
  right: 0;
  padding: 3px;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  text-transform: uppercase;
  background-color: transparent;
}

.new-select {
  font-size: 20px !important;
  color: #4e4e4e;
  border-color: transparent;
  border-bottom-color: #4e4e4e;
  border: 2px solid;
  background-color: #f4f4f4;
}

.form-select:focus {
  border: 2px solid #86b7fe;
  outline: 0;
  box-shadow: none;
}

.glyphicon {
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: "Glyphicons Halflings";
  font-style: normal;
  font-weight: normal;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.list > .row > div,
#truyen-slide .caption > h3,
#truyen-slide .caption > small {
  /* white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; */
}

#list-index .row > div {
  height: 40px;
  line-height: 40px;
  border-left: 1px dashed #ccc;
  border-bottom: 1px dashed #ccc;
  overflow: hidden;
}

.row > .col-title {
  border-left: 0px;
}

.row {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 !important;
}

.row1 {
  --bs-gutter-y: 0;
  display: flex;
  flex-wrap: wrap;
  margin-top: calc(-1 * var(--bs-gutter-y));
  margin-right: calc(-0.5 * var(--bs-gutter-x));
  margin-left: calc(-0.5 * var(--bs-gutter-x));
}

.col-title {
  border-right: 1px;
}

#list-index .text-info a {
  color: #31708f !important;
}

.text-info:hover {
  cursor: pointer;
}

.title-list > h4 {
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  font-size: 20px !important;
  height: 40px;
  line-height: 40px;
  max-width: 100%;
  /* white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; */
  border-bottom: 1px solid #4e4e4e;
  color: #4e4e4e;
}

@media (min-width: 992px) {
  #list-index .col-truyen-side {
    top: 0;
    right: 0;
    position: absolute;
  }

  #list-index .col-truyen-side {
    padding: 0 0 0 15px;
  }

  #list-index .col-truyen-side .list.list-truyen {
    margin: 10px 0;
    padding-right: 14px;
    padding-left: 14px;
  }
}

@media (max-width: 1024px) {
  #list-index .col-truyen-side .list.list-truyen {
    padding: 0;
  }
}

@media (max-width: 991px) {
  #list-index .container > .col-truyen-side {
    padding-left: 0;
    padding-right: 0;
  }
}

@media (max-width: 992px) {
  .col-truyen-side {
    display: none;
  }
}

.col-truyen-main {
  padding: 0;
}

@media (min-width: 992px) {
  .col-truyen-main {
    width: 73%;
  }
}
@media (min-width: 992px) {
  .col-truyen-side {
    width: 27%;
    padding: 0 0 0 7.5px;
  }
}

.col-truyen-side .list {
  background-color: #ecf0f1;
  border: 1px solid #d9e1e4;
}

.col-truyen-side .list.list-cat > .row1 > div:nth-child(odd) {
  padding-left: 40px !important;
}

.col-truyen-side .list.list-cat > .row1 > div {
  height: 30px !important;
  line-height: 35px !important;
  border: 0 !important;
  padding-left: 20px !important;
}

.col-truyen-side .list > .row1 {
  background-color: #ecf0f1;
}

.list > .row > div:first-child {
  border-left: 0 !important;
  padding-left: 0;
}

.list > .row1 {
  display: table-row;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  font-size: 13px;
}

.list-cat > .row1 {
  font-size: 16px;
}

@media (min-width: 992px) {
  .col-md-4,
  .col-md-8 {
    float: left;
  }
}

@media (min-width: 1025px) {
  .list {
    border: 0;
  }
}

@media (min-width: 1200px) {
  .visible-lg-block {
    display: block !important;
  }
}

#list-index .list h3 a {
  font-size: 16px;
}

.col-truyen-side .list > .row1 > div {
  max-width: 270px;
  padding: 0;
}

@media (max-width: 1200px) {
  .list-cat > .row {
    font-size: 14px;
  }
}
</style>
