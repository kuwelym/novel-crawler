<template>
  <nav class="navbar navbar-expand-lg" style="background-color: #eeedeb">
    <div class="container-fluid">
      <router-link
        class="navbar-brand"
        to="/"
        @click="updateFilter('truyen-hot')"
        style="font-weight: bold"
      >
        Web Truyện Siêu Cấp
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item dropdown">
            <router-link
              class="nav-link dropdown-toggle"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              to="#"
            >
              Danh sách
            </router-link>
            <ul class="dropdown-menu">
              <li>
                <router-link
                  class="dropdown-item"
                  to="/truyen-moi"
                  @click="updateFilter('truyen-moi')"
                >
                  Truyện Mới
                </router-link>
              </li>

              <li>
                <router-link
                  class="dropdown-item"
                  to="/truyen-full"
                  @click="updateFilter('truyen-full')"
                >
                  Truyện Full
                </router-link>
              </li>

              <li>
                <router-link
                  class="dropdown-item"
                  to="/tien-hiep-hay"
                  @click="updateFilter('tien-hiep-hay')"
                >
                  Truyện Tiên Hiệp Hay
                </router-link>
              </li>

              <li>
                <router-link
                  class="dropdown-item"
                  to="/kiem-hiep-hay"
                  @click="updateFilter('kiem-hiep-hay')"
                >
                  Truyện Kiếm Hiệp Hay
                </router-link>
              </li>
            </ul>
          </li>
        </ul>
        <form class="d-flex" @submit.prevent="handleSearch">
          <input
            ref="searchInput"
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
            :value="searchText"
          />
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "HeaderComponent",
  computed: {
    ...mapState(["searchText"]),
  },
  methods: {
    ...mapActions(["updateSearchText", "updateFilter"]),
    async handleSearch() {
      await this.$router.push("/");
      const searchText = this.$refs.searchInput.value.trim();
      this.updateSearchText(searchText);
    },
  },
};
</script>

<style></style>
