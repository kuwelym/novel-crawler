<template>
  <div v-if="!isLoading" id="chapter-big-container" class="container chapter">
    <div class="row">
      <div class="col-12">
        <router-link
          class="truyen-title"
          :to="novelUrl"
          title="Kiều Sủng Vi Thượng"
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
        <div class="chapter-nav" id="chapter-nav-top">
          <input type="hidden" id="ten-truyen" value="kieu-sung-vi-thuong" />
          <input
            type="hidden"
            id="truyen-comment"
            value="kieu-sung-vi-thuong"
          />
          <div class="btn-group">
            <button
              class="btn btn-success btn-chapter-nav me-1"
              id="prev_chap"
              @click="handleClickPreviousChap"
              :disabled="chapterNumber == 1"
            >
              <span class="glyphicon glyphicon-chevron-left"></span>
              <span class="hidden-xs">Chương </span>trước
            </button>

            <button
              class="btn btn-success btn-chapter-nav"
              id="next_chap"
              @click="handleClickNextChap"
            >
              <span class="hidden-xs">Chương </span>tiếp
              <span class="glyphicon glyphicon-chevron-right"></span>
            </button>

            <button
              type="button"
              class="btn btn-warning dropdown-toggle"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Điều chỉnh
            </button>
            <ul class="dropdown-menu">
              <li>
                <button class="dropdown-item" @click="handleFontSizeDecrease">
                  Giảm cỡ chữ
                </button>
              </li>
              <li>
                <button class="dropdown-item" @click="handleFontSizeIncrease">
                  Tăng cỡ chữ
                </button>
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>
              <li>
                <label for="font-family-select" class="dropdown-item"
                  >Chọn font chữ:</label
                >
                <select
                  id="font-family-select"
                  class="form-select"
                  v-model="fontFamily"
                >
                  <option value="Arial, sans-serif" selected>Arial</option>
                  <option value="Times New Roman, serif">
                    Times New Roman
                  </option>
                  <option value="Courier New, monospace">Courier New</option>
                </select>
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>
              <li>
                <label for="font-color-picker" class="dropdown-item"
                  >Chọn màu chữ:</label
                >
                <input
                  type="color"
                  id="font-color-picker"
                  v-model="fontColor"
                />
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>
              <li>
                <label for="line-height-select" class="dropdown-item"
                  >Chọn khoảng cách dòng:</label
                >
                <select
                  id="line-height-select"
                  class="form-select"
                  v-model="lineHeight"
                  @change="handleLineHeightChange"
                >
                  <option value="1.0">1.0</option>
                  <option value="1.2">1.2</option>
                  <option value="1.5">1.5</option>
                </select>
              </li>
            </ul>
          </div>
        </div>
        <br />
        <hr class="chapter-end" />
        <div
          id="chapter-c"
          class="chapter-c"
          itemprop="articleBody"
          v-html="content"
          :style="contentStyle"
        ></div>
        <hr class="chapter-end mt-3" />
      </div>
    </div>
  </div>
  <h3 v-else class="loading-text">Đang tải ...</h3>
</template>

<script>
import { getNovelChapter } from "@/services/apiService";

export default {
  name: "NovelChapter",
  data() {
    return {
      content: "",
      novelTitle: "",
      chapterTitle: "",
      isLoading: true,
      fontSize: "",
      fontFamily: "Arial",
      fontColor: "",
      lineHeight: "",
    };
  },
  computed: {
    novelName() {
      return this.$route.params.novelName;
    },
    chapterNumber() {
      return this.$route.params.chapterNumber;
    },
    novelUrl() {
      return "./";
    },
    contentStyle() {
      const contentStyle = {
        fontSize: this.fontSize || "16px", // Set default font size
        fontFamily: this.fontFamily || "Arial, sans-serif", // Set default font family
        color: this.fontColor || "#000", // Set default font color
        lineHeight: this.lineHeight || "1.5", // Set default line height
      };
      return contentStyle;
    },
  },
  methods: {
    async getNovelChapter() {
      // Call api get novel chapter
      const response = await getNovelChapter(
        this.novelName,
        this.chapterNumber
      );

      // Set response to state
      this.content = response.data.chapterContent;
      this.novelTitle = response.data.novelTitle;
      this.chapterTitle = response.data.chapterTitle;
      this.isLoading = false;
    },
    async handleClickNextChap() {
      const newChapterNumber = parseInt(this.chapterNumber) + 1;
      this.isLoading = true;

      await this.$router.push("./" + newChapterNumber);

      await this.getNovelChapter();
    },
    async handleClickPreviousChap() {
      const newChapterNumber = parseInt(this.chapterNumber) - 1;
      this.isLoading = true;

      await this.$router.push("./" + newChapterNumber);

      await this.getNovelChapter();
    },
    handleFontSizeDecrease() {
      const currentFontSize = parseFloat(this.fontSize) || 16;
      this.fontSize = Math.max(currentFontSize - 2, 8) + "px"; // Ensure minimum font size
    },
    handleFontSizeIncrease() {
      const currentFontSize = parseFloat(this.fontSize) || 16;
      this.fontSize = Math.min(currentFontSize + 2, 32) + "px"; // Ensure maximum font size
    },
  },
  async mounted() {
    await this.getNovelChapter();
  },
};
</script>

