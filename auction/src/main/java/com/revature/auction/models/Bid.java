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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid_id;

    @Getter
    @Setter
    private Timestamp bidtime;

    @Getter
    @Setter
    private int amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_bidder")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_bidfor")
    private Item item;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return getBid_id() == bid.getBid_id() && Objects.equals(getBidtime(), bid.getBidtime()) && Objects.equals(getUser(), bid.getUser()) && Objects.equals(getItem(), bid.getItem());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getBid_id(), getBidtime(), getUser(), getItem());
    }
}
