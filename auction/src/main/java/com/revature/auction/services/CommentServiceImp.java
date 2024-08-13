package com.revature.auction.services;

import com.revature.auction.models.Comment;
import com.revature.auction.repositories.BidRepo;
import com.revature.auction.repositories.CommentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CommentServiceImp implements CommentService, CrudService<Comment>
{

    public CommentRepo commentRepo;

    @Autowired
    public CommentServiceImp(CommentRepo commentRepo)
    {
        this.commentRepo = commentRepo;
    }


    @Override
    public List<Comment> getAll()
    {
        return List.of();
    }

    @Override
    public Comment findById(int id)
    {
        return null;
    }

    @Override
    public Comment update(int id, Comment object)
    {
        return null;
    }

    @Override
    public Comment add(Comment object)
    {
        return null;
    }

    @Override
    public int delete(int id)
    {
        return 0;
    }
}
