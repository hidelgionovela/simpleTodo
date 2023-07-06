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

import com.hdlg9la.todosimple.models.Task;
import com.hdlg9la.todosimple.services.TaskService;
// import com.hdlg9la.todosimple.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {

        @Autowired
        private TaskService taskService;

        // private UserService userService;

        @GetMapping("/{id}")
        public ResponseEntity<Task> findTaskById(@PathVariable Long id) {
                Task task = this.taskService.findById(id);
                return ResponseEntity.ok().body(task);

        }
        

        @GetMapping("/user/{user_id}")
        public ResponseEntity<List<Task>> findAllTasksByUser_Id(@PathVariable Long user_id) {
                // Todo: Verificacao da existencia do user e dar uma response caso nao exista
                // this.userService.findById(user_id);
                List<Task> task = this.taskService.findAllByUser_Id(user_id);
                return ResponseEntity.ok().body(task);

        }


        @PostMapping
        @Validated
        public ResponseEntity<Void> createTask(@Valid @RequestBody Task task) {
                this.taskService.create(task);
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId())
                                .toUri();
                return ResponseEntity.created(uri).build();
        }


        @PutMapping("/{id}")
        @Validated
        public ResponseEntity<Void> updateTask(@Valid @RequestBody Task task, @PathVariable Long id) {
                task.setId(id);
                this.taskService.update(task);
                return ResponseEntity.noContent().build();
        }


        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
                this.taskService.delete(id);
                return ResponseEntity.noContent().build();

        }

}
