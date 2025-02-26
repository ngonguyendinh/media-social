package com.example.mxh.model.user;

import com.example.mxh.model.post.Post;
import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", length = 100, nullable = false , unique = true)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "email", length = 255, nullable = false , unique = true)
    private String email;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "ngay_sinh", length = 150 , nullable = false)
    private LocalDate ngaySinh;

    @Column(name = "gioi_tinh", length = 3 , nullable = false)
    private String gioiTinh;

    private String avatar;
    private String backGround;
    @ManyToMany
    private List<Post> savePost = new ArrayList<>();


    @Column(name = "follower")
    private List<Integer> follower = new ArrayList<>();
    @Column(name = "following")
    private List<Integer> following = new ArrayList<>() ;

    @OneToMany(mappedBy = "user")
    List<Post> posts;

    public LocalDate setNgaySinh(String ngaySinh) throws ParseException {
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.ngaySinh = LocalDate.parse(ngaySinh,formatter);
        return this.ngaySinh;
    }
}
