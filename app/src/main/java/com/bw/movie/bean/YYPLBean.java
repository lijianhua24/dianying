package com.bw.movie.bean;

import java.util.List;

public class YYPLBean {
    /**
     * result : [{"address":"十八里店西直河商业中心东融国际影城","cinemaId":21,"cinemaName":"东融国际影城西直河店","commentTime":1575337099000,"distance":12251542,"logo":"http://172.17.8.100/images/movie/logo/drgjyc.jpg","myCommentContent":"很棒"}]
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
         * address : 十八里店西直河商业中心东融国际影城
         * cinemaId : 21
         * cinemaName : 东融国际影城西直河店
         * commentTime : 1575337099000
         * distance : 12251542
         * logo : http://172.17.8.100/images/movie/logo/drgjyc.jpg
         * myCommentContent : 很棒
         */

        private String address;
        private String cinemaId;
        private String cinemaName;
        private long commentTime;
        private String distance;
        private String logo;
        private String myCommentContent;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(String cinemaId) {
            this.cinemaId = cinemaId;
        }

        public String getCinemaName() {
            return cinemaName;
        }

        public void setCinemaName(String cinemaName) {
            this.cinemaName = cinemaName;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getMyCommentContent() {
            return myCommentContent;
        }

        public void setMyCommentContent(String myCommentContent) {
            this.myCommentContent = myCommentContent;
        }
    }
}
