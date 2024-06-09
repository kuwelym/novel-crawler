import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import PulseLoader from "vue-spinner/src/PulseLoader.vue";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle.js";

import ToastPlugin from "vue-toast-notification";
import "vue-toast-notification/dist/theme-bootstrap.css";

const app = createApp(App);
app.use(router);
app.use(store);
app.use(ToastPlugin);

app.component("font-awesome-icon", FontAwesomeIcon);
app.component("pulse-loader", PulseLoader);

app.mount("#app");
