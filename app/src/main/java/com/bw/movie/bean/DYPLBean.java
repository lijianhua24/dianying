package com.bw.movie.bean;

import java.util.List;

public class DYPLBean {
    /**
     * result : [{"commentTime":1575336822000,"director":"刘阔","imageUrl":"http://172.17.8.100/images/movie/stills/fyz/fyz1.jpg","movieId":12,"movieName":"风语咒","movieScore":0,"myCommentContent":"电影好看","myCommentScore":5,"starring":"路知行,阎萌萌,褚珺"},{"commentTime":1574340149000,"director":"闫非","imageUrl":"http://172.17.8.100/images/movie/stills/xhssf/xhssf1.jpg","movieId":3,"movieName":"西虹市首富","movieScore":0,"myCommentContent":"好看","myCommentScore":5,"starring":"沈腾,宋芸烨,张晨光,艾伦,常远"},{"commentTime":1574340097000,"director":"徐克","imageUrl":"http://172.17.8.100/images/movie/stills/drjzsdtw/drjzsdtw1.jpg","movieId":4,"movieName":"狄仁杰之四大天王","movieScore":0,"myCommentContent":"好看","myCommentScore":5,"starring":"赵又廷,冯绍峰,林更新,刘嘉玲,阮经天,马思纯"},{"commentTime":1574339738000,"director":"李海龙","imageUrl":"http://172.17.8.100/images/movie/stills/jmyx/jmyx1.jpg","movieId":14,"movieName":"解码游戏","movieScore":0,"myCommentContent":"好看","myCommentScore":5,"starring":"韩庚,凤小岳,李媛,山下智久"},{"commentTime":1574339691000,"director":"克里斯托弗·麦奎里","imageUrl":"http://172.17.8.100/images/movie/stills/dzd6qmwj/dzd6qmwj1.jpg","movieId":16,"movieName":"碟中谍6：全面瓦解","movieScore":0,"myCommentContent":"还行","myCommentScore":4.5,"starring":"汤姆·克鲁斯,亨利·卡维尔,丽贝卡·弗格森,西蒙·佩吉"},{"commentTime":1574339606000,"director":"曾国祥","imageUrl":"http://172.17.8.100/images/movie/stills/sndn/sndn1.jpg","movieId":22,"movieName":"少年的你","movieScore":0,"myCommentContent":"真好看!!!","myCommentScore":5,"starring":"周冬雨,易烊千玺,张耀,周也,尹昉"},{"commentTime":1574339164000,"director":"\r\n刘伟强","imageUrl":"http://172.17.8.100/images/movie/stills/zgjz/zgjz1.jpg","movieId":24,"movieName":"中国机长","movieScore":0,"myCommentContent":"真好看","myCommentScore":5,"starring":"张涵予,欧豪,袁泉,张天爱,李沁"}]
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
         * commentTime : 1575336822000
         * director : 刘阔
         * imageUrl : http://172.17.8.100/images/movie/stills/fyz/fyz1.jpg
         * movieId : 12
         * movieName : 风语咒
         * movieScore : 0
         * myCommentContent : 电影好看
         * myCommentScore : 5
         * starring : 路知行,阎萌萌,褚珺
         */

        private long commentTime;
        private String director;
        private String imageUrl;
        private String movieId;
        private String movieName;
        private String movieScore;
        private String myCommentContent;
        private String myCommentScore;
        private String starring;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

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

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getMovieScore() {
            return movieScore;
        }

        public void setMovieScore(String movieScore) {
            this.movieScore = movieScore;
        }

        public String getMyCommentContent() {
            return myCommentContent;
        }

        public void setMyCommentContent(String myCommentContent) {
            this.myCommentContent = myCommentContent;
        }

        public String getMyCommentScore() {
            return myCommentScore;
        }

        public void setMyCommentScore(String myCommentScore) {
            this.myCommentScore = myCommentScore;
        }

        public String getStarring() {
            return starring;
        }

        public void setStarring(String starring) {
            this.starring = starring;
        }
    }
}
