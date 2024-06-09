<template>
  <div class="container csstransforms3d truyen" v-if="!isLoading">
    <div class="col-truyen-main">
      <div class="col-xs-12 col-info-desc">
        <div class="title-list book-intro">
          <h2>Thông tin truyện</h2>
        </div>
        <h3 class="title text-center">{{ novel.title }}</h3>
        <div class="info-holder">
          <div class="books">
            <div class="book">
              <img :src="novel.coverUrl" />
            </div>
          </div>
          <div class="info">
            <div>
              <h4>Tác giả:</h4>
              <span :title="novel.author" class="ms-3">
                {{ novel.author }}
              </span>
            </div>
            <div>
              <h4>Thể loại:</h4>
              <router-link
                v-for="(genre, index) in novel.genres"
                :key="index"
                :to="{ name: 'NovelListGenre', params: { genre: genre.url } }"
                class="ms-3"
              >
                {{ genre.name }}
              </router-link>
            </div>
            <div>
              <h4>Trạng thái:</h4>
              <span class="text-success ms-3">{{
                novel.status || "Đang ra"
              }}</span>
            </div>
          </div>
        </div>
        <div class="desc" v-html="novel.description"></div>
      </div>
      <div class="col-xs-12 list-chapter-first" id="list-chapter">
        <div class="title-list">
          <h2>Danh sách chương</h2>
        </div>
        <div class="row clearfix">
          <div class="col-md-6">
            <ul class="list-chapter">
              <li v-for="(chapter, index) in novel.chapters" :key="index">
                <span class="glyphicon glyphicon-certificate"></span>
                <router-link :to="chapterUrl(chapter)">
                  {{ chapter }}
                </router-link>
              </li>
            </ul>
          </div>
          <div class="col-md-6">
            <ul class="list-chapter">
              <li v-for="(chapter, index) in novel.chapters" :key="index">
                <span class="glyphicon glyphicon-certificate"></span>
                <router-link :to="chapterUrl(chapter)">
                  {{ chapter }}
                </router-link>
              </li>
            </ul>
          </div>
        </div>
        <div class="row my-3 pagination">
          <v-pagination
            :model-value="currentPage"
            :pages="totalPages"
            :range-size="1"
            active-color="#DCEDFF"
            class="d-flex justify-content-center"
            @update:modelValue="handleChangePage"
          />
        </div>
      </div>
    </div>
  </div>
  <h3 v-else class="loading-text" style="margin: 10px auto">Đang tải ...</h3>
</template>

<script>
import { getNovelDetail } from "@/services/apiService";
import { scrollToTopSmooth } from "@/utils";
import VPagination from "@hennge/vue3-pagination";
import "@hennge/vue3-pagination/dist/vue3-pagination.css";
import { mapActions } from "vuex";

export default {
  name: "NovelDetail",
  components: {
    VPagination,
  },
  data() {
    return {
      novel: {},
      totalPages: 10,
      currentPage: 1,
      isLoading: true,
    };
  },
  computed: {
    novelNameUrl() {
      return this.$route.params.novelName;
    },
  },
  methods: {
    ...mapActions(["updateReadingState"]),
    async fetchNovelDetail() {
      const respone = await getNovelDetail(this.novelNameUrl, this.currentPage);

      this.novel = respone.data.content[0];
      this.totalPages = respone.data.totalPages;
      this.isLoading = false;

      scrollToTopSmooth();

      // Update reading state of novel
      this.updateReadingState({
        novelName: this.novel.title,
        novelNameUrl: this.novelNameUrl,
        coverUrl: this.novel.coverUrl,
        chapterNumber: 1,
      });
    },
    handleChangePage(newPage) {
      this.currentPage = newPage;
    },
    chapterUrl(chapter) {
      const novelName = this.$route.params.novelName;

      const chapterNumberMatch = chapter.match(/Chương\s*(\d+)/i);
      const chapterNumber = chapterNumberMatch ? chapterNumberMatch[1] : "";

      return novelName + "/" + chapterNumber;
    },
  },
  async mounted() {
    await this.fetchNovelDetail();
  },
  watch: {
    async currentPage() {
      await this.fetchNovelDetail();
    },
  },
};
</script>

<style scoped>
.loading-text {
  text-align: center;
  margin-top: 30px;
}

