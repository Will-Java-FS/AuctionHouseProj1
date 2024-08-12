package com.revature.auction.repositories;

import com.revature.auction.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>
{
    @Query("Select Items.* from Items Right Join Comments On Items.item_id = Comments.item_commentOn Where Comments.comment_id = ?1")
    Item findItemByCommentId(int id);

    @Query("Select Users.* from Users Right Join Comments On Users.user_id = Comments.user_commenter Where Comments.comment_id = ?1")
    User findCommenterByCommentId(int id);
}
