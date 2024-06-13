<template>
  <div v-if="!isLoading" id="chapter-big-container" class="container chapter">
    <div class="row">
      <div class="col-xs-12">
        <router-link
          class="truyen-title"
          :to="{ name: 'NovelDetail', params: { novelName: novelName } }"
        >
          {{ novelTitle }}
        </router-link>
        <h2>
          <span class="chapter-title">
            <span class="chapter-text">
              {{ chapterTitle }}
            </span>
          </span>
        </h2>
        <hr class="chapter-start" />
        <!--      -->
        <div class="chapter-nav" id="chapter-nav-top">
          <div class="btn-group mb-2">
            <button
              type="button"
              :class="{
                'btn-success': serverName === '1',
                'btn btn-secondary': true,
              }"
              @click="serverName = '1'"
            >
              Server 1
            </button>

            <button
              :class="{
                'btn-success': serverName === '2',
                'btn btn-secondary mx-1': true,
              }"
              :disabled="isDisableServer2"
              @click="serverName = '2'"
            >
              Server 2
            </button>
          </div>

          <br />

          <div class="btn-group">
            <button
              class="btn btn-success btn-chapter-nav btn-left"
              id="prev_chap"
              @click="handleClickPreviousChap"
              :disabled="chapterNumber == 1"
            >
              <span class="glyphicon">
                <FontAwesomeIcon
                  class="font-click-left"
                  icon="{faChevronLeft}"
                />
              </span>
              <span class="hidden-xs">Chương </span>trước
            </button>

            <button
              type="button"
              class="btn btn-success btn-chapter-nav chapter_jump btn-middle dropdown-toggle"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Tải xuống
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">Action</a></li>
              <li><a class="dropdown-item" href="#">Another action</a></li>
              <li>
                <a class="dropdown-item" href="#">Something else here</a>
              </li>
            </ul>

            <button
              class="btn btn-success btn-chapter-nav btn-right"
              id="next_chap"
              :disabled="isNewestChapter"
              @click="handleClickNextChap"
            >
              <span class="hidden-xs">Chương </span>tiếp
              <span class="glyphicon">
                <FontAwesomeIcon
                  class="font-click-list"
                  icon="{faRectangleList}"
                />
              </span>
            </button>
          </div>
        </div>

        <!----------------------------------------------------->
        <br />
        <hr class="chapter-end" />

        <div
          id="chapter-c"
          class="chapter-c"
          itemprop="articleBody"
          v-html="content"
          :style="textStyle"
        ></div>
      </div>
    </div>
  </div>
  <h3 v-else class="loading-text">Đang tải ...</h3>
</template>

<script>
import { getNovelChapter } from "@/services/apiService";
import { mapState, mapActions } from "vuex";

export default {
  name: "NovelChapter",
  data() {
    return {
      serverName: "1",
      content: "",
      novelTitle: "",
      chapterTitle: "",
      isLoading: true,
      isNewestChapter: false,
      isDisableServer2: false,
    };
  },
  computed: {
    ...mapState(["textStyle"]),
    novelNameUrl() {
      return this.$route.params.novelName;
    },
    chapterNumber() {
      return this.$route.params.chapterNumber;
    },
  },
  methods: {
    ...mapActions(["updateReadingState"]),
    async getNovelChapter() {
      // Call api get novel chapter
      const response = await getNovelChapter(
        this.serverName,
        this.novelNameUrl,
        this.chapterNumber
      );

      // Set response to state
      this.content = response.data.chapterContent;
      this.novelTitle = response.data.novelTitle;
      this.chapterTitle = response.data.chapterTitle;
      this.isLoading = false;

      // Update reading state of novel
      this.updateReadingState({
        novelName: this.novelTitle,
        novelNameUrl: this.novelNameUrl,
        chapterNumber: this.chapterNumber,
      });
    },
    async checkIfNewestChapter() {
      const response = await getNovelChapter(
        this.serverName,
        this.novelNameUrl,
        parseInt(this.chapterNumber) + 1
      );

      this.isNewestChapter = !response.data.chapterContent;
    },
    async checkDisableServer2() {
      const response = await getNovelChapter(
        "2",
        this.novelNameUrl,
        parseInt(this.chapterNumber) + 1
      );

      this.isDisableServer2 = response.statusCode === 500;
    },
    async handleClickNextChap() {
      const newChapterNumber = parseInt(this.chapterNumber) + 1;
      this.isLoading = true;

      await this.$router.push("./" + newChapterNumber);

      await this.checkIfNewestChapter();
      await this.checkDisableServer2();
      await this.getNovelChapter();
    },
    async handleClickPreviousChap() {
      const newChapterNumber = parseInt(this.chapterNumber) - 1;
      this.isLoading = true;

      await this.$router.push("./" + newChapterNumber);

      await this.checkIfNewestChapter();
      await this.checkDisableServer2();
      await this.getNovelChapter();
    },
    handleDownloadChapter() {
      window.open(
        `http://localhost:8080/api/novels/export?novelName=${this.novelNameUrl}&chapterNumber=${this.chapterNumber}`,
        "_blank"
      );
    },
  },
  watch: {
    async serverName() {
      await this.checkIfNewestChapter();
      await this.checkDisableServer2();
      await this.getNovelChapter();
    },
  },
  async mounted() {
    await this.checkIfNewestChapter();
    await this.checkDisableServer2();
    await this.getNovelChapter();
  },
};
</script>

