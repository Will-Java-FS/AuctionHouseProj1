package com.revature.auction.repositories;

import com.revature.auction.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Integer, Comment>
{

}
