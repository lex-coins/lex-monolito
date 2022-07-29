package br.com.lexcoins.controller;

import br.com.lexcoins.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class UserController {

    @GetMapping
    public List<User> findAll() {
        return findAll();
    }
    @PostMapping
    public User saveUser(@RequestBody User user) {
        return saveUser(user);
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return findById(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                             @RequestBody User user) {
        return user;
    }
}
