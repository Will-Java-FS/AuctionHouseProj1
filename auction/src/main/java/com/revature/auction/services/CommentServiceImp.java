package com.revature.auction.services;

import com.revature.auction.models.Comment;
import com.revature.auction.models.Item;
import com.revature.auction.models.User;
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
        return commentRepo.findAll();
    }

    @Override
    public Comment findById(int id)
    {
        return commentRepo.findById(id).orElse(null);
    }

    @Override
    public Comment update(int id, Comment object)
    {
        Comment comment = commentRepo.findById(id).orElse(null);

        // valid id
        if(comment != null)
        {
            commentRepo.save(object);
            return object;
        }

        return null;
    }

    @Override
    public Comment add(Comment object)
    {
        return commentRepo.save(object);
    }

    @Override
    public int delete(int id)
    {
        Comment comment = commentRepo.findById(id).orElse(null);

        if(comment != null)
        {
            commentRepo.deleteById(id);
            return 1; // deletion occurred
        }

        return 0; // no such comment
    }

    public User getUser(int id)
    {
        return commentRepo.findCommenterByCommentId(id);
    }

    public Item getItem(int id)
    {
        return commentRepo.findItemByCommentId(id);
    }
}
