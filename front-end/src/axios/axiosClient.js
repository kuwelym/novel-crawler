import axios from "axios";
import { useToast } from "vue-toast-notification";
import "vue-toast-notification/dist/theme-sugar.css";

const API_BASE_URL = process.env.VUE_APP_API_BASE_URL;
const $toast = useToast();

const axiosClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

axiosClient.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    const { response } = error;
    if (response) {
      const { status, data } = response;
      switch (status) {
        case 400:
          $toast.error(data.message || "Bad Request");
          break;
        case 401:
          $toast.error(data.message || "Unauthorized");
          break;
        case 403:
          $toast.error(data.message || "Forbidden");
          break;
        case 404:
          $toast.error(data.message || "Not Found");
          break;
        case 500:
          // $toast.error(data.message || "Internal Server Error");
          break;
        default:
          $toast.error(data.message || "An error occurred");
          break;
      }
    } else {
      $toast.error("Network error");
    }
    return Promise.reject(error);
  }
);

export default axiosClient;
