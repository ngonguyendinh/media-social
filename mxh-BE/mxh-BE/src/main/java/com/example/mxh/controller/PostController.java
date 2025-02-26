package com.example.mxh.controller;

import com.example.mxh.form.FormCreatePost;
import com.example.mxh.map.PostMapper;
import com.example.mxh.model.post.Post;
import com.example.mxh.model.post.PostDto;
import com.example.mxh.model.user.User;
import com.example.mxh.service.post.IPostService;
import com.example.mxh.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private IPostService postService;
    private IUserService iUserService;
    @PostMapping("/user")
    public ResponseEntity<PostDto> createPost(@RequestBody FormCreatePost form,@RequestHeader("Authorization") String jwt){
        User user = iUserService.findUserByJwt(jwt);
        Post post = postService.createPost(form, user.getId());
        return new ResponseEntity<>(PostMapper.map(post), HttpStatus.CREATED);
    }
    @DeleteMapping("/{postsId}")
    public ResponseEntity<String> deletePost(@PathVariable("postsId")int postId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = iUserService.findUserByJwt(jwt);
        postService.deletePost(postId, user.getId());
        return new ResponseEntity<>("the post is deleted successfull",HttpStatus.OK);
    }
    @GetMapping("/{Pid}")
    public ResponseEntity<PostDto> findByIdHandler(@PathVariable("Pid") int idPost){

        return  new ResponseEntity<>(PostMapper.map(postService.findById(idPost)),HttpStatus.ACCEPTED);
    }
    @GetMapping("/user/{Uid}")
    public ResponseEntity<List<PostDto>> findUsersPost(@PathVariable("Uid")int idUser){
        return new ResponseEntity<>(postService.findByUserId(idUser),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> findAll( ){
        return new ResponseEntity<>(postService.findAll(),HttpStatus.OK);
    }
    @PutMapping("/save/{Pid}")
    public ResponseEntity<PostDto> savePostHandler(@PathVariable("Pid") int postId,@RequestHeader("Authorization") String jwt) throws Exception{
        User user = iUserService.findUserByJwt(jwt);
        return new ResponseEntity<>(postService.savePost(postId, user.getId()),HttpStatus.ACCEPTED);
    }
    @PutMapping("/like/{Pid}")
    public ResponseEntity<PostDto> likePostHandler(@PathVariable("Pid") int postId,@RequestHeader("Authorization") String jwt){
        User user = iUserService.findUserByJwt(jwt);
        return new ResponseEntity<>(PostMapper.map(postService.likePost(postId, user.getId())), HttpStatus.ACCEPTED);
    }
}
