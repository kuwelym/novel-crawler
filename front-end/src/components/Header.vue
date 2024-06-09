<template>
  <nav class="navbar navbar-default navbar-static-top" id="headerId">
    <div class="container">
      <div class="navbar-header">
        <router-link class="header-logo" to="/" />
      </div>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="control nav navbar-nav">
          <!-- Types -->
          <li class="dropdown">
            <router-link
              class="dropdown-toggle"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              to="#"
            >
              <span>
                <font-awesome-icon :icon="['fas', 'list']" />
              </span>

              Danh sách
            </router-link>
            <ul class="dropdown-menu">
              <li v-for="(type, index) in novelTypes" :key="index">
                <router-link
                  class="dropdown-item"
                  :to="{ name: 'NovelListType', params: { type: type.url } }"
                  @click="updateType(type.url)"
                >
                  {{ type.name }}
                </router-link>
              </li>
            </ul>
          </li>

          <!-- Genres -->
          <li class="dropdown">
            <router-link
              class="dropdown-toggle"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              to="#"
            >
              <span>
                <font-awesome-icon :icon="['fas', 'list']" />
              </span>
              Thể loại
            </router-link>
            <ul class="dropdown-menu multi-column">
              <div class="row">
                <li
                  v-for="(genre, index) in novelGenres"
                  :key="index"
                  class="col-md-4"
                >
                  <router-link
                    class="dropdown-item"
                    :to="{
                      name: 'NovelListGenre',
                      params: { genre: genre.url },
                    }"
                    @click="updateGenre(genre.url)"
                  >
                    {{ genre.name }}
                  </router-link>
                </li>
              </div>
            </ul>
          </li>

          <!-- Text style -->
          <li class="dropdown">
            <router-link
              class="dropdown-toggle"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              to="#"
            >
              <span>
                <font-awesome-icon :icon="['fas', 'gear']" />
              </span>
              Tùy Chỉnh
            </router-link>
            <div
              class="dropdown-menu dropdown-menu-right settings"
              aria-labelledby="dropdown-basic"
            >
              <form class="form-horizontal">
                <!-- Font size -->
                <div class="form-group form-group-sm row">
                  <label class="col-sm-2 col-md-5 control-label">Cỡ chữ</label>
                  <div class="col-sm-5 col-md-7">
                    <select
                      class="form-control"
                      size="lg"
                      :value="fontSize"
                      @change="updateFontSize($event.target.value)"
                    >
                      <option value="16px">16</option>
                      <option value="18px">18</option>
                      <option value="20px">20</option>
                      <option value="22px">22</option>
                      <option value="24px">24</option>
                      <option value="26px">26</option>
                      <option value="28px">28</option>
                      <option value="30px">30</option>
                      <option value="32px">32</option>
                      <option value="34px">34</option>
                      <option value="36px">36</option>
                      <option value="38px">38</option>
                      <option value="40px">40</option>
                    </select>
                  </div>
                </div>

                <!-- Font family -->
                <div class="form-group form-group-sm row">
                  <label class="col-sm-2 col-md-5 control-label"
                    >Font chữ</label
                  >
                  <div class="col-sm-5 col-md-7">
                    <select
                      class="form-control"
                      size="lg"
                      :value="fontFamily"
                      @change="updateFontFamily($event.target.value)"
                    >
                      <option value="'Palatino Linotype', serif">
                        Palatino Linotype
                      </option>
                      <option value="Bookerly, serif">Bookerly</option>
                      <option value="Minion, serif">Minion</option>
                      <option value="'Segoe UI', sans-serif">Segoe UI</option>
                      <option value="Roboto, sans-serif">Roboto</option>
                      <option value="'Roboto Condensed', sans-serif">
                        Roboto Condensed
                      </option>
                      <option value="'Patrick Hand', sans-serif">
                        Patrick Hand
                      </option>
                      <option value="'Noticia Text', sans-serif">
                        Noticia Text
                      </option>
                      <option value="'Times New Roman', serif">
                        Times New Roman
                      </option>
                      <option value="Verdana, sans-serif">Verdana</option>
                      <option value="Tahoma, sans-serif">Tahoma</option>
                      <option value="Arial, sans-serif">Arial</option>
                    </select>
                  </div>
                </div>

                <!-- Text color -->
                <div class="form-group form-group-sm row">
                  <label class="col-sm-2 col-md-5 control-label">Màu chữ</label>
                  <div class="col-sm-5 col-md-7">
                    <select
                      class="form-control"
                      size="lg"
                      :value="color"
                      @change="updateFontColor($event.target.value)"
                    >
                      <option value="black">Black</option>
                      <option value="red">Red</option>
                      <option value="blue">Blue</option>
                      <option value="green">Green</option>
                    </select>
                  </div>
                </div>

                <!-- Line height -->
                <div class="form-group form-group-sm row">
                  <label class="col-sm-2 col-md-5 control-label"
                    >Chiều cao dòng</label
                  >
                  <div class="col-sm-5 col-md-7">
                    <select
                      class="form-control"
                      size="lg"
                      :value="lineHeight"
                      @change="updateLineHeight($event.target.value)"
                    >
                      <option value="100%">100%</option>
                      <option value="120%">120%</option>
                      <option value="140%">140%</option>
                      <option value="160%">160%</option>
                      <option value="180%">180%</option>
                      <option value="200%">200%</option>
                    </select>
                  </div>
                </div>

                <!-- Reset text style -->
                <div class="form-group form-group-sm row text-center">
                  <button
                    class="col-12 control-label"
                    @click="resetTextStyle()"
                    type="button"
                  >
                    Mặc định
                  </button>
                </div>
              </form>
            </div>
          </li>
        </ul>
        <form class="navbar-form navbar-right" @submit.prevent="handleSearch">
          <div class="input-group search-holder">
            <input
              ref="searchInput"
              class="form-control"
              type="search"
              placeholder="Tìm kiếm ..."
              aria-label="Search"
              :value="searchText"
            />
            <div class="input-group-btn">
              <button
                class="btn btn-default"
                type="submit"
                aria-label="Tìm kiếm"
                role="search"
              >
                <span class="glyphicon">
                  <font-awesome-icon :icon="['fas', 'magnifying-glass']" />
                </span>
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapActions, mapState, mapGetters } from "vuex";
import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faList,
  faGear,
  faMagnifyingGlass,
} from "@fortawesome/free-solid-svg-icons";
import { getHeaderData } from "@/services/apiService";

