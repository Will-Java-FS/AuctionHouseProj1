package com.revature.auction.repositories;

import com.revature.auction.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository< User, Integer> {

//    List<User> findByUsername(String un);
//
//    @Query("Select Items.* from Users Left Join Items On Users.user_id = Items.user_owner Where Users.user_id = ?1")
//    List<Item> findItemsByUserId(int id);
//
//    @Query("Select Bid.* from Users Left Join Bids On Users.user_id = Bids.user_bidder Where Users.user_id = ?1")
//    List<Bid> findBidsByUserId(int id);
//
//    @Query("Select Comments.* from Users Left Join Comments On Users.user_id = Comments.user_commenter Where Users.user_id = ?1")
//    List<Comment> findCommentsByUserId(int id);
}


/*Bids
@Query("Select Users.* from Users Right Join Bids On Users.user_id = Bids.user_bidder Where Bids.bid_id = ?1")
User findBidderByBidId(int id);


//Comments
@Query("Select Users.* from Users Right Join Comments On Users.user_id = Comments.user_commenter Where Comments.comment_id = ?1")
    User findCommenterByCommentId(int id);
 */