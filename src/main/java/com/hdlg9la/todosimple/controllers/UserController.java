package com.hdlg9la.todosimple.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hdlg9la.todosimple.models.User;
import com.hdlg9la.todosimple.models.User.CreateUser;
import com.hdlg9la.todosimple.models.User.UpdateUser;
import com.hdlg9la.todosimple.services.UserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

        @Autowired
        private UserService userService;

        @GetMapping("/{id}")
        public ResponseEntity<User> findUserById(@PathVariable Long id) {

                User user = this.userService.findById(id);
                return ResponseEntity.ok().body(user);

        }

        @GetMapping
        public ResponseEntity<List<User>> findAllUsers() {

                List<User> users = this.userService.getAllUsers();
                if (!users.isEmpty()) {
                        for (var user : users) {
                            Long id = user.getId();
                            user.add(linkTo(methodOn(UserController.class).findUserById(id)).withRel("Detalhes do User"));
                        }
                }

                return ResponseEntity.ok().body(users);



        }

        @PostMapping
        @Validated(CreateUser.class)
        public ResponseEntity<Void> create(@Valid @RequestBody User user) {
                this.userService.create(user);
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
                                .toUri();
                return ResponseEntity.created(uri).build();
        }

        @PutMapping("/{id}")
        @Validated(UpdateUser.class)
        public ResponseEntity<Void> update(@Valid @RequestBody User user, @PathVariable Long id) {
                user.setId(id);
                this.userService.update(user);
                return ResponseEntity.noContent().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
                this.userService.delete(id);
                return ResponseEntity.noContent().build();

        }

}