<style scoped>
.loading-text {
  text-align: center;
  margin-top: 30px;
}

html {
  font-family: sans-serif;
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
}

body {
  margin: 0;
}

a {
  background-color: transparent;
}

h1 {
  font-size: 2em;
  margin: 0.67em 0;
}

hr {
  -webkit-box-sizing: content-box;
  -moz-box-sizing: content-box;
  box-sizing: content-box;
  height: 0;
}

button,
input,
select {
  color: inherit;
  font: inherit;
  margin: 0;
}

button {
  overflow: visible;
}

button,
select {
  text-transform: none;
}

button {
  -webkit-appearance: button;
}

button::-moz-focus-inner,
input::-moz-focus-inner {
  border: 0;
  padding: 0;
}

input {
  line-height: normal;
}

input[type="radio"] {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  padding: 0;
}

input[type="search"] {
  -webkit-appearance: textfield;
  -webkit-box-sizing: content-box;
  -moz-box-sizing: content-box;
  box-sizing: content-box;
}

input[type="search"]::-webkit-search-cancel-button,
input[type="search"]::-webkit-search-decoration {
  -webkit-appearance: none;
}

@font-face {
  font-family: "Glyphicons Halflings";
  font-display: swap;
  src: url("//static.8cache.com/lib/bootstrap/fonts/glyphicons-halflings-regular.eot");
  src: url("//static.8cache.com/lib/bootstrap/fonts/glyphicons-halflings-regular.eot?#iefix")
      format("embedded-opentype"),
    url("//static.8cache.com/lib/bootstrap/fonts/glyphicons-halflings-regular.woff2")
      format("woff2"),
    url("//static.8cache.com/lib/bootstrap/fonts/glyphicons-halflings-regular.woff")
      format("woff"),
    url("//static.8cache.com/lib/bootstrap/fonts/glyphicons-halflings-regular.ttf")
      format("truetype"),
    url("//static.8cache.com/lib/bootstrap/fonts/glyphicons-halflings-regular.svg#glyphicons_halflingsregular")
      format("svg");
}

