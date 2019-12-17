package com.bw.movie.bean;

import java.util.List;

public class WDDYPBean {
    /**
     * result : [{"amount":0,"beginTime":"22:00:00","cinemaName":"华谊兄弟影院","createTime":1575032340000,"endTime":"13:36:00","id":5039,"movieName":"碟中谍6：全面瓦解","price":0,"screeningHall":"9号厅","seat":"1-3","status":0,"userId":0},{"amount":0,"beginTime":"18:30:00","cinemaName":"魔影国际影城","createTime":1575024097000,"endTime":"19:55:00","id":4383,"movieName":"少年的你","price":0,"screeningHall":"5号厅","seat":"10-10","status":0,"userId":0},{"amount":0,"beginTime":"19:20:00","cinemaName":"青春光线电影院","createTime":1575012208000,"endTime":"21:18:00","id":4189,"movieName":"中国机长","price":0,"screeningHall":"3号厅","seat":"2-2","status":0,"userId":0}]
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
         * amount : 0
         * beginTime : 22:00:00
         * cinemaName : 华谊兄弟影院
         * createTime : 1575032340000
         * endTime : 13:36:00
         * id : 5039
         * movieName : 碟中谍6：全面瓦解
         * price : 0
         * screeningHall : 9号厅
         * seat : 1-3
         * status : 0
         * userId : 0
         */

        private String amount;
        private String beginTime;
        private String cinemaName;
        private long createTime;
        private String endTime;
        private String id;
        private String movieName;
        private String price;
        private String screeningHall;
        private String seat;
        private String status;
        private String userId;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getbeginTime() {
            return beginTime;
        }

        public void setbeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getCinemaName() {
            return cinemaName;
        }

        public void setCinemaName(String cinemaName) {
            this.cinemaName = cinemaName;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getScreeningHall() {
            return screeningHall;
        }

        public void setScreeningHall(String screeningHall) {
            this.screeningHall = screeningHall;
        }

        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
