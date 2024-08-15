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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    @Getter
    @Setter
    private Timestamp commenttime;

    @Getter
    @Setter
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_commenter")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_commenton")
    private Item item;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return getComment_id() == comment.getComment_id() && Objects.equals(getCommenttime(), comment.getCommenttime()) && Objects.equals(getUser(), comment.getUser()) && Objects.equals(getItem(), comment.getItem());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getComment_id(), getCommenttime(), getUser(), getItem());
    }



}
