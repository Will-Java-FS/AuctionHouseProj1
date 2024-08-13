package com.revature.auction.repositories;

import com.revature.auction.models.Comment;
import com.revature.auction.models.Item;
import com.revature.auction.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>
{
    @Query(value = "Select Items.* from Items Right Join Comments On Items.item_id = Comments.item_commentOn Where Comments.comment_id = ?1", nativeQuery = true)
    Item findItemByCommentId(int id);

    @Query(value = "Select Users.* from Users Right Join Comments On Users.user_id = Comments.user_commenter Where Comments.comment_id = ?1", nativeQuery = true)
    User findCommenterByCommentId(int id);
}
