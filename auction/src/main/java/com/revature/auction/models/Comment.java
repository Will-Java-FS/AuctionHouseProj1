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
@Table(name = "comments")
public class Comment
{
    @Id
    private int comment_id;

    // FK
    private int user_commenter;

    // FK
    private int item_commenton;

    @Getter
    @Setter
    private Timestamp commenttime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return getComment_id() == comment.getComment_id() && getUser_commenter() == comment.getUser_commenter() && getItem_commenton() == comment.getItem_commenton() && Objects.equals(getCommenttime(), comment.getCommenttime()) && Objects.equals(getUser(), comment.getUser()) && Objects.equals(getItem(), comment.getItem());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getComment_id(), getUser_commenter(), getItem_commenton(), getCommenttime(), getUser(), getItem());
    }
}
