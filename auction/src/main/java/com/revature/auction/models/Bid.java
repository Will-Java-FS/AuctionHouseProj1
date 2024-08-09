package com.revature.auction.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bids")
public class Bid
{
    @Id
    @Column(name="bid_id")
    private int bid_id;

    // FK
    private int user_bidder;

    // FK
    private int item_bidfor;

    @Getter
    @Setter
    private Timestamp bidtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return getBid_id() == bid.getBid_id() && getUser_bidder() == bid.getUser_bidder() && getItem_bidfor() == bid.getItem_bidfor() && Objects.equals(getBidtime(), bid.getBidtime()) && Objects.equals(getUser(), bid.getUser()) && Objects.equals(getItem(), bid.getItem());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getBid_id(), getUser_bidder(), getItem_bidfor(), getBidtime(), getUser(), getItem());
    }
}
