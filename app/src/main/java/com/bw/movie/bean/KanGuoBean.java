package com.bw.movie.bean;

import java.util.List;

public class KanGuoBean {
    /**
     * result : [{"begStringime":50400000,"cinemaId":10,"endTime":20160000,"imageUrl":"http://172.17.8.100/images/movie/stills/dzd6qmwj/dzd6qmwj1.jpg","movieId":16,"movieName":"碟中谍6：全面瓦解","recordId":5039,"whetherComment":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * begStringime : 50400000
         * cinemaId : 10
         * endTime : 20160000
         * imageUrl : http://172.17.8.100/images/movie/stills/dzd6qmwj/dzd6qmwj1.jpg
         * movieId : 16
         * movieName : 碟中谍6：全面瓦解
         * recordId : 5039
         * whetherComment : 2
         */

        private String begStringime;
        private String cinemaId;
        private String endTime;
        private String imageUrl;
        private String movieId;
        private String movieName;
        private String recordId;
        private String whetherComment;

        public String getBegStringime() {
            return begStringime;
        }

        public void setBegStringime(String begStringime) {
            this.begStringime = begStringime;
        }

        public String getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(String cinemaId) {
            this.cinemaId = cinemaId;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public String getWhetherComment() {
            return whetherComment;
        }

        public void setWhetherComment(String whetherComment) {
            this.whetherComment = whetherComment;
        }
    }
}
