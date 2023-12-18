package com.example.bankapp.service.impl;

import com.example.bankapp.dtos.UserDto;
import com.example.bankapp.entities.RoleEntity;
import com.example.bankapp.entities.UserEntity;
import com.example.bankapp.entities.UserRoleEntity;
import com.example.bankapp.exception.NotFoundException;
import com.example.bankapp.repository.RoleRepository;
import com.example.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    public final UserRepository userRepository;
    public final RoleRepository roleRepository;
    public final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optUserEntity = userRepository.findByUsername(username);
        if (optUserEntity.isEmpty()) {
            throw new NotFoundException( "User not found");
        }
        UserEntity user = optUserEntity.get();
        return new User(user.getUsername(),
                user.getPassword(),
                user.getAuthorities());
    }
    public void createUser(UserDto userDto) {
        Optional<RoleEntity> roleFromDb = roleRepository.findByName(userDto.getRoleName());
        if (roleFromDb.isEmpty()) {
            throw new NotFoundException("Role not found, user cannot be created");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setRole(roleFromDb.get());
        UserEntity savedUser = userRepository.save(userEntity);
        log.info("User with id {} is created", savedUser.getId() );
    }
}
