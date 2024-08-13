package com.revature.auction.repositories;

import com.revature.auction.models.Bid;
import com.revature.auction.models.User;
import com.revature.auction.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepo extends JpaRepository<Bid, Integer>
{
    @Query(value = "Select Items.* from Items Right Join Bids On Items.item_id = Bids.item_bidOn Where Bids.bid_id = ?1", nativeQuery = true)
    Item findItemByBidId(int id);

    @Query(value = "Select Users.* from Users Right Join Bids On Users.user_id = Bids.user_bidder Where Bids.bid_id = ?1",nativeQuery = true)
    User findBidderByBidId(int id);
}
