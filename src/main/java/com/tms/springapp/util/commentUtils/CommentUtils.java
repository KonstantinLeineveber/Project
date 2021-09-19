package com.tms.springapp.util.commentUtils;

import com.tms.springapp.model.comment.Comment;
import com.tms.springapp.service.IService;
import org.springframework.stereotype.Component;

@Component(value = "commentUtils")
public class CommentUtils {
    private final IService<Comment> commentIService;

    public CommentUtils(
            IService<Comment> commentIService) {
        this.commentIService = commentIService;
    }

}
