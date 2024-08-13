package com.revature.auction.controller;

import com.revature.auction.controllers.CommentController;
import com.revature.auction.models.Comment;
import com.revature.auction.services.CommentServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentControllerTest {

    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentServiceImp commentService;

    private Comment testComment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        //testComment = new Comment("Test comment", 1); // Mock comment for testing
    }

    @Test
    public void testGetAllComments() {
        List<Comment> comments = Arrays.asList(testComment);
        when(commentService.getAll()).thenReturn(comments);

        ResponseEntity<List<Comment>> response = commentController.getAllComments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comments.size(), response.getBody().size());
        assertEquals(comments, response.getBody());
    }

    @Test
    public void testGetCommentById_Found() {
        when(commentService.findById(1)).thenReturn(testComment);

        ResponseEntity<Comment> response = commentController.getCommentById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testComment, response.getBody());
    }

    @Test
    public void testGetCommentById_NotFound() {
        when(commentService.findById(99)).thenReturn(null);

        ResponseEntity<Comment> response = commentController.getCommentById(99);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testAddComment_Success() {
        when(commentService.add(testComment)).thenReturn(testComment);

        ResponseEntity<Comment> response = commentController.addComment(testComment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testComment, response.getBody());
    }

    @Test
    public void testAddComment_BadRequest() {
        ResponseEntity<Comment> response = commentController.addComment(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateComment_Success() {
        Comment updatedComment = null;
        when(commentService.update(1, updatedComment)).thenReturn(updatedComment);

        ResponseEntity<Comment> response = commentController.updateComment(1, updatedComment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedComment, response.getBody());
    }

    @Test
    public void testUpdateComment_BadRequest() {
        when(commentService.update(99, testComment)).thenReturn(null);

        ResponseEntity<Comment> response = commentController.updateComment(99, testComment);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteCommentById() {
        doNothing().when(commentService).delete(1);

        HttpStatus response = commentController.deleteCommentById(1);

        assertEquals(HttpStatus.OK, response);
        verify(commentService, times(1)).delete(1);
    }
}
