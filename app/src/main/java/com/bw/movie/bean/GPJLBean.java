package com.bw.movie.bean;

import java.util.List;

public class GPJLBean {
    /**
     * result : [{"amount":1,"createTime":1575012208000,"id":4189,"imageUrl":"http://172.17.8.100/images/movie/stills/zgjz/zgjz1.jpg","movieName":"中国机长","orderId":"20191129152328761","price":0.01},{"amount":1,"createTime":1575024097000,"id":4383,"imageUrl":"http://172.17.8.100/images/movie/stills/sndn/sndn1.jpg","movieName":"少年的你","orderId":"20191129184137353","price":0.01},{"amount":1,"createTime":1575032340000,"id":5039,"imageUrl":"http://172.17.8.100/images/movie/stills/dzd6qmwj/dzd6qmwj1.jpg","movieName":"碟中谍6：全面瓦解","orderId":"20191129205900923","price":0.2}]
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
         * amount : 1
         * createTime : 1575012208000
         * id : 4189
         * imageUrl : http://172.17.8.100/images/movie/stills/zgjz/zgjz1.jpg
         * movieName : 中国机长
         * orderId : 20191129152328761
         * price : 0.01
         */

        private String amount;
        private long createTime;
        private String id;
        private String imageUrl;
        private String movieName;
        private String orderId;
        private double price;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