.glyphicon {
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: "Glyphicons Halflings";
  font-style: normal;
  font-weight: 400;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.glyphicon-search:before {
  content: "\e003";
}

.glyphicon-cog:before {
  content: "\e019";
}

.glyphicon-home:before {
  content: "\e021";
}

.glyphicon-list-alt:before {
  content: "\e032";
}

.glyphicon-list:before {
  content: "\e056";
}

.glyphicon-chevron-left:before {
  content: "\e079";
}

.glyphicon-chevron-right:before {
  content: "\e080";
}

.glyphicon-menu-up:before {
  content: "\e260";
}

.glyphicon-menu-down:before {
  content: "\e259";
}

* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

*:before,
*:after {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

html {
  font-size: 10px;
}

body {
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 14px;
  line-height: 1.42857143;
  color: #333;
  background-color: #fff;
}

input,
button,
select {
  font-family: inherit;
  font-size: inherit;
  line-height: inherit;
}

a {
  color: #428bca;
  text-decoration: none;
}

hr {
  margin-top: 20px;
  margin-bottom: 20px;
  border: 0;
  border-top: 1px solid #eee;
}

.sr-only {
  position: absolute;
  width: 1px;
  height: 1px;
  margin: -1px;
  padding: 0;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}

h1,
h2 {
  font-family: inherit;
  font-weight: 500;
  line-height: 1.1;
  color: inherit;
}

h1,
h2 {
  margin-top: 20px;
  margin-bottom: 10px;
}

h1 {
  font-size: 36px;
}

h2 {
  font-size: 30px;
}

.text-center {
  text-align: center;
}

ul,
ol {
  margin-top: 0;
  margin-bottom: 10px;
}

ul ul {
  margin-bottom: 0;
}

.container {
  margin-right: auto;
  margin-left: auto;
  padding-left: 15px;
  padding-right: 15px;
}

@media (min-width: 768px) {
  .container {
    width: 750px;
  }
}

@media (min-width: 992px) {
  .container {
    width: 970px;
  }
}

@media (min-width: 1200px) {
  .container {
    width: 1170px;
  }
}

.row {
  margin-left: -15px;
  margin-right: -15px;
}

.col-sm-2,
.col-md-4,
.col-sm-5,
.col-md-5,
.col-md-7,
.col-xs-12 {
  position: relative;
  min-height: 1px;
  padding-left: 15px;
  padding-right: 15px;
}

.col-xs-12 {
  float: left;
}

.col-xs-12 {
  width: 100%;
}

@media (min-width: 768px) {
  .col-sm-2,
  .col-sm-5 {
    float: left;
  }
  .col-sm-5 {
    width: 41.66666667%;
  }
  .col-sm-2 {
    width: 16.66666667%;
  }
}

@media (min-width: 992px) {
  .col-md-4,
  .col-md-5,
  .col-md-7 {
    float: left;
  }
  .col-md-7 {
    width: 58.33333333%;
  }
  .col-md-5 {
    width: 41.66666667%;
  }
  .col-md-4 {
    width: 33.33333333%;
  }
}

label {
  display: inline-block;
  max-width: 100%;
  margin-bottom: 5px;
  font-weight: 700;
}

input[type="search"] {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

input[type="radio"] {
  margin: 4px 0 0;
  margin-top: 1px \9;
  line-height: normal;
}

.form-control {
  display: block;
  width: 100%;
  height: 34px;
  padding: 6px 12px;
  font-size: 14px;
  line-height: 1.42857143;
  color: #555;
  background-color: #fff;
  background-image: none;
  border: 1px solid #ccc;
  border-radius: 4px;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
}

.form-control::-moz-placeholder {
  color: #777;
  opacity: 1;
}

.form-control:-ms-input-placeholder {
  color: #777;
}

.form-control::-webkit-input-placeholder {
  color: #777;
}

.form-control::-ms-expand {
  border: 0;
  background-color: transparent;
}

input[type="search"] {
  -webkit-appearance: none;
}

.form-group {
  margin-bottom: 15px;
}

.radio-inline input[type="radio"] {
  position: absolute;
  margin-left: -20px;
  margin-top: 4px \9;
}

.radio-inline {
  position: relative;
  display: inline-block;
  padding-left: 20px;
  margin-bottom: 0;
  vertical-align: middle;
  font-weight: 400;
}

.radio-inline + .radio-inline {
  margin-top: 0;
  margin-left: 10px;
}

.form-group-sm .form-control {
  height: 30px;
  padding: 5px 10px;
  font-size: 12px;
  line-height: 1.5;
  border-radius: 3px;
}

.form-group-sm select.form-control {
  height: 30px;
  line-height: 30px;
}

.form-horizontal .radio-inline {
  margin-top: 0;
  margin-bottom: 0;
  padding-top: 7px;
}

.form-horizontal .form-group {
  margin-left: -15px;
  margin-right: -15px;
}

@media (min-width: 768px) {
  .form-horizontal .control-label {
    text-align: right;
    margin-bottom: 0;
    padding-top: 7px;
  }
}

@media (min-width: 768px) {
  .form-horizontal .form-group-sm .control-label {
    padding-top: 6px;
    font-size: 12px;
  }
}

.btn {
  display: inline-block;
  margin-bottom: 0;
  font-weight: 400;
  text-align: center;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  background-image: none;
  border: 1px solid transparent;
  white-space: nowrap;
  padding: 6px 12px;
  font-size: 14px;
  line-height: 1.42857143;
  border-radius: 4px;
}

.btn-default {
  color: #333;
  background-color: #fff;
  border-color: #ccc;
}

.btn-success {
  color: #fff;
  background-color: #5cb85c;
  border-color: #4cae4c;
}

.collapse {
  display: none;
}

.caret {
  display: inline-block;
  width: 0;
  height: 0;
  margin-left: 2px;
  vertical-align: middle;
  border-top: 4px dashed;
  border-top: 4px solid \9;
  border-right: 4px solid transparent;
  border-left: 4px solid transparent;
}

.dropdown {
  position: relative;
}

.dropdown-menu {
  position: absolute;
  top: 100%;
  left: 0;
  z-index: 1000;
  display: none;
  float: left;
  min-width: 160px;
  padding: 5px 0;
  margin: 2px 0 0;
  list-style: none;
  font-size: 14px;
  text-align: left;
  background-color: #fff;
  border: 1px solid #ccc;
  border: 1px solid rgba(0, 0, 0, 0.15);
  border-radius: 4px;
  -webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
  -webkit-background-clip: padding-box;
  background-clip: padding-box;
}

.dropdown-menu > li > a {
  display: block;
  padding: 3px 20px;
  clear: both;
  font-weight: 400;
  line-height: 1.42857143;
  color: #333;
  white-space: nowrap;
}

.dropdown-menu-right {
  left: auto;
  right: 0;
}

.btn-group {
  position: relative;
  display: inline-block;
  vertical-align: middle;
}

.btn-group > .btn {
  position: relative;
  float: left;
}

.btn-group .btn + .btn {
  margin-left: -1px;
}

.btn-group > .btn:not(:first-child):not(:last-child):not(.dropdown-toggle) {
  border-radius: 0;
}

.btn-group > .btn:first-child {
  margin-left: 0;
}

.btn-group > .btn:first-child:not(:last-child):not(.dropdown-toggle) {
  border-bottom-right-radius: 0;
  border-top-right-radius: 0;
}

.btn-group > .btn:last-child:not(:first-child) {
  border-bottom-left-radius: 0;
  border-top-left-radius: 0;
}

.input-group {
  position: relative;
  display: table;
  border-collapse: separate;
}

.input-group .form-control {
  position: relative;
  z-index: 2;
  float: left;
  width: 100%;
  margin-bottom: 0;
}

.input-group-btn,
.input-group .form-control {
  display: table-cell;
}

.input-group-btn {
  width: 1%;
  white-space: nowrap;
  vertical-align: middle;
}

.input-group .form-control:first-child {
  border-bottom-right-radius: 0;
  border-top-right-radius: 0;
}

.input-group-btn:last-child > .btn {
  border-bottom-left-radius: 0;
  border-top-left-radius: 0;
}

.input-group-btn {
  position: relative;
  font-size: 0;
  white-space: nowrap;
}

.input-group-btn > .btn {
  position: relative;
}

.input-group-btn:last-child > .btn {
  z-index: 2;
  margin-left: -1px;
}

.nav {
  margin-bottom: 0;
  padding-left: 0;
  list-style: none;
}

.nav > li {
  position: relative;
  display: block;
}

.nav > li > a {
  position: relative;
  display: block;
  padding: 10px 15px;
}

.navbar {
  position: relative;
  min-height: 50px;
  margin-bottom: 20px;
  border: 1px solid transparent;
}

@media (min-width: 992px) {
  .navbar {
    border-radius: 4px;
  }
}

@media (min-width: 992px) {
  .navbar-header {
    float: left;
  }
}

.navbar-collapse {
  overflow-x: visible;
  padding-right: 15px;
  padding-left: 15px;
  border-top: 1px solid transparent;
  -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.1);
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.1);
  -webkit-overflow-scrolling: touch;
}

@media (min-width: 992px) {
  .navbar-collapse {
    width: auto;
    border-top: 0;
    -webkit-box-shadow: none;
    box-shadow: none;
  }
  .navbar-collapse.collapse {
    display: block !important;
    height: auto !important;
    padding-bottom: 0;
    overflow: visible !important;
  }
  .navbar-static-top .navbar-collapse {
    padding-left: 0;
    padding-right: 0;
  }
}

.container > .navbar-header,
.container > .navbar-collapse {
  margin-right: -15px;
  margin-left: -15px;
}

@media (min-width: 992px) {
  .container > .navbar-header,
  .container > .navbar-collapse {
    margin-right: 0;
    margin-left: 0;
  }
}

.navbar-static-top {
  z-index: 1000;
  border-width: 0 0 1px;
}

@media (min-width: 992px) {
  .navbar-static-top {
    border-radius: 0;
  }
}

.navbar-toggle {
  position: relative;
  float: right;
  margin-right: 15px;
  padding: 9px 10px;
  margin-top: 8px;
  margin-bottom: 8px;
  background-color: transparent;
  background-image: none;
  border: 1px solid transparent;
  border-radius: 4px;
}

.navbar-toggle .icon-bar {
  display: block;
  width: 22px;
  height: 2px;
  border-radius: 1px;
}

.navbar-toggle .icon-bar + .icon-bar {
  margin-top: 4px;
}

@media (min-width: 992px) {
  .navbar-toggle {
    display: none;
  }
}

.navbar-nav {
  margin: 7.5px -15px;
}

.navbar-nav > li > a {
  padding-top: 10px;
  padding-bottom: 10px;
  line-height: 20px;
}

@media (min-width: 992px) {
  .navbar-nav {
    float: left;
    margin: 0;
  }
  .navbar-nav > li {
    float: left;
  }
  .navbar-nav > li > a {
    padding-top: 15px;
    padding-bottom: 15px;
  }
}

.navbar-form {
  margin-left: -15px;
  margin-right: -15px;
  padding: 10px 15px;
  border-top: 1px solid transparent;
  border-bottom: 1px solid transparent;
  -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.1),
    0 1px 0 rgba(255, 255, 255, 0.1);
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.1),
    0 1px 0 rgba(255, 255, 255, 0.1);
  margin-top: 8px;
  margin-bottom: 8px;
}