library.add(faList, faGear, faMagnifyingGlass);

export default {
  name: "HeaderComponent",
  data() {
    return {
      novelGenres: [],
      novelTypes: [],
    };
  },
  computed: {
    ...mapGetters(["searchText"]),
    ...mapState(["textStyle"]),
    isDarkTheme() {
      return this.textStyle.isDarkTheme;
    },
    fontSize() {
      return this.textStyle.fontSize;
    },
    fontFamily() {
      return this.textStyle.fontFamily;
    },
    color() {
      return this.textStyle.color;
    },
    lineHeight() {
      return this.textStyle.lineHeight;
    },
  },
  methods: {
    ...mapActions([
      "updateSearchText",
      "updateType",
      "updateGenre",
      "resetTextStyle",
      "updateFontSize",
      "updateFontFamily",
      "updateFontColor",
      "updateLineHeight",
    ]),
    async handleSearch() {
      const searchText = this.$refs.searchInput.value.trim();
      this.updateSearchText(searchText);
      await this.$router.push({ name: "NovelListSearch" });
    },
  },
  async mounted() {
    const response = await getHeaderData();

    this.novelGenres = response.data.novelGenres;
    this.novelTypes = response.data.novelTypes;
  },
};
</script>

<style>
@media (min-width: 1200px) {
  .container {
    max-width: 1170px !important;
  }
}

.btn-default {
  height: 32px !important;
  color: #333 !important;
  background-color: #fff !important;
  border-color: #ccc;
}

.navbar {
  min-height: 0;
}

.navbar-default {
  background-color: #ececec;
  border-color: #ececec;
}

.navbar-default {
  padding: 0px !important;
  background-color: #14425d;
  background-position-x: -50px;
  background-repeat: no-repeat;
  -moz-box-shadow: 0 0 6px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0 0 6px rgba(0, 0, 0, 0.3);
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.3);
}

.navbar-static-top {
  z-index: 1000;
  border-width: 0 0 1px;
  border-radius: 0;
}

.container {
  margin-right: auto !important;
  margin-left: auto !important;
  padding-left: 15px !important;
  padding-right: 15px !important;
}

.container > .navbar-header {
  margin-right: 0;
  margin-left: 0;
}