<style scoped>
.btn:disabled {
  opacity: 0.35;
}
.loading-text {
  text-align: center;
  margin-top: 30px;
}

.container {
  width: 1170px;
}

#chapter-big-container {
  margin-top: 20px;
}

.chapter {
  text-align: center;
}

.row {
  margin-left: -15px;
  margin-right: -15px;
}

.col-xs-12 {
  width: 100%;
  float: left;
  position: relative;
  min-height: 1px;
  padding-left: 15px;
  padding-right: 15px;
}

.chapter .truyen-title {
  display: table;
  font-size: 24px;
  margin: 0 auto 5px;
  text-transform: uppercase;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  color: #690;
  text-decoration: none !important;
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

h1,
h2,
h3,
h4,
h5,
h6,
.h1,
.h2,
.h3,
.h4,
.h5,
.h6 {
  font-family: inherit;
  font-weight: 500;
  line-height: 1.1;
  color: inherit;
}

.chapter .chapter-title {
  color: gray;
  font-size: 16px;
}

.chapter hr.chapter-start {
  background: url(//static.8cache.com/img/spriteimg_new_white_op.png) -200px -27px
    no-repeat;
  width: 59px;
  height: 20px;
}
.chapter hr.chapter-start,
.chapter hr.chapter-end {
  border: 0;
  margin: 10px auto;
  display: table;
}

.btn-group,
.btn-group-vertical {
  position: relative;
  display: inline-block;
  vertical-align: middle;
}

.btn-right {
  border-bottom-left-radius: 0 !important;
  border-top-left-radius: 0 !important;
  border-top-right-radius: inherit !important;
  border-bottom-right-radius: inherit !important;
}

.btn-left {
  border-bottom-right-radius: 0 !important;
  border-top-right-radius: 0 !important;
}

.btn-group > .btn:first-child {
  margin-left: 0;
}

.chapter-nav .btn-group button {
  width: 170px;
}

.btn-group > .btn {
  position: relative;
  float: left;
}

.btn {
  display: inline-block;
  margin-bottom: 0;
  font-weight: normal;
  text-align: center;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  background-image: none;
  border: 1px solid transparent;
  white-space: nowrap;
  padding: 6px 12px;
  font-size: 14px;
  line-height: 1.42857143;
  border-radius: 4px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.btn-chapter-nav {
  padding: 11px 22px;
  font-size: 16px;
  height: 47px;
}

.btn-success {
  color: #fff;
  background-color: #5cb85c !important;
  border-color: #4cae4c !important;
}

.chapter-c {
  font-size: 22px;
}
.chapter .chapter-c {
  font-family: "Palatino Linotype", "Arial", "Times New Roman", sans-serif;
  line-height: 180%;
  color: #2b2b2b;
  font-size: 22px;
  text-align: left;
  word-wrap: break-word;
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

.font-click-left {
  font-size: 20px;
  margin-right: 5px;
}

.font-click-right {
  font-size: 20px;
  margin-left: 5px;
}

.font-click-list {
  font-size: 20px;
}

.btn-middle {
  border-radius: 0 !important;
}

.chapter-nav .btn-group .chapter_jump {
  margin: 0 5px !important;
  text-align: left;
}

.chapter_jump {
  width: inherit !important;
}

.chapter hr.chapter-end {
  background: url(//static.8cache.com/img/spriteimg_new_white_op.png) 0 -51px no-repeat;
  width: 277px;
  height: 35px;
}
</style>
