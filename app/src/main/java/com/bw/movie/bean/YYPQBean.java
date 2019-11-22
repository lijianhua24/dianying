package com.bw.movie.bean;

import java.util.List;

public class YYPQBean {
    /**
     * result : [{"director":"黄渤","imageUrl":"http://172.17.8.100/images/movie/stills/ychx/ychx1.jpg","movieId":13,"name":"一出好戏","score":9.6,"starring":"黄渤,舒淇,王宝强,张艺兴","trailerUrl":"http://172.17.8.100/video/movie/ychx/ychx1.ts"},{"director":"韩延","imageUrl":"http://172.17.8.100/images/movie/stills/dwsj/dwsj1.jpg","movieId":10,"name":"动物世界","score":9.5,"starring":"李易峰,迈克尔·道格拉斯,周冬雨","trailerUrl":"http://172.17.8.100/video/movie/dwsj/dwsj1.ts"},{"director":"林德禄","imageUrl":"http://172.17.8.100/images/movie/stills/ftfb3/ftfb(3)1.jpg","movieId":17,"name":"反贪风暴3","score":9.1,"starring":"古天乐,张智霖,郑嘉颖,邓丽欣,谢天华","trailerUrl":"http://172.17.8.100/video/movie/ftfb3/ftfb(3)1.mp4"},{"director":"是枝裕和","imageUrl":"http://172.17.8.100/images/movie/stills/xtjz/xtjz1.jpg","movieId":9,"name":"小偷家族","score":9.4,"starring":"中川雅也,安藤樱,松冈茉优,","trailerUrl":"http://172.17.8.100/video/movie/xtjz/xtjz1.ts"},{"director":"曾国祥","imageUrl":"http://172.17.8.100/images/movie/stills/sndn/sndn1.jpg","movieId":22,"name":"少年的你","score":9.5,"starring":"周冬雨,易烊千玺,张耀,周也,尹昉","trailerUrl":"http://172.17.8.100/video/movie/sndn/sndn1.mp4"},{"director":"乔·德特杜巴","imageUrl":"http://172.17.8.100/images/movie/stills/jcs/jcs1.jpg","movieId":11,"name":"巨齿鲨","score":9.6,"starring":"杰森·斯坦森,李冰冰,雷恩·威尔森","trailerUrl":"http://172.17.8.100/video/movie/jcs/jcs1.ts"},{"director":"吕乐","imageUrl":"http://172.17.8.100/images/movie/stills/zdn/zdn1.jpg","movieId":21,"name":"找到你","score":8.5,"starring":"姚晨,马伊琍,袁文康,吴昊宸","trailerUrl":"http://172.17.8.100/video/movie/zdn/zdn1.mp4"},{"director":"庄文强","imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws1.jpg","movieId":20,"name":"无双","score":8.6,"starring":"周润发,郭富城,张静初,冯文娟,廖启智","trailerUrl":"http://172.17.8.100/video/movie/ws/ws1.mp4"},{"director":"贾樟柯","imageUrl":"http://172.17.8.100/images/movie/stills/jhen/jhen1.jpg","movieId":19,"name":"江湖儿女","score":9.7,"starring":"赵涛,廖凡,徐峥,梁嘉艳","trailerUrl":"http://172.17.8.100/video/movie/jhen/jhen1.mp4"},{"director":"韦正","imageUrl":"http://172.17.8.100/images/movie/stills/aqgy/aqgy1.jpg","movieId":15,"name":"爱情公寓","score":9.2,"starring":"陈赫,娄艺潇,邓家佳,孙艺洲","trailerUrl":"http://172.17.8.100/video/movie/aqgy/aqgy1.ts"}]
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
         * director : 黄渤
         * imageUrl : http://172.17.8.100/images/movie/stills/ychx/ychx1.jpg
         * movieId : 13
         * name : 一出好戏
         * score : 9.6
         * starring : 黄渤,舒淇,王宝强,张艺兴
         * trailerUrl : http://172.17.8.100/video/movie/ychx/ychx1.ts
         */

        private String director;
        private String imageUrl;
        private String movieId;
        private String name;
        private double score;
        private String starring;
        private String trailerUrl;

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getStarring() {
            return starring;
        }

        public void setStarring(String starring) {
            this.starring = starring;
        }

        public String getTrailerUrl() {
            return trailerUrl;
        }

        public void setTrailerUrl(String trailerUrl) {
            this.trailerUrl = trailerUrl;
        }
    }
}
