package com.revature.auction.repositories;

import com.revature.auction.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepo extends JpaRepository<Bid, Integer>
{
    @Query("Select Items.* from Items Right Join Bids On Items.item_id = Bids.item_bidOn Where Bids.bid_id = ?1")
    Bid findItemByBidId(int id);

    @Query("Select Users.* from Users Right Join Bids On Users.user_id = Bids.user_bidder Where Bids.bid_id = ?1")
    User findBidderByBidId(int id);
}
