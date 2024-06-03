

<template>
    <div class="container clearfix list-index" id="list-index">
    <div class="list list-truyen list-new col-xs-12 col-sm-12 col-md-8 col-truyen-main">
      <div class="title-list">
        <h2>
          <a class="text-title" href="https://truyenfull.vn/danh-sach/truyen-moi/" title="Truyện mới">
            Truyện mới cập nhật
            <FontAwesomeIcon class="glyphicon glyphicon-chevron-right ms-1" icon={faChevronRight} />
          </a>
        </h2>
        <a href="https://truyenfull.vn/danh-sach/truyen-moi/" title="Truyện mới">
          <span class="glyphicon glyphicon-menu-right"></span>
        </a>
        <select class="new-select form-select form-select-lg">
          <option value="all">Tất cả</option>
          <option value="1">Tiên Hiệp</option>
          <option value="2">Kiếm Hiệp</option>
          <option value="3">Ngôn Tình</option>
          <option value="13">Đam Mỹ</option>
          <option value="20">Quan Trường</option>
          <option value="6">Võng Du</option>
          <option value="5">Khoa Huyễn</option>
          <option value="39">Hệ Thống</option>
          <option value="8">Huyền Huyễn</option>
          <option value="7">Dị Giới</option>
          <option value="19">Dị Năng</option>
          <option value="10">Quân Sự</option>
          <option value="11">Lịch Sử</option>
          <option value="15">Xuyên Không</option>
          <option value="38">Xuyên Nhanh</option>
          <option value="17">Trọng Sinh</option>
          <option value="18">Trinh Thám</option>
          <option value="16">Thám Hiểm</option>
          <option value="9">Linh Dị</option>
          <option value="30">Ngược</option>
          <option value="36">Sủng</option>
          <option value="21">Cung Đấu</option>
          <option value="22">Nữ Cường</option>
          <option value="24">Gia Đấu</option>
          <option value="23">Đông Phương</option>
          <option value="4">Đô Thị</option>
          <option value="14">Bách Hợp</option>
          <option value="25">Hài Hước</option>
          <option value="27">Điền Văn</option>
          <option value="28">Cổ Đại</option>
          <option value="29">Mạt Thế</option>
          <option value="26">Truyện Teen</option>
          <option value="32">Phương Tây</option>
          <option value="33">Nữ Phụ</option>
          <option value="34">Light Novel</option>
          <option value="35">Việt Nam</option>
          <option value="37">Đoản Văn</option>
          <option value="31">Khác</option>
        </select>
      </div>
      <div class="row" itemscope itemtype="https://schema.org/Book">
        <div class="col-xs-9 col-sm-6 col-md-5 col-title">
          <FontAwesomeIcon class="glyphicon glyphicon-chevron-right me-1" icon={faChevronRight} />
          <span></span>
          <h3 itemprop="name">
            <a href="https://truyenfull.vn/hoi-am-cua-anh/" title="Hơi Ấm Của Anh" itemprop="url">
              Hơi Ấm Của Anh
            </a>
          </h3>
          <span class="label-title label-full"></span>
          <span class="label-title label-hot"></span>
        </div>
        <div class="hidden-xs col-sm-3 col-md-3 col-cat">
          <a itemprop="genre" href="https://truyenfull.vn/the-loai/ngon-tinh/" title="Ngôn Tình">
            Ngôn Tình
          </a>
          ,
          <a itemprop="genre" href="https://truyenfull.vn/the-loai/sung/" title="Sủng">
            Sủng
          </a>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-2 col-chap text-info">
          <a href="https://truyenfull.vn/hoi-am-cua-anh/chuong-108/" title="Hơi Ấm Của Anh - Chương 108">
            <span class="chapter-text">
              <span>Chương</span>
            </span>
            108
          </a>
        </div>
        <div class="hidden-xs hidden-sm col-md-2 col-time">1 giờ trước </div>
      </div>
      <div class="row" itemscope itemtype="https://schema.org/Book">
        <div class="col-xs-9 col-sm-6 col-md-5 col-title">
          <FontAwesomeIcon class="glyphicon glyphicon-chevron-right me-1" icon={faChevronRight} />
          <span></span>
          <h3 itemprop="name">
            <a href="https://truyenfull.vn/hoi-am-cua-anh/" title="Hơi Ấm Của Anh" itemprop="url">
              Hơi Ấm Của Anh
            </a>
          </h3>
          <span class="label-title label-full"></span>
          <span class="label-title label-hot"></span>
        </div>
        <div class="hidden-xs col-sm-3 col-md-3 col-cat">
          <a itemprop="genre" href="https://truyenfull.vn/the-loai/ngon-tinh/" title="Ngôn Tình">
            Ngôn Tình
          </a>
          ,
          <a itemprop="genre" href="https://truyenfull.vn/the-loai/sung/" title="Sủng">
            Sủng
          </a>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-2 col-chap text-info">
          <a href="https://truyenfull.vn/hoi-am-cua-anh/chuong-108/" title="Hơi Ấm Của Anh - Chương 108">
            <span class="chapter-text">
              <span>Chương</span>
            </span>
            108
          </a>
        </div>
        <div class="hidden-xs hidden-sm col-md-2 col-time">1 giờ trước </div>
      </div>
      <div class="col-md-4 col-truyen-side">
        <div class="visible-md-block visible-lg-block text-center">
          <div class="list list-truyen list-history col-xs-12">
            <div class="title-list">
                <h2>Truyện đang đọc</h2>
            </div>
            <div class="row">
              <div class="col-md-5 col-lg-7 text-edit">
                <span class="glyphicon glyphicon-chevron-right">
                </span>
                <h3 itemprop="name">
                  <a href="https://truyenfull.vn/tu-cam-270192/">
                    Tự Cẩm
                  </a>
                </h3>
              </div>
              
              <div class="col-md-4 text-info">
                  <a href="https://truyenfull.vn/tu-cam-270192/chuong-1/">
                    Đọc tiếp
                  </a>
                  <span class="chapter-text">
                    Chương 1
                  </span>
              </div>
            </div>
            <div class="row">
              <div class="col-md-5 col-lg-7 text-edit">
                <span class="glyphicon glyphicon-chevron-right">
                </span>
                <h3 itemprop="name">
                  <a href="https://truyenfull.vn/tu-cam-270192/">
                    Tự Cẩm
                  </a>
                </h3>
              </div>
              
              <div class="col-md-4 text-info">
                  <a href="https://truyenfull.vn/tu-cam-270192/chuong-1/">
                    Đọc tiếp
                  </a>
                  <span class="chapter-text">
                    Chương 1
                  </span>
              </div>
            </div>
            <div class="row">
              <div class="col-md-5 col-lg-7 text-edit">
                <span class="glyphicon glyphicon-chevron-right">
                </span>
                <h3 itemprop="name">
                  <a href="https://truyenfull.vn/tu-cam-270192/">
                    Tự Cẩm
                  </a>
                </h3>
              </div>
              
              <div class="col-md-4 text-info">
                  <a href="https://truyenfull.vn/tu-cam-270192/chuong-1/">
                    Đọc tiếp
                  </a>
                  <span class="chapter-text">
                    Chương 1
                  </span>
              </div>
            </div>
            <div class="row">
              <div class="col-md-5 col-lg-7 text-edit">
                <span class="glyphicon glyphicon-chevron-right">
                </span>
                <h3 itemprop="name">
                  <a href="https://truyenfull.vn/tu-cam-270192/">
                    Tự Cẩm
                  </a>
                </h3>
              </div>
              
              <div class="col-md-4 text-info">
                  <a href="https://truyenfull.vn/tu-cam-270192/chuong-1/">
                    Đọc tiếp
                  </a>
                  <span class="chapter-text">
                    Chương 1
                  </span>
              </div>
            </div>
          </div>
          <div class="list list-truyen list-cat col-xs-12">
            <div class="title-list">
              <h4>Thể loại truyện</h4>
            </div>
            <div class="row1">
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/tien-hiep/" title="Truyện Tiên Hiệp">
                  Tiên Hiệp
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/kiem-hiep/" title="Truyện Kiếm Hiệp">
                  Kiếm Hiệp
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/ngon-tinh/" title="Truyện Ngôn Tình">
                  Ngôn Tình
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/dam-my/" title="Truyện Đam Mỹ">
                  Đam Mỹ
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/quan-truong/" title="Truyện Quan Trường">
                  Quan Trường
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/vong-du/" title="Truyện Võng Du">
                  Võng Du
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/khoa-huyen/" title="Truyện Khoa Huyễn">
                  Khoa Huyễn
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/he-thong/" title="Truyện Hệ Thống">
                  Hệ Thống
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/huyen-huyen/" title="Truyện Huyền Huyễn">
                  Huyền Huyễn
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/di-gioi/" title="Truyện Dị Giới">
                  Dị Giới
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/di-nang/" title="Truyện Dị Năng">
                  Dị Năng
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/quan-su/" title="Truyện Quân Sự">
                  Quân Sự
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/lich-su/" title="Truyện Lịch Sử">
                  Lịch Sử
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/xuyen-khong/" title="Truyện Xuyên Không">
                  Xuyên Không
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/xuyen-nhanh/" title="Truyện Xuyên Nhanh">
                  Xuyên Nhanh
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/trong-sinh/" title="Truyện Trọng Sinh">
                  Trọng Sinh
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/trinh-tham/" title="Truyện Trinh Thám">
                  Trinh Thám
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/tham-hiem/" title="Truyện Thám Hiểm">
                  Thám Hiểm
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/linh-di/" title="Truyện Linh Dị">
                  Linh Dị
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/nguoc/" title="Truyện Ngược">
                  Ngược
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/sung/" title="Truyện Sủng">
                  Sủng
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/cung-dau/" title="Truyện Cung Đấu">
                  Cung Đấu
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/nu-cuong/" title="Truyện Nữ Cường">
                  Nữ Cường
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/gia-dau/" title="Truyện Gia Đấu">
                  Gia Đấu
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/dong-phuong/" title="Truyện Đông Phương">
                  Đông Phương
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/do-thi/" title="Truyện Đô Thị">
                  Đô Thị
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/bach-hop/" title="Truyện Bách Hợp">
                  Bách Hợp
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/hai-huoc/" title="Truyện Hài Hước">
                  Hài Hước
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/dien-van/" title="Truyện Điền Văn">
                  Điền Văn
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/co-dai/" title="Truyện Cổ Đại">
                  Cổ Đại
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/mat-the/" title="Truyện Mạt Thế">
                  Mạt Thế
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/truyen-teen/" title="Truyện Truyện Teen">
                  Truyện Teen
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/phuong-tay/" title="Truyện Phương Tây">
                  Phương Tây
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/nu-phu/" title="Truyện Nữ Phụ">
                  Nữ Phụ
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/light-novel/" title="Truyện Light Novel">
                  Light Novel
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/viet-nam/" title="Truyện Việt Nam">
                  Việt Nam
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/doan-van/" title="Truyện Đoản Văn">
                  Đoản Văn
                </a>
              </div>
              <div class="col-xs-6">
                <a href="https://truyenfull.vn/the-loai/khac/" title="Truyện Khác">
                  Khác
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
      
      <script>
    
    
    //   import { getNovelDetail } from "@/services/apiService";
    //   import { scrollToTopSmooth } from "@/utils";
    //   import VPagination from "@hennge/vue3-pagination";
      import "@hennge/vue3-pagination/dist/vue3-pagination.css";
      
      export default {
        name: "HomePagePart2",
        // components: {
        //   VPagination,
        // },
        data() {
          return {
            
          };
        },
        // methods: {
        //   async fetchNovelDetail() {
        //     const novelName = this.$route.params.novelName;
        //     const respone = await getNovelDetail(novelName, this.currentPage);
      
        //     this.novel = respone.data.content[0];
        //     this.totalPages = respone.data.totalPages;
        //     this.isLoading = false;
      
        //     scrollToTopSmooth();
        //   },
        //   handleChangePage(newPage) {
        //     this.currentPage = newPage;
        //   },
        //   chapterUrl(chapter) {
        //     const novelName = this.$route.params.novelName;
      
        //     const chapterNumberMatch = chapter.match(/Chương\s*(\d+)/i);
        //     const chapterNumber = chapterNumberMatch ? chapterNumberMatch[1] : "";
      
        //     return novelName + "/" + chapterNumber;
        //   },
        // },
        // async mounted() {
        //   await this.fetchNovelDetail();
        // },
        // watch: {
        //   async currentPage() {
        //     await this.fetchNovelDetail();
        //   },
        // },
      };
