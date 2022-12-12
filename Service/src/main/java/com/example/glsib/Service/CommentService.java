package com.example.glsib.Service;

import com.example.glsib.Entite.Comment;
import com.example.glsib.Entite.Service;
import com.example.glsib.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service
public class CommentService  {

    @Autowired
    CommentRepository commentRepository;

    public Comment addComment(Comment s1){
        Comment savedComment = commentRepository.save(s1);
        return savedComment;
    }
    public Comment deleteComment(Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            return comment.get();
        }else
        {
            return null;
        }
    }
    public List<Comment> getAllComment(){
        return  commentRepository.findAll();

    }

}
