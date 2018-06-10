package edu.mum.coffee.service;

import edu.mum.coffee.domain.User;
import edu.mum.coffee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> findByUsername(String userName){
        return userRepository.findByUsername(userName);
    }

}