@media (min-width: 768px) {
  .navbar-form .form-control {
    display: inline-block;
    width: auto;
    vertical-align: middle;
  }
  .navbar-form .input-group {
    display: inline-table;
    vertical-align: middle;
  }
  .navbar-form .input-group .input-group-btn,
  .navbar-form .input-group .form-control {
    width: auto;
  }
  .navbar-form .input-group > .form-control {
    width: 100%;
  }
}

@media (min-width: 992px) {
  .navbar-form {
    width: auto;
    border: 0;
    margin-left: 0;
    margin-right: 0;
    padding-top: 0;
    padding-bottom: 0;
    -webkit-box-shadow: none;
    box-shadow: none;
  }
}

.navbar-nav > li > .dropdown-menu {
  margin-top: 0;
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}

@media (min-width: 992px) {
  .navbar-right {
    float: right !important;
    margin-right: -15px;
  }
}

.navbar-default {
  background-color: #f8f8f8;
  border-color: #e7e7e7;
}

.navbar-default .navbar-nav > li > a {
  color: #777;
}

.navbar-default .navbar-toggle {
  border-color: #ddd;
}

.navbar-default .navbar-toggle .icon-bar {
  background-color: #888;
}

.navbar-default .navbar-collapse,
.navbar-default .navbar-form {
  border-color: #e7e7e7;
}

