package com.revature.auction.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class CommentTest {
    private Comment comment;
    private User mockUser;
    private Item mockItem;

    @BeforeEach
    void setUp() {
        // Initialize mock objects
        mockUser = mock(User.class);
        mockItem = mock(Item.class);
        // Initialize Comment with mock objects
        comment = Comment.builder()
                .commenttime(new Timestamp(System.currentTimeMillis()))
                .user(mockUser)
                .item(mockItem)
                .build();
    }

    @Test
    void testCommentCreation() {
        assertNotNull(comment);
        assertNotNull(comment.getCommenttime());
        assertEquals(mockUser, comment.getUser());
        assertEquals(mockItem, comment.getItem());
    }

    @Test
    void testCommentEqualitySameObject() {
        assertEquals(comment, comment);
    }

    @Test
    void testCommentEqualityDifferentObjectSameValues() {
        Comment anotherComment = Comment.builder()
                .commenttime(comment.getCommenttime())
                .user(comment.getUser())
                .item(comment.getItem())
                .build();
        assertEquals(comment, anotherComment);
    }

    @Test
    void testCommentEqualityDifferentObjectDifferentValues() {
        Comment differentComment = Comment.builder()
                .commenttime(new Timestamp(System.currentTimeMillis())) // different timestamp
                .user(mockUser)
                .item(mockItem)
                .build();
        assertNotEquals(comment, differentComment);
    }

    @Test
    void testCommentHashCodeSameValues() {
        Comment anotherComment = Comment.builder()
                .commenttime(comment.getCommenttime())
                .user(comment.getUser())
                .item(comment.getItem())
                .build();
        assertEquals(comment.hashCode(), anotherComment.hashCode());
    }

    @Test
    void testCommentHashCodeDifferentValues() {
        Comment differentComment = Comment.builder()
                .commenttime(new Timestamp(System.currentTimeMillis())) // different timestamp
                .user(mockUser)
                .item(mockItem)
                .build();
        assertNotEquals(comment.hashCode(), differentComment.hashCode());
    }

    @Test
    void testCommentCreationWithNullUser() {
        Comment nullUserComment = Comment.builder()
                .commenttime(new Timestamp(System.currentTimeMillis()))
                .user(null) // Setting user to null
                .item(mockItem)
                .build();
        assertNotNull(nullUserComment);
        assertNull(nullUserComment.getUser());
        assertEquals(mockItem, nullUserComment.getItem());
    }

    @Test
    void testCommentCreationWithNullItem() {
        Comment nullItemComment = Comment.builder()
                .commenttime(new Timestamp(System.currentTimeMillis()))
                .user(mockUser)
                .item(null) // Setting item to null
                .build();
        assertNotNull(nullItemComment);
        assertNull(nullItemComment.getItem());
        assertEquals(mockUser, nullItemComment.getUser());
    }
}
