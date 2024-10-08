package com.revature.auction.repositories;

import com.revature.auction.models.*;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepo extends JpaRepository< User, Integer> {

    User findByUsernameAndPassword(String un, String pw);
    User findByUsername(String username);

    @Query(value = "Select Items.* from Users Left Join Items On Users.user_id = Items.user_owner Where Users.user_id = ?1", nativeQuery = true)
    List<Item> findItemsByUserId(int id);

      @Query(value = "Select Bid.* from Users Left Join Bids On Users.user_id = Bids.user_bidder Where Users.user_id = ?1", nativeQuery = true)
      List<Bid> findBidsByUserId(int id);

    @Query(value = "Select Comments.* from Users Left Join Comments On Users.user_id = Comments.user_commenter Where Users.user_id = ?1", nativeQuery = true)
    List<Comment> findCommentsByUserId(int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET isadmin = :isAdmin WHERE user_id = :id", nativeQuery = true)
    int updateAdmin(@Param("id") int id, @Param("isAdmin") boolean isAdmin);

}


/*Bids
@Query("Select Users.* from Users Right Join Bids On Users.user_id = Bids.user_bidder Where Bids.bid_id = ?1")
User findBidderByBidId(int id);


//Comments
@Query("Select Users.* from Users Right Join Comments On Users.user_id = Comments.user_commenter Where Comments.comment_id = ?1")
    User findCommenterByCommentId(int id);
 */