.breadcrumb {
  padding: 8px 15px;
  margin-bottom: 20px;
  list-style: none;
  background-color: #f5f5f5;
  border-radius: 4px;
}

.breadcrumb > li {
  display: inline-block;
}

.breadcrumb > li + li:before {
  content: "/\00a0";
  padding: 0 5px;
  color: #ccc;
}

.breadcrumb > .active {
  color: #777;
}

.list-group {
  margin-bottom: 20px;
  padding-left: 0;
}

.container:before,
.container:after,
.row:before,
.row:after,
.form-horizontal .form-group:before,
.form-horizontal .form-group:after,
.nav:before,
.nav:after,
.navbar:before,
.navbar:after,
.navbar-header:before,
.navbar-header:after,
.navbar-collapse:before,
.navbar-collapse:after {
  content: " ";
  display: table;
}

.container:after,
.row:after,
.form-horizontal .form-group:after,
.nav:after,
.navbar:after,
.navbar-header:after,
.navbar-collapse:after {
  clear: both;
}

.pull-right {
  float: right !important;
}

.hide {
  display: none !important;
}

@-ms-viewport {
  width: device-width;
}

.visible-md,
.visible-lg {
  display: none !important;
}

@media (min-width: 992px) and (max-width: 1199px) {
  .visible-md {
    display: block !important;
  }
}

