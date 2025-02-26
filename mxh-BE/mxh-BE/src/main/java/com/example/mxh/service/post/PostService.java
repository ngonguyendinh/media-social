package com.example.mxh.service.post;

import com.example.mxh.form.FormCreatePost;
import com.example.mxh.map.CommentMapper;
import com.example.mxh.map.PostMapper;
import com.example.mxh.model.post.Post;
import com.example.mxh.model.post.PostDto;
import com.example.mxh.model.user.User;
import com.example.mxh.repository.PostRepository;
import com.example.mxh.repository.UserRepository;
import com.example.mxh.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class PostService implements IPostService{
    private PostRepository postRepository;
    private UserRepository userRepository;
    private IUserService userService;
    @Override
    public Post createPost(FormCreatePost form, int idUser) {
        Post post = PostMapper.map(form);
        User user = userRepository.findById(idUser).get();
        post.setUser(user);

        return postRepository.save(post);
    }

    @Override
    public List<PostDto> findByUserId(int idUser) {
        List<PostDto> postDtos = new ArrayList<>();
        var user = userRepository.findById(idUser).get();
        var posts = postRepository.findByUser(user);
        for (Post post : posts) {
            postDtos.add(PostMapper.map(post));
        }
        return postDtos;
    }

    @Override
    public List<PostDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            var comment = post.getComments();
            var dto = PostMapper.map(post);
            dto.setComments(CommentMapper.map(comment));
            postDtos.add(dto);
        }
        return postDtos ;
    }

    @Override
    public void deletePost(int idPost, int idUser) throws Exception {
        var post = postRepository.findById(idPost).get();
        if (post.getUser().getId() != idUser){
            throw new Exception("error please try again");
        }
        postRepository.deleteById(idPost);
    }

    @Override
    public Post findById(int idPost) {
        return postRepository.findById(idPost).get();
    }

    @Override
    public PostDto savePost(int idPost, int idUser) {
        var post = postRepository.findById(idPost).get();
        var user = userRepository.findById(idUser).get();
        var dto = PostMapper.map(post);
        if(!user.getSavePost().contains(post)){
            user.getSavePost().add(post);
        }
        else user.getSavePost().remove(post);
        userRepository.save(user);
        return dto;
    }

    @Override
    public Post likePost(int idPost, int idUser) {
        var post = postRepository.findById(idPost).get();
        var user = userRepository.findById(idUser).get();
        if(post.getLike().contains(user)){
            post.getLike().remove(user);
        }
        else{ post.getLike().add(user);}
        return postRepository.save(post);
    }
}
