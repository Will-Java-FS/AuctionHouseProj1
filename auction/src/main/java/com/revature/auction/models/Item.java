package com.revature.auction.models;

import java.util.Objects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Items")
public class Item {
    @Getter
    @Id //makes this a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id",updatable = false)
    private int item_id;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "user_owner")
    private User owner;

    @Getter
    @Setter
    @Column(name = "itemName",nullable = false,unique = true)
    private String itemName;


    @Getter
    @Setter
    @Column(name = "itemDescription")
    private String itemDescription;

    @Getter
    @Setter
    @Column(name = "itemImage")
    private String itemImage;

    @Getter
    @Setter
    @Column(name = "isApproved")
    private boolean isApproved = false;


    public Item(User owner, String itemName) {
        this.owner = owner;
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", owner=" + owner +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getItem_id() == item.getItem_id() && Objects.equals(getOwner(), item.getOwner()) && Objects.equals(getItemName(), item.getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem_id(), getOwner(), getItemName());
    }
}