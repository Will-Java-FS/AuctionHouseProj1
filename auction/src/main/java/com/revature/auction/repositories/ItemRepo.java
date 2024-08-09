package com.revature.repositories;

import com.revature.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository< Item, Integer> {

    List<Item> findByItemName(String in);

    @Query("Select Users.* from Users Right Join Items On Users.user_id = Items.user_owner Where Items.item_id = ?1")
    User findOwnerByItemId(int id);

    @Query("Select Bid.* from Items Left Join Bids On Items.item_id = Bids.user_bidOn Where Items.item_id = ?1")
    List<Bid> findBidsByItemId(int id);

    @Query("Select Comments.* from Items Left Join Comments On Items.item_id = Comments.item_commentOn Where Item.item_id = ?1")
    List<Comment> findCommentsByItemId(int id);
}

/*
//Bids
    @Query("Select Items.* from Items Right Join Bids On Items.item_id = Bids.item_bidOn Where Bids.bid_id = ?1")
    Bid findItemByBidId(int id);

    //Comments
@Query("Select Items.* from Items Right Join Comments On Items.item_id = Comments.item_commentOn Where Comments.comment_id = ?1")
    Item findItemByCommentId(int id);
 */