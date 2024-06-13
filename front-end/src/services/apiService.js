import axios from "../axios/axiosClient";

export const getHeaderData = async () => {
  const response = await axios.get(`/novels/header`);
  return response.data;
};

export const getHomeData = async () => {
  const response = await axios.get(`/novels`);
  return response.data;
};

export const getNovelByType = async (type, page) => {
  const response = await axios.get(`/novels/types?type=${type}&page=${page}`);
  return response.data;
};

export const getNovelByGenre = async (genre, page) => {
  const response = await axios.get(
    `/novels/genres?genre=${genre}&page=${page}`
  );
  return response.data;
};

export const searchNovel = async (searchText, page) => {
  searchText = searchText.replace(/\s{2,}/g, " ").trim();
  searchText = searchText.replace(/\s/g, "+");

  const response = await axios.post(`/novels/search`, {
    searchText,
    page,
  });
  return response.data;
};

export const getNovelDetail = async (novelName, page) => {
  const response = await axios.get(
    `/novels/detail?novelName=${novelName}&page=${page}`
  );
  return response.data;
};

export const getNovelChapter = async (serverName, novelName, chapterNumber) => {
  try {
    const response = await axios.get(
      `/novels/chapter?serverName=${serverName}&novelName=${novelName}&chapterNumber=${chapterNumber}`
    );
    return response.data;
  } catch (error) {
    return error.response.data;
  }
};
