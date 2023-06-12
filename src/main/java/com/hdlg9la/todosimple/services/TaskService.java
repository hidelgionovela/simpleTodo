package com.hdlg9la.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdlg9la.todosimple.models.Task;
import com.hdlg9la.todosimple.models.User;
import com.hdlg9la.todosimple.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

         @Autowired
         private UserService userService;

         @Autowired
         private TaskRepository taskRepository;

         public Task findById(Long id) {

                  Optional<Task> task = this.taskRepository.findById(id);

                  return task.orElseThrow(() -> new RuntimeException(
                                    "Tarefa nao encontrada! id: " + id + ", Tipo: " + Task.class.getName()));
         }

         public List<Task> findAllByUser_Id(Long user_id) {

                  List<Task> tasks = this.taskRepository.findByUser_Id(user_id);

                  return tasks;
         }

         @Transactional
         public Task create(Task task) {
                  User user = this.userService.findById(task.getUser().getId());
                  task.setId(null);
                  task.setUser(user);
                  task = this.taskRepository.save(task);
                  return task;

         }

         @Transactional
         public Task update(Task task) {

                  Task newTask = findById(task.getId());
                  newTask.setDescription(task.getDescription());
                  return this.taskRepository.save(newTask);

         }

         // @Transactional
         public void delete(Long id) {

                  findById(id);

                  try {
                           this.taskRepository.deleteById(id);
                  } catch (Exception e) {

                           throw new RuntimeException("Nao é possivel excluir pois ha entidades relacionadas");
                  }

         }

}
