package com.revature.auction.controllers;

import com.revature.auction.models.Comment;
import com.revature.auction.services.CommentService;
import com.revature.auction.services.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Returns all comments in the comment database
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments()
    {
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    //Returns the comment with the given ID and if no comment is found return not found
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") int id)
    {

        Comment comment = commentService.findById(id);

        if(comment != null)
        {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

    }

    //Post a comment to the comment database making sure that comment is not null
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment)
    {

        if(comment == null)
        {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(commentService.add(comment), HttpStatus.OK);

    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") int id, @RequestBody Comment comment)
    {
        Comment updatedComment = commentService.update(id);
        if(updatedComment != null)
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteCommentById(@PathVariable("id") int id)
    {
        commentService.delete(id);
        return HttpStatus.OK;
    }

}