</script>

<style>

.text-title:hover {
    text-decoration: none!important;
}

#list-index a {
    color: #4e4e4e!important;
    text-decoration: none;
}

#list-index a:hover {
    text-decoration: underline;
}

.container {
  margin-right: auto;
  margin-left: auto;
  padding-left: 15px;
  padding-right: 15px;
}

.list-index {
  position: relative;
}

.container {
    width: 1170px;
  }

.clearfix::after {
  content: '';
  clear: both;
  display: table;
}

.col-xs-12 {
  width: 100%;
}


.col-xs-6 {
  width: 50%;
}

.col-xs-6,
.col-xs-12 {
  float: left;
}


.list {
  display: table;
  text-align: left;
}

.list > .row {
  font-family: 'Roboto Condensed', Tahoma, sans-serif;
  font-size: 14px;
}

.intro-index {
  margin-bottom: 15px;
  height: 448px;
  overflow: hidden;
  position: relative;
}

.title-list {
  text-align: left;
  height: 40px;
  text-transform: uppercase;
  border-bottom: 1px solid #ccc;
  position: relative;
}

.text-edit {
  padding: 0px!important;
}

#list-index .col-truyen-side .list-history .text-info {
  padding: 0px 0px 0px 5px!important;
}

.title-list > h2 {
  font-family: 'Roboto Condensed', Tahoma, sans-serif;
  font-size: 20px;
  height: 40px;
  line-height: 40px;
  max-width: 100%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  border-bottom: 1px solid #4e4e4e;
  margin: 0;
  display: inline-block;
  color: #4e4e4e;
}

