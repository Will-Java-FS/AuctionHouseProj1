package com.revature.auction.services;

import com.revature.auction.models.Comment;
import com.revature.auction.repositories.CommentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceImpTest {
    private CommentServiceImp commentService;
    private CommentRepo mockCommentRepo;

    @BeforeEach
    void setUp() {
        mockCommentRepo = Mockito.mock(CommentRepo.class);
        commentService = new CommentServiceImp(mockCommentRepo);
    }

    @Test
    void testGetAllComments() {
        List<Comment> mockComments = new ArrayList<>();
        mockComments.add(new Comment());
        mockComments.add(new Comment());

        when(mockCommentRepo.findAll()).thenReturn(mockComments);

        List<Comment> result = commentService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(mockCommentRepo, times(1)).findAll();
    }

    @Test
    void testFindByIdExists() {
        int commentId = 1;
        Comment mockComment = new Comment();

        when(mockCommentRepo.findById(commentId)).thenReturn(Optional.of(mockComment));

        Comment result = commentService.findById(commentId);

        assertNotNull(result);
        assertEquals(mockComment, result);
        verify(mockCommentRepo, times(1)).findById(commentId);
    }

    @Test
    void testFindByIdDoesNotExist() {
        int commentId = 2;

        when(mockCommentRepo.findById(commentId)).thenReturn(Optional.empty());

        Comment result = commentService.findById(commentId);

        assertNull(result);
        verify(mockCommentRepo, times(1)).findById(commentId);
    }

    @Test
    void testUpdateExists() {
        int commentId = 1;
        Comment existingComment = new Comment();
        Comment updatedComment = new Comment();

        when(mockCommentRepo.findById(commentId)).thenReturn(Optional.of(existingComment));
        when(mockCommentRepo.save(updatedComment)).thenReturn(updatedComment);

        Comment result = commentService.update(commentId, updatedComment);

        assertNotNull(result);
        assertEquals(updatedComment, result);
        verify(mockCommentRepo, times(1)).findById(commentId);
        verify(mockCommentRepo, times(1)).save(updatedComment);
    }

    @Test
    void testUpdateDoesNotExist() {
        int commentId = 1;
        Comment updatedComment = new Comment();

        when(mockCommentRepo.findById(commentId)).thenReturn(Optional.empty());

        Comment result = commentService.update(commentId, updatedComment);

        assertNull(result);
        verify(mockCommentRepo, times(1)).findById(commentId);
        verify(mockCommentRepo, never()).save(any(Comment.class)); // Ensure save was never called
    }

    @Test
    void testAddComment() {
        Comment newComment = new Comment();

        when(mockCommentRepo.save(newComment)).thenReturn(newComment);

        Comment result = commentService.add(newComment);

        assertNotNull(result);
        assertEquals(newComment, result);
        verify(mockCommentRepo, times(1)).save(newComment);
    }

    @Test
    void testDeleteExists() {
        int commentId = 1;
        Comment mockComment = new Comment();

        when(mockCommentRepo.findById(commentId)).thenReturn(Optional.of(mockComment));

        int result = commentService.delete(commentId);

        assertEquals(1, result);
        verify(mockCommentRepo, times(1)).deleteById(commentId);
    }

    @Test
    void testDeleteDoesNotExist() {
        int commentId = 2;

        when(mockCommentRepo.findById(commentId)).thenReturn(Optional.empty());

        int result = commentService.delete(commentId);

        assertEquals(0, result);
        verify(mockCommentRepo, never()).deleteById(commentId); // Ensure delete was never called
    }
}
