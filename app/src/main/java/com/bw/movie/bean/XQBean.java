package com.bw.movie.bean;

import java.util.List;

public class XQBean {


    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * commentNum : 38
         * duration : 117分钟
         * imageUrl : http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg
         * movieActor : [{"name":"徐峥","photo":"http://172.17.8.100/images/movie/actor/wbsys/xuzheng.jpg","role":"程勇"},{"name":"周一围","photo":"http://172.17.8.100/images/movie/actor/wbsys/zhouyiwei.jpg","role":"曹斌"},{"name":"王传君","photo":"http://172.17.8.100/images/movie/actor/wbsys/wangchuanjun.jpg","role":"吕受益"},{"name":"谭卓","photo":"http://172.17.8.100/images/movie/actor/wbsys/tanzhuo.jpg","role":"刘思惠"},{"name":"章宇","photo":"http://172.17.8.100/images/movie/actor/wbsys/zhangyu.jpg","role":"彭浩"},{"name":"杨新鸣","photo":"http://172.17.8.100/images/movie/actor/wbsys/yangxinming.jpg","role":"刘牧师"},{"name":"王砚辉","photo":"http://172.17.8.100/images/movie/actor/wbsys/wangyanhui.jpg","role":"张长林"}]
         * movieDirector : [{"name":"文牧野","photo":"http://172.17.8.100/images/movie/director/wbsys/1.jpg"}]
         * movieId : 1
         * movieType : 剧情 / 喜剧
         * name : 我不是药神
         * placeOrigin : 中国
         * posterList : ["http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys2.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys3.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys4.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys5.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys6.jpg"]
         * releaseTime : 1530720000000
         * score : 8.9
         * shortFilmList : [{"imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys3.jpg","videoUrl":"http://172.17.8.100/video/movie/wbsys/wbsysygp1.ts"},{"imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys2.jpg","videoUrl":"http://172.17.8.100/video/movie/wbsys/wbsysygp2.ts"},{"imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys4.jpg","videoUrl":"http://172.17.8.100/video/movie/wbsys/wbsysygp3.mp4"}]
         * summary : 一位不速之客的意外到访，打破了神油店老板程勇（徐峥 饰）的平凡人生，他从一个交不起房租的男性保健品商贩，一跃成为印度仿制药“格列宁”的独家代理商。收获巨额利润的他，生活剧烈变化，被病患们冠以“药神”的称号。但是，一场关于救赎的拉锯战也在波涛暗涌中慢慢展开......
         * whetherFollow : 2
         */

        private String commentNum;
        private String duration;
        private String imageUrl;
        private String movieId;
        private String movieType;
        private String name;
        private String placeOrigin;
        private long releaseTime;
        private double score;
        private String summary;
        private String whetherFollow;
        private List<MovieActorBean> movieActor;
        private List<MovieDirectorBean> movieDirector;
        private List<String> posterList;
        private List<ShortFilmListBean> shortFilmList;

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
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

        public String getMovieType() {
            return movieType;
        }

        public void setMovieType(String movieType) {
            this.movieType = movieType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceOrigin() {
            return placeOrigin;
        }

        public void setPlaceOrigin(String placeOrigin) {
            this.placeOrigin = placeOrigin;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getWhetherFollow() {
            return whetherFollow;
        }

        public void setWhetherFollow(String whetherFollow) {
            this.whetherFollow = whetherFollow;
        }

        public List<MovieActorBean> getMovieActor() {
            return movieActor;
        }

        public void setMovieActor(List<MovieActorBean> movieActor) {
            this.movieActor = movieActor;
        }

        public List<MovieDirectorBean> getMovieDirector() {
            return movieDirector;
        }

        public void setMovieDirector(List<MovieDirectorBean> movieDirector) {
            this.movieDirector = movieDirector;
        }

        public List<String> getPosterList() {
            return posterList;
        }

        public void setPosterList(List<String> posterList) {
            this.posterList = posterList;
        }

        public List<ShortFilmListBean> getShortFilmList() {
            return shortFilmList;
        }

        public void setShortFilmList(List<ShortFilmListBean> shortFilmList) {
            this.shortFilmList = shortFilmList;
        }

        public static class MovieActorBean {
            /**
             * name : 徐峥
             * photo : http://172.17.8.100/images/movie/actor/wbsys/xuzheng.jpg
             * role : 程勇
             */

            private String name;
            private String photo;
            private String role;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }

        public static class MovieDirectorBean {
            /**
             * name : 文牧野
             * photo : http://172.17.8.100/images/movie/director/wbsys/1.jpg
             */

            private String name;
            private String photo;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }

        public static class ShortFilmListBean {
            /**
             * imageUrl : http://172.17.8.100/images/movie/stills/wbsys/wbsys3.jpg
             * videoUrl : http://172.17.8.100/video/movie/wbsys/wbsysygp1.ts
             */

            private String imageUrl;
            private String videoUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }
        }
    }
}