.navbar-header {
  float: left;
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

.navbar-default .navbar-collapse {
  border-color: #e7e7e7;
}

.container > .navbar-collapse {
  margin-right: 0;
  margin-left: 0;
}

.navbar-static-top .navbar-collapse {
  padding-left: 0;
  padding-right: 0;
}

.navbar-nav {
  font-family: "Roboto Condensed", sans-serif;
}

.navbar-nav {
  flex-direction: row !important;
  float: left;
  margin: 0;
}

.navbar-header {
  float: left;
}

.nav {
  margin-bottom: 0;
  padding-left: 0;
  list-style: none;
}

.navbar-nav > li {
  float: left;
}

.nav > li {
  position: relative;
  display: block;
}

.navbar-default .navbar-nav > li > a {
  color: #f2f2f2;
}

.navbar-nav > li > a {
  padding-top: 10px;
  padding-bottom: 10px;
  line-height: 20px;
}

.nav > li > a {
  position: relative;
  display: block;
  padding: 10px 15px;
}

.navbar-collapse.collapse {
  display: block !important;
  height: auto !important;
  padding-bottom: 0;
  overflow: visible !important;
}

.navbar-collapse {
  width: auto !important;
  border-top: 0 !important;
  -webkit-box-shadow: none !important;
  box-shadow: none !important;
}

.navbar-collapse {
  flex-basis: auto !important;
  flex-grow: 1 !important;
  align-items: flex-start !important;
}
.navbar-nav > li > a {
  padding-top: 15px;
  padding-bottom: 15px;
}

.multi-column {
  width: 409px;
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

.navbar-nav > li > .dropdown-menu {
  background-color: #2f566d !important;
}

.navbar-nav > li > .dropdown-menu {
  margin-top: 0 !important;
  border-top-right-radius: 0 !important;
  border-top-left-radius: 0 !important;
}

.dropdown-menu {
  position: absolute !important;
  top: 100% !important;
  left: 0 !important;
  z-index: 1000 !important;
  display: none;
  float: left !important;
  min-width: 160px !important;
  padding: 5px 0 !important;
  margin: 2px 0 0 !important;
  list-style: none !important;
  font-size: 14px !important;
  text-align: left !important;
  background-color: #fff !important;
  border: 1px solid #ccc !important;
  border: 1px solid rgba(0, 0, 0, 0.15) !important;
  border-radius: 4px !important;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175) !important;
  -webkit-background-clip: padding-box !important;
  background-clip: padding-box !important;
}

.navbar-nav .dropdown-menu {
  position: static;
}

.navbar {
  position: relative;
  min-height: 50px;
  margin-bottom: 20px;
  border: 1px solid transparent;
}

.nav > li {
  position: relative;
  display: block;
}

.dropdown-menu > li {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.dropdown-menu > li > a {
  padding: 6px 15px;
  font-size: 15px;
}

.navbar-default .navbar-nav .open > a {
  color: #fff;
  background: #2f566d;
}

.navbar-default .navbar-nav .show .dropdown-menu > li > a,
.dropdown-menu .row > li > a {
  color: #f9f9f9;
}
.dropdown-menu .row > li > a:hover {
  background-color: #14425d !important;
  color: #fff;
}

.dropdown-menu > li > a:hover,
.dropdown-menu > li > a:focus {
  background-color: #14425d;
  color: #fff;
}

#headerId a {
  text-decoration: none !important;
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

.navbar-form .input-group.search-holder .input-group-btn {
  width: auto;
}

.input-group.search-holder {
  width: auto;
}

.input-group-btn {
  position: relative;
  font-size: 0;
  white-space: nowrap;
}
.input-group-addon,
.input-group-btn {
  width: 1%;
  white-space: nowrap;
  vertical-align: middle;
}

.input-group-btn {
  display: table-cell;
}

.search-holder > .input-group-btn > .btn {
  padding: 5px 10px;
  border-left: 0;
  border-radius: 0;
}

.input-group {
  position: relative;
  display: table;
  border-collapse: separate;
}

.input-group .form-control:first-child {
  border-bottom-right-radius: 0;
  border-top-right-radius: 0;
}

.search-holder .form-control {
  height: 32px;
  padding: 5px 10px;
  border-right: 0;
  border-radius: 0;
}

.input-group .form-control {
  position: relative;
  z-index: 2;
  float: left;
  width: 100%;
  margin-bottom: 0;
}

.default .navbar-form {
  border-color: #e7e7e7;
}

.navbar-form .input-group.search-holder .input-group-btn {
  width: auto;
}

.navbar-right {
  float: right !important;
  margin-right: -15px;
}

.form-control {
  color: #555;
}

.dropdown-menu-right {
  left: auto !important;
  right: 0;
}

.settings {
  padding: 10px;
  width: 300px;
}

.settings > form > div {
  color: #f9f9f9;
  text-align: right;
  font-size: 14px;
  margin-bottom: 10px !important;
}

.control-label {
  padding-top: 6px;
  margin-bottom: 0;
}

.drow-menu > a:hover {
  text-decoration: none !important;
}

.form-group {
  margin-bottom: 15px;
}

.form-group-sm .form-control {
  height: 30px;
  padding: 5px 10px;
  font-size: 13px;
  line-height: 1.5;
  border-radius: 3px;
}

.form-group-sm select.form-control {
  height: 30px;
}

.form-check {
  display: flex !important;
  justify-content: flex-start;
  align-items: center;
  padding: 7px;
  margin-top: 12px !important;
}

.dropdown-item {
  color: white !important;
}
</style>
