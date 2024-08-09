package com.revature.auction.services;

public interface CommentService
{
    public List<Comment> getAll();
    public Comment findById(int id);
    public Comment update(int id);
    public Comment add(Comment object);
    public int delete(int id);
}
