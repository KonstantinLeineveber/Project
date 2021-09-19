//package com.tms.springapp.util.userUtils;
//
////import com.tms.springapp.model.comment.Comment;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
////import com.tms.springapp.util.commentUtils.CommentUtils;
//
//@Component(value = "userUtils")
//public class UserUtils {
////    private final CommentUtils commentUtils;
////
////    public UserUtils(CommentUtils commentUtils) {
////        this.commentUtils = commentUtils;
////    }
//
//
////    public void editComment(List<Comment> comments, Comment comment) {
////        comments.forEach(o -> {
////            if (o.getId() == comment.getId()) {
////                o.setState(comment.getState());
////            }
////        });
////    }
//
////
////    public Comment findComment(List<Comment> comments, long id) {
////        return comments.stream().filter(comment -> comment.getId() == id)
////                .findFirst()
////                .orElse(null);
////    }
////
////
////    public void addComment(List<Comment> comments, Comment comment) {
////        comments.add(comment);
////    }
////
////
////    public void deleteComment(List<Comment> comments, long id) {
////        comments.removeIf(comment -> comment.getId() == id);
////    }
////
////}
