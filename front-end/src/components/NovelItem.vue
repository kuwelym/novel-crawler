<template>
  <div class="row novel-container">
    <div class="col-3">
      <div>
        <img :src="novel.coverUrl" class="novel-cover" :alt="novel.title" />
      </div>
    </div>
    <div class="col-9">
      <h3 class="novel-title">
        <router-link
          class="navbar-brand"
          :to="`/detail/${novelUrl}`"
          style="font-weight: bold"
        >
          {{ novel.title }}
        </router-link>
      </h3>
      <h2 class="novel-author">
        <span>{{ novel.author }}</span>
      </h2>
    </div>
  </div>
</template>

<script>
import { convertToSlug } from "@/utils";

export default {
  name: "NovelItem",
  props: {
    novel: {
      type: Object,
      required: true,
    },
  },

  computed: {
    novelUrl() {
      return convertToSlug(this.novel.title);
    },
  },
  methods: {},
};
</script>

<style>
.novel-container {
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  margin: 5px;
  padding: 5px;
  font-family: "Roboto Condensed", Tahoma, sans-serif;
}

.novel-cover {
  max-height: 100%;
  max-width: 100%;
  border: 1px solid #e7e7e7;
}

.novel-title,
.novel-author {
  font-weight: bold;
  font-size: 18px;
  margin-top: 20px;
}

.novel-title a {
  text-decoration: none;
  color: #4e4e4e;
}

.novel-title a:hover {
  text-decoration: underline;
}

.novel-author {
  font-size: 15px;
  color: #4e4e4e;
  font-style: italic;
}
</style>
