package com.bw.movie.bean;

import java.util.List;

public class YYPJBean {
    /**
     * result : [{"commentContent":"很棒","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-11-15/20191115194408.jpg","commentId":100,"commentTime":1573797666000,"commentUserId":13818,"commentUserName":"你的名字","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"很棒","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":94,"commentTime":1573480318000,"commentUserId":13771,"commentUserName":"tzy","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"咋的了","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-31/20191031165328.png","commentId":93,"commentTime":1572512635000,"commentUserId":13678,"commentUserName":"abnetming","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"耐思","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-11-15/20191115201537.jpg","commentId":90,"commentTime":1571986952000,"commentUserId":13715,"commentUserName":"张乐乐","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"就很棒","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-08-24/20190824094957.jpg","commentId":85,"commentTime":1571826049000,"commentUserId":13501,"commentUserName":"哥哥","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"�ܺ�","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-24/20191024191144.jpg","commentId":53,"commentTime":1570842026000,"commentUserId":13544,"commentUserName":"woailuo","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"实时热点排行榜--百度搜索风云榜","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-11-15/20191115204815.jpg","commentId":44,"commentTime":1570602371000,"commentUserId":13669,"commentUserName":"ddds","greatHeadPic":[],"greatNum":1,"hotComment":0,"isGreat":0},{"commentContent":"很棒","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-25/20191025160324.jpg","commentId":42,"commentTime":1570602020000,"commentUserId":13609,"commentUserName":"wanggangwang","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"很棒哦！！","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-25/20191025154714.jpg","commentId":37,"commentTime":1568891242000,"commentUserId":13596,"commentUserName":"欢喜就好","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"棒棒打","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-25/20191025152521.jpg","commentId":36,"commentTime":1568877381000,"commentUserId":13602,"commentUserName":"kongxiangwei","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0}]
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
         * commentContent : 很棒
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2019-11-15/20191115194408.jpg
         * commentId : 100
         * commentTime : 1573797666000
         * commentUserId : 13818
         * commentUserName : 你的名字
         * greatHeadPic : []
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private String commentId;
        private long commentTime;
        private String commentUserId;
        private String commentUserName;
        private String greatNum;
        private String hotComment;
        private String isGreat;
        private List<?> greatHeadPic;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(String commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public String getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(String greatNum) {
            this.greatNum = greatNum;
        }

        public String getHotComment() {
            return hotComment;
        }

        public void setHotComment(String hotComment) {
            this.hotComment = hotComment;
        }

        public String getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(String isGreat) {
            this.isGreat = isGreat;
        }

        public List<?> getGreatHeadPic() {
            return greatHeadPic;
        }

        public void setGreatHeadPic(List<?> greatHeadPic) {
            this.greatHeadPic = greatHeadPic;
        }
    }
}