.text-title {
  color: #4e4e4e;
  text-decoration: none;
}


.new-select {
  line-height: 40px;
  height: 40px;
  width: 150px;
  border-radius: 0;
  position: absolute;
  top: 0;
  right: 0;
  padding: 3px;
  font-family: 'Roboto Condensed', Tahoma, sans-serif;
  text-transform: uppercase;
  background-color: transparent;
}

.new-select {
  font-size: 20px !important;
  color: #4e4e4e;
  border-color: transparent;
  border-bottom-color: #4e4e4e;
  border: 2px solid;
  background-color: #f4f4f4;
}

.form-select:focus {
  border: 2px solid #86b7fe;
  outline: 0;
  box-shadow: none;
}

.glyphicon {
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: 'Glyphicons Halflings';
  font-style: normal;
  font-weight: normal;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.list > .row > div,
#truyen-slide .caption > h3,
#truyen-slide .caption > small {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}


#list-index .row > div {
  height: 40px;
  line-height: 40px;
  border-left: 1px dashed #ccc;
  border-bottom: 1px dashed #ccc;
  overflow: hidden;
}

.row > .col-title {
  border-left: 0px;
}

.row {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0!important;
}

.row1 {
  --bs-gutter-y: 0;
  display: flex;
  flex-wrap: wrap;
  margin-top: calc(-1 * var(--bs-gutter-y));
  margin-right: calc(-0.5 * var(--bs-gutter-x));
  margin-left: calc(-0.5 * var(--bs-gutter-x));
}

