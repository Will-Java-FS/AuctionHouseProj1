package com.revature.auction.controllers;

import com.revature.auction.services.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController
{
    public CommentServiceImp commentService;

    @Autowired
    public CommentController(CommentServiceImp commentService)
    {
        this.commentService = commentService;
    }

}