.container {
  margin-right: auto;
  margin-left: auto;
  padding-left: 15px;
  padding-right: 15px;
}

.container {
  overflow: hidden;
}

.col-truyen-main {
  width: 100%;
  padding: 0;
  float: left;
}

.col-xs-12 {
  width: 100%;
  float: left;
  position: relative;
  min-height: 1px;
  padding-left: 15px;
  padding-right: 15px;
}

.col-info-desc {
  margin-bottom: 5px;
  padding: 0;
  float: left;
}

.title-list {
  text-align: left;
  height: 40px;
  text-transform: uppercase;
  border-bottom: 1px solid #ccc;
  position: relative;
}

.title-list {
  margin-bottom: 15px;
}

.title-list > h2,
.title-list > h4 {
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  font-size: 20px;
  height: 40px;
  line-height: 40px;
  max-width: 100%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  border-bottom: 1px solid #4e4e4e;
}
h1,
.h1,
h2,
.h2,
h3,
.h3,
h4,
.h4 {
  margin: 0;
  padding: 0;
  font-size: inherit;
  display: inline-block;
}

.col-info-desc h3.title {
  font-size: 24px !important;
  font-weight: bold;
  margin: 0 0 5px;
  padding: 0 15px 7px;
  border-bottom: 1px solid #b5b5b5;
  text-transform: uppercase;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  float: right;
  width: 68%;
  text-align: center;
}

.info-holder {
  width: 32%;
  padding: 0;
  float: left;
}

.csstransforms3d .books {
  margin: 15px auto 30px;
  -moz-perspective: 300px;
  -moz-transform-style: preserve-3d;
  -webkit-transform-style: preserve-3d;
}

.book {
  display: inline-block;
  margin: 10px;
}
.csstransforms3d .book {
  position: relative;
  transform: perspective(300px) rotateY(-3deg);
  -moz-perspective: 300px;
  -moz-transform: rotateY(-3deg);
  -webkit-transform: perspective(300) rotateY(-3deg);
  outline: 1px solid transparent;
  box-shadow: 5px 5px 20px #333;
  margin: 0;
}

.csstransforms3d .book:before {
  width: 100%;
  left: 7.5%;
  background-color: #000;
}

.csstransforms3d .book:before,
.csstransforms3d .book:after {
  position: absolute;
  top: 2%;
  height: 96%;
  content: " ";
  z-index: -1;
}

.csstransforms3d .book img {
  position: relative;
}

.book img {
  max-width: 100%;
}

.book img {
  vertical-align: middle;
  border: 1px solid #000;
  border-left: 5px solid #000;
  height: 324px;
  width: 221px;
}

.csstransforms3d .book:after {
  width: 5%;
  left: 100%;
  background-color: #efefef;
  box-shadow: inset 0px 0px 5px #aaa;
  transform: perspective(300px) rotateY(20deg);
  -moz-transform: rotateY(20deg);
  -webkit-transform: perspective(300) rotateY(20deg);
}

.info-holder .info {
  padding: 5px;
  margin-top: 5px;
  margin-right: 15px;
  border-right: 1px solid #ddd;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
}

.info-holder .info div {
  margin: 0 0 5px;
}

.info-holder .info h3 {
  text-align: right;
  margin-right: 5px;
  font-weight: bold;
}

a:hover {
  text-decoration: underline !important;
}

a {
  color: #4e4e4e;
  text-decoration: none;
}

.desc {
  padding: 0 15px;
  text-align: center;
}

.desc {
  width: 68%;
  padding: 0 15px 0 0;
  float: left;
}

.desc {
  margin-top: 10px;
  font-size: 12px;
  overflow: hidden;
  text-align: left;
  font-family: "Arial", sans-serif;
}

#list-chapter {
  background-color: #f4f4f4;
  text-align: center;
  margin-top: 10px;
  padding: 0;
}

#list-chapter > .row {
  margin: 0;
}

.col-md-6 {
  float: left;
  position: relative;
  min-height: 1px;
  padding-left: 15px;
  padding-right: 15px;
}

ul {
  margin-top: 0;
  margin-bottom: 10px;
}

.list-chapter {
  list-style: none;
  padding-left: 13px;
  text-align: left;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
}

.list-chapter > li {
  padding: 5px 0;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
