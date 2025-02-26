package com.example.mxh.service.user;

import com.example.mxh.config.jwt.JwtProvider;
import com.example.mxh.exception.UserException;
import com.example.mxh.form.FormCreateUser;
import com.example.mxh.form.FormUpdateUser;
import com.example.mxh.map.UserMapper;
import com.example.mxh.model.user.User;
import com.example.mxh.model.user.UserDto;
import com.example.mxh.repository.UserRepository;
import com.example.mxh.response.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AuthResponse create(FormCreateUser form) throws ParseException, Exception {
        var user = userRepository.findByUsername(form.getUsername());
        if (user != null){
            return null;
        }
        User newUser = UserMapper.map(form);
        newUser.setPassword(passwordEncoder.encode(form.getPassword()));
        var saveUser = userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(saveUser.getUsername(),saveUser.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse(token,"success");
        return response;
    }

    @Override
    public User findById(int id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return userRepository.findById(id).get();

        }
        throw new UserException("not found user by id: "+id);
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserMapper::map);
    }

    @Override
    public User update(int id,User newUser) throws UserException, ParseException {
        var user = userRepository.findById(id).get();
        if(user == null){
            throw  new UserException("not found user");
        }
        return userRepository.save(newUser);
    }

    @Override
    public UserDto followUser(int id, int idFollower) throws UserException {
        User user1 = userRepository.findById(id).get();
        User user2 = userRepository.findById(idFollower).get();
        if(user1 == null || user2 == null){
            return null;
        }
        user2.getFollower().add(user1.getId());
        user1.getFollowing().add(user2.getId());
        userRepository.save(user1);
        userRepository.save(user2);
        return UserMapper.map(user1);
    }

    @Override
    public List<UserDto> searchUser(String keySearch) {
        return UserMapper.map(userRepository.searchUser(keySearch));
    }

    @Override
    public User findUserByJwt(String jwt) {
        String username = JwtProvider.getUsernameFromToken(jwt);
        User user = userRepository.findByUsername(username);
        return user;
    }
}
