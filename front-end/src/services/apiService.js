import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

export const getAllNovels = async (filter, page) => {
  try {
    const response = await axios.get(
      `${API_BASE_URL}/novels?filter=${filter}&page=${page}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
};

export const searchNovel = async (searchText, page) => {
  try {
    searchText = searchText.replace(/\s{2,}/g, " ").trim();
    searchText = searchText.replace(/\s/g, "+");

    const response = await axios.post(`${API_BASE_URL}/novels/search`, {
      searchText,
      page,
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
};

export const getNovelDetail = async (novelName, page) => {
  try {
    const response = await axios.get(
      `${API_BASE_URL}/novels/detail?novelName=${novelName}&page=${page}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
};

export const getNovelChapter = async (novelName, chapterNumber) => {
  try {
    const response = await axios.get(
      `${API_BASE_URL}/novels/chapter?novelName=${novelName}&chapterNumber=${chapterNumber}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
};