@media (min-width: 1200px) {
  .visible-lg {
    display: block !important;
  }
}

@media (max-width: 767px) {
  .hidden-xs {
    display: none !important;
  }
}

@media (min-width: 768px) and (max-width: 991px) {
  .hidden-sm {
    display: none !important;
  }
}

@media (min-width: 992px) and (max-width: 1199px) {
  .hidden-md {
    display: none !important;
  }
}

@media (min-width: 1200px) {
  .hidden-lg {
    display: none !important;
  }
}

.navbar {
  min-height: 0;
}

.navbar-nav {
  font-family: "Roboto Condensed", sans-serif;
}

.navbar-default {
  background-color: #ececec;
  border-color: #ececec;
}

.btn-responsive {
  padding: 6px 12px;
  font-size: 14px;
  line-height: 1.5;
  border-radius: 3px;
}

@media (min-width: 992px) {
  .btn-responsive {
    padding: 10px 16px;
    font-size: 18px;
    line-height: 1.33;
    border-radius: 6px;
  }
}

@media (min-width: 1200px) {
  .btn-responsive {
    padding: 16px 24px;
    font-size: 28px;
    line-height: 1;
    border-radius: 8px;
  }
}

html,
body {
  height: 100%;
}

body {
  background: url(//static.8cache.com/img/bg.jpg) top center repeat-x #f4f4f4;
  color: #4e4e4e;
}

* {
  margin: 0;
}

a {
  color: #4e4e4e;
}

.form-control {
  box-shadow: none;
}

.form-control {
  box-shadow: none;
}

h1,
h2 {
  margin: 0;
  padding: 0;
  font-size: inherit;
  display: inline-block;
}

#body_chapter {
  background-image: none;
  background-color: #f4f4f4;
}

#wrap {
  min-height: 100%;
  min-height: calc(100% - 117px);
  height: auto !important;
  height: 100%;
}

.navbar-default {
  background-color: #14425d;
  background-position-x: -50px;
  background-repeat: no-repeat;
  -moz-box-shadow: 0 0 6px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0 0 6px rgba(0, 0, 0, 0.3);
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.3);
}

.navbar-default .navbar-nav > li > a {
  color: #f2f2f2;
}

.navbar-breadcrumb {
  background: #ececec;
  border-top: 1px solid #476171;
}

.settings .form-horizontal .form-group-sm .control-label {
  font-weight: 400;
}

.search-holder .form-control {
  height: 32px;
  padding: 5px 10px;
  border-right: 0;
  border-radius: 0;
}

.search-holder > .input-group-btn > .btn {
  padding: 5px 10px;
  border-left: 0;
  border-radius: 0;
}

.breadcrumb-container {
  height: 30px;
  line-height: 30px;
  color: #4e4e4e;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  font-family: "Roboto Condensed", sans-serif;
}

.breadcrumb {
  padding: 0;
  margin: 0;
  background-color: inherit;
  border-radius: 0;
  display: inline-block;
}

.breadcrumb > li:first-child span.glyphicon {
  padding-right: 6px;
}

.breadcrumb a {
  color: #666;
}

.breadcrumb > .active,
.breadcrumb > .active a {
  color: #8d8d8d;
}

