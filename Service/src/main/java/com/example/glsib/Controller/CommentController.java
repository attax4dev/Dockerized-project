package com.example.glsib.Controller;


import com.example.glsib.Entite.Comment;
import com.example.glsib.Entite.Service;
import com.example.glsib.Service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RequestMapping("/api/comment")
@RestController
public class CommentController {

    @Autowired
     CommentService commentService;


    @GetMapping("/list-Comment")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Comment> ListComment() {return commentService.getAllComment();}

    @DeleteMapping("/delete-comment/{idcomment}")
    public void deletecomment(@PathVariable("idLevel") Long idcomment) {
        commentService.deleteComment(idcomment);
    }

    @PostMapping("/add-comment")
    public Comment addcomment(@RequestBody @Validated Comment c1) {
        return commentService.addComment(c1);
    }

}
