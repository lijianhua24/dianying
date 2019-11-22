package com.bw.movie.bean;

import java.util.List;

public class GjsjcyyBean {

    /**
     * result : [{"address":"十八里店西直河商业中心东融国际影城","cinemaId":21,"logo":"http://172.17.8.100/images/movie/logo/drgjyc.jpg","name":"东融国际影城西直河店","price":0}]
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
         * logo : http://172.17.8.100/images/movie/logo/drgjyc.jpg
         * name : 东融国际影城西直河店
         * price : 0
         */

        private String address;
        private String cinemaId;
        private String logo;
        private String name;
        private String price;

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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