.header-logo {
  display: block;
  width: 196px;
  height: 36px;
  background: url(//static.8cache.com/img/spriteimg_new_white_op.png) no-repeat
    0 0;
  text-indent: -9999px;
  white-space: nowrap;
  outline: none;
  margin: 5px 5px 0 0;
}

.multi-column {
  width: 409px;
}

.navbar-nav > li > .dropdown-menu {
  background-color: #2f566d;
}

.multi-column .dropdown-menu {
  display: block !important;
  position: static !important;
  margin: 0 !important;
  padding: 0 !important;
  border: none !important;
  box-shadow: none !important;
  min-width: 95px;
  max-width: 106px;
  background-color: transparent;
}

.dropdown-menu > li > a {
  padding: 6px 15px;
  font-size: 15px;
}

.list-search-res {
  margin-top: 3px;
  position: absolute;
  width: 235px;
  z-index: 1000;
  background-color: #fff;
  box-shadow: 0 2px 10px #ababab;
}

.chapter {
  text-align: center;
}

.chapter .truyen-title {
  display: table;
  font-size: 24px;
  margin: 0 auto 5px;
  text-transform: uppercase;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  color: #690;
}

.chapter .chapter-title {
  color: gray;
  font-size: 16px;
}

.chapter hr.chapter-start,
.chapter hr.chapter-end {
  border: 0;
  margin: 10px auto;
  display: table;
}

.chapter hr.chapter-start {
  background: url(//static.8cache.com/img/spriteimg_new_white_op.png) -200px -27px
    no-repeat;
  width: 59px;
  height: 20px;
}

.chapter hr.chapter-end {
  background: url(//static.8cache.com/img/spriteimg_new_white_op.png) 0 -51px no-repeat;
  width: 277px;
  height: 35px;
}

.chapter .chapter-c {
  font-family: "Palatino Linotype", "Arial", "Times New Roman", sans-serif;
  line-height: 180%;
  color: #2b2b2b;
  font-size: 22px;
  text-align: left;
  word-wrap: break-word;
}

.chapter-c img {
  max-width: 100%;
  display: block;
  margin: 0 auto;
}

.chapter-nav .btn-group a {
  width: 170px;
}

.chapter-nav .btn-group .chapter_jump {
  margin: 0 5px;
  text-align: left;
}

.settings {
  padding: 10px;
  width: 300px;
}

.toggle-nav-open {
  position: absolute;
  left: 15px;
}

@font-face {
  font-family: "Roboto";
  font-display: fallback;
  font-style: normal;
  font-weight: 400;
  src: url(//static.8cache.com/font/Roboto-Regular.eot);
  src: local("Roboto Regular"), local("Roboto-Regular"),
    url(//static.8cache.com/font/Roboto-Regular.eot) format("embedded-opentype"),
    url(//static.8cache.com/font/Roboto-Regular.woff) format("woff");
}

@font-face {
  font-family: "Roboto Condensed";
  font-display: fallback;
  font-style: normal;
  font-weight: 400;
  src: url(//static.8cache.com/font/RobotoCondensed-Regular.eot);
  src: local("Roboto Condensed Regular"), local("RobotoCondensed-Regular"),
    url(//static.8cache.com/font/RobotoCondensed-Regular.eot)
      format("embedded-opentype"),
    url(//static.8cache.com/font/RobotoCondensed-Regular.woff) format("woff");
}

.container-fluid-switch #chapter-big-container {
  width: auto;
}

#body_chapter.hidenav #nav {
  display: none;
}

#body_chapter.hidenav #chapter-big-container {
  margin-top: 10px;
}

.glyphicon-menu-updownswitch:before {
  content: "\e260";
}

#body_chapter.hidenav .glyphicon-menu-updownswitch:before {
  content: "\e259";
}

#body_chapter.background-hatsan {
  background-image: url(//static.8cache.com/img/bg_op.png);
  background-color: #f2f2f2;
  background-repeat: repeat;
}

#body_chapter.background-sachcu {
  background-image: url(https://static.8cache.com/img/bg_book_op.png);
  background-color: #c2b49b;
  background-repeat: repeat;
}

#body_chapter.background-232323 {
  background-image: url(https://static.8cache.com/img/bg_dark.gif);
  background-color: #232323;
  background-repeat: repeat;
}

#body_chapter.background-F4F4F4 {
  background-image: none;
  background-color: #f4f4f4;
}

#body_chapter.background-E9EBEE {
  background-image: none;
  background-color: #e9ebee;
}

#body_chapter.background-F4F4E4 {
  background-image: none;
  background-color: #f4f4e4;
}

#body_chapter.background-EAE4D3 {
  background-image: none;
  background-color: #eae4d3;
}

#body_chapter.background-D5D8DC {
  background-image: none;
  background-color: #d5d8dc;
}

#body_chapter.background-FAFAC8 {
  background-image: none;
  background-color: #fafac8;
}

#body_chapter.background-EFEFAB {
  background-image: none;
  background-color: #efefab;
}

