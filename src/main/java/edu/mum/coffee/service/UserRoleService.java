package edu.mum.coffee.service;

import edu.mum.coffee.domain.User;
import edu.mum.coffee.domain.UserRole;
import edu.mum.coffee.repository.RolesRepository;
import edu.mum.coffee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    @Autowired
    RolesRepository userRolesRepository;

    public UserRole saveUser(UserRole userRole){
        return userRolesRepository.save(userRole);
    }
}
