package com.revature.auction.services;

import com.revature.auction.models.Comment;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CommentServiceImp implements CommentService, CrudService<Comment>
{

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
    public Comment update(int id, Comment comment)
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
