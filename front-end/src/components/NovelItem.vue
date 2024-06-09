<template>
  <div class="row form-row">
    <div class="col-xs-3">
      <div>
        <img :src="novel.coverUrl" class="cover" :alt="novel.title" />
      </div>
    </div>
    <div class="col-xs-7">
      <div>
        <span class="glyphicon glyphicon-book">
          <font-awesome-icon :icon="['fas', 'book']" />
        </span>
        <h3 class="truyen-title">
          <router-link
            :to="{ name: 'NovelDetail', params: { novelName: novel.nameUrl } }"
          >
            {{ novel.title }}
          </router-link>
        </h3>
        <span class="author">
          <span class="glyphicon glyphicon-pencil">
            <font-awesome-icon class="pencil" :icon="['fas', 'pencil']" />
          </span>
          {{ novel.author }}
        </span>
      </div>
    </div>
    <div class="col-xs-2 text-info">
      <div>
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
            <span>Chương </span>
          </span>
          {{ novel.newestChapter }}
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { library } from "@fortawesome/fontawesome-svg-core";
import { faBook, faPencil } from "@fortawesome/free-solid-svg-icons";
library.add(faBook, faPencil);

export default {
  name: "NovelItem",
  props: {
    novel: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style>
.container {
  margin-right: auto;
  margin-left: auto;
  padding-left: 15px;
  padding-right: 15px;
}

.container {
  width: 1170px;
}

.list-page {
  text-align: center;
  font-size: 13px;
}

.col-truyen-main,
.list-page .col-truyen-main .list-truyen {
  padding: 0;
}

.list {
  display: table;
  text-align: left;
  border: 0;
}

.col-truyen-main {
  width: 73%;
}

.col-xs-12 {
  width: 100%;
}

.col-xs-7,
.col-xs-12 {
  float: left;
}

.title-list {
  text-align: left;
  height: 40px;
  text-transform: uppercase;
  border-bottom: 1px solid #ccc;
  position: relative;
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

.filter {
  border-bottom: 1px solid #ccc;
}

.filter {
  line-height: 40px;
  height: 40px;
  width: initial;
  border-radius: 0;
  position: absolute;
  top: 0;
  right: 0;
  padding: 5px;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  text-transform: uppercase;
  background-color: transparent;
}

.filter a {
  color: #4e4e4e;
  text-decoration: none;
}
.list > .row {
  display: flex;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
  font-size: 15px;
}

.list-page .list > .row > div {
  height: 90px;
  line-height: 20px;
  display: table;
  table-layout: fixed;
  border-left: 0;
  border-bottom: 1px dashed #ccc;
  white-space: normal;
  padding-left: 10px;
  padding-right: 10px;
}

.form-row .col-xs-3 {
  width: 25% !important;
}

.form-row {
  margin: 0px !important;
}

.list-page .list > .row > div > div {
  display: table-cell;
  vertical-align: middle;
}

.list-page .list > .row > div > div > .cover {
  max-height: 100%;
  max-width: 100%;
  border: 1px solid #e7e7e7;
}

img {
  vertical-align: middle;
}

.form-row .col-xs-7 {
  width: 58.33333333% !important;
}

.col-xs-7 {
  position: relative;
  min-height: 1px;
  padding-left: 15px;
  padding-right: 15px;
}

.list-page .list > .row > div > div > .truyen-title {
  font-weight: bold;
  font-size: 18px !important;
  margin-top: 0 !important;
  margin-bottom: 5px !important;
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

.glyphicon-book {
  margin-right: 5px;
}

#list-page .list h3 {
  display: inline;
}

#list-page a {
  color: #4e4e4e;
  text-decoration: none;
}

.label-title {
  padding: 1px 3px;
  font-size: 13px;
  vertical-align: bottom;
  margin-left: 5px;
}

/* LABEL NEW - HOT - FULL */
.label-new {
  border: 1px solid #8eb3fd;
  color: #8eb3fd;
}

.label-new:before {
  content: "New";
}

.label-hot {
  border: 1px solid #fd8383;
  color: #fd8383;
}

.label-hot:before {
  content: "Hot";
}

.label-full {
  border: 1px solid #86ad86;
  color: #86ad86;
}

.label-full:before {
  content: "Full";
}

.list-page .list > .row > div > div > .author {
  font-style: italic;
  display: block;
}

.list-page .list > .row > div > div {
  display: table-cell;
  vertical-align: middle;
}

.list-page .list > .row > div > div > .author > .glyphicon.glyphicon-pencil {
  font-size: 11px;
}

.glyphicon-book {
  margin-right: 5px;
}

.pencil {
  margin-bottom: 2px;
}

#list-page .text-info {
  color: #31708f !important;
}

#list-page .text-info a {
  color: #31708f !important;
}

#list-page .col-xs-2 {
  width: 16.66666667%;
}
</style>