.col-title {
  border-right: 1px;
}

#list-index .text-info a {
  color: #31708f!important;
}

.title-list > h4 {
  font-family: 'Roboto Condensed', Tahoma, sans-serif;
  font-size: 20px!important;
  height: 40px;
  line-height: 40px;
  max-width: 100%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  border-bottom: 1px solid #4e4e4e;
  color: #4e4e4e;
}

@media (min-width: 992px) {
  .list-index .col-truyen-side {
    top: 0;
    right: 0;
    position: absolute;
  }

  .col-truyen-side {
    padding: 0 0 0 15px;
  }

  .col-truyen-side .list.list-truyen {
    margin: 10px 0;
    padding-right: 14px;
    padding-left: 14px;
  }
}

@media (max-width: 1024px) {
  .col-truyen-side .list.list-truyen {
    padding: 0;
  }
}

@media (max-width: 991px) {
  .container > .col-truyen-side {
    padding-left: 0;
    padding-right: 0;
  }
}


@media (max-width: 992px) {
  .col-truyen-side {
    display: none;
  }
}


.col-truyen-main {
  padding: 0;
}

@media (min-width: 992px) {
  .col-truyen-main {
    width: 73%;
  }
}
@media (min-width: 992px) {
  .col-truyen-side {
    width: 27%;
    padding: 0 0 0 7.5px;
  }
}

.col-truyen-side .list {
  background-color: #ecf0f1;
  border: 1px solid #d9e1e4;
}

.col-truyen-side .list.list-cat > .row1 > div:nth-child(odd) {
  padding-left: 40px !important;
}

.col-truyen-side .list.list-cat > .row1 > div {
  height: 30px !important;
  line-height: 35px !important;
  border: 0 !important;
  padding-left: 20px !important;
}

.col-truyen-side .list > .row1 {
  background-color: #ecf0f1;
}

.list>.row>div:first-child {
    border-left: 0!important;
    padding-left: 0;
}

.list > .row1 {
  display: table-row;
  font-family: 'Roboto Condensed', Tahoma, sans-serif;
  font-size: 13px;
}

.list-cat > .row1 {
  font-size: 16px;
}

@media (min-width: 992px) {
  .col-md-4,
  .col-md-8 {
    float: left;
  }
}

@media (min-width: 1025px) {
  .list {
    border: 0;
  }
}

@media (min-width: 992px) {
  .col-truyen-side {
    top: 0;
    right: 0;
    position: absolute;
  }
}

@media (min-width: 1200px) {
  .visible-lg-block {
    display: block !important;
  }
}

#list-index .list h3 a {
  font-size: 16px;
}

.col-truyen-side .list > .row1 > div {
  max-width: 270px;
  padding: 0;
}

@media (max-width: 1200px) {
  .list-cat > .row {
    font-size: 14px;
  }
} 

</style> 
    