#body_chapter.background-FFF {
  background-image: none;
  background-color: #fff;
}

#body_chapter.size-16px .chapter-c {
  font-size: 16px;
}

#body_chapter.size-18px .chapter-c {
  font-size: 18px;
}

#body_chapter.size-20px .chapter-c {
  font-size: 20px;
}

#body_chapter.size-22px .chapter-c {
  font-size: 22px;
}

#body_chapter.size-24px .chapter-c {
  font-size: 24px;
}

#body_chapter.size-26px .chapter-c {
  font-size: 26px;
}

#body_chapter.size-28px .chapter-c {
  font-size: 28px;
}

#body_chapter.size-30px .chapter-c {
  font-size: 30px;
}

#body_chapter.size-32px .chapter-c {
  font-size: 32px;
}

#body_chapter.size-34px .chapter-c {
  font-size: 34px;
}

#body_chapter.size-36px .chapter-c {
  font-size: 36px;
}

#body_chapter.size-38px .chapter-c {
  font-size: 38px;
}

#body_chapter.size-40px .chapter-c {
  font-size: 40px;
}

#body_chapter.font-PalatinoLinotype .chapter-c {
  font-family: "Palatino Linotype", serif;
}

#body_chapter.font-Bookerly .chapter-c {
  font-family: Bookerly, serif;
}

#body_chapter.font-Minion .chapter-c {
  font-family: Minion, serif;
}

#body_chapter.font-SegoeUI .chapter-c {
  font-family: "Segoe UI", sans-serif;
}

#body_chapter.font-Roboto .chapter-c {
  font-family: Roboto, sans-serif;
}

#body_chapter.font-RobotoCondensed .chapter-c {
  font-family: "Roboto Condensed", sans-serif;
}

#body_chapter.font-PatrickHand .chapter-c {
  font-family: "Patrick Hand", sans-serif;
}

#body_chapter.font-NoticiaText .chapter-c {
  font-family: "Noticia Text", sans-serif;
}

#body_chapter.font-TimesNewRoman .chapter-c {
  font-family: "Times New Roman", serif;
}

#body_chapter.font-Verdana .chapter-c {
  font-family: Verdana, sans-serif;
}

#body_chapter.font-Tahoma .chapter-c {
  font-family: Tahoma, sans-serif;
}

#body_chapter.font-Arial .chapter-c {
  font-family: Arial, sans-serif;
}

#body_chapter.lineheight-100 .chapter-c {
  line-height: 100%;
}

#body_chapter.lineheight-120 .chapter-c {
  line-height: 120%;
}

#body_chapter.lineheight-140 .chapter-c {
  line-height: 140%;
}

#body_chapter.lineheight-160 .chapter-c {
  line-height: 160%;
}

#body_chapter.lineheight-180 .chapter-c {
  line-height: 180%;
}

#body_chapter.lineheight-200 .chapter-c {
  line-height: 200%;
}

.dark-theme .navbar-default,
.dark-theme.navbar-default {
  background: #242f39;
  border-bottom-color: #242f39;
  box-shadow: 0 0 1px rgba(10, 10, 10, 0.4);
}

.dark-theme .navbar-breadcrumb {
  background: #2e3740;
  border-top: 0;
}

.dark-theme .breadcrumb a,
.dark-theme .breadcrumb-container {
  color: #b1b1b1;
}

.dark-theme .breadcrumb > .active,
.dark-theme .breadcrumb > .active a {
  color: #dadada;
}

.dark-theme .form-control,
.dark-theme .input-group-addon,
.dark-theme .list-group-item,
.dark-theme .list-search-res,
.dark-theme .search-holder .form-control {
  box-shadow: inset 0 0 2px rgba(0, 0, 0, 0.3);
  background: #1c242d;
  border: 1px solid #192128;
  color: #fff;
}

.dark-theme .search-holder > .input-group-btn > .btn {
  color: #92bb35;
  border-color: #92bb35;
  text-shadow: 1px 1px 0 rgba(10, 10, 10, 0.4);
  background: rgba(10, 10, 10, 0.15);
  border: 1px solid;
  margin-left: 2px;
}

.btn-chapter-nav {
  padding: 11px 22px;
  font-size: 16px;
  height: 47px;
}
</style>
