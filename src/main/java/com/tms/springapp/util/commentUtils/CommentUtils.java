package com.tms.springapp.util.commentUtils;

import com.tms.springapp.model.comment.Comment;
import org.springframework.stereotype.Component;

@Component(value = "commentUtils")
public class CommentUtils {

    public void addComents(Comment comment, String commentary) {
        comment.getComments().add(commentary);
    }
}
