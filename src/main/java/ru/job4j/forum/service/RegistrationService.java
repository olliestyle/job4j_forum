package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.UserRepository;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public RegistrationService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }


    public Authority findUserByAuthority(String role) {
        return authorityRepository.findByAuthority(role);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }
}
