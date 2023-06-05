package com.hdlg9la.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdlg9la.todosimple.models.User;
import com.hdlg9la.todosimple.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

         @Autowired
         private UserRepository userRepository;



         public User findById(Long id){
                  // O optional é uma classe do java util que impede o lancamento de 
                  // da excessao nullpointer exception em caso de se retornal null entao caso 
                  // nao se encontre os dados retorna vazio.  
                  Optional<User> user = this.userRepository.findById(id) ;

                  return user.orElseThrow(() -> new RuntimeException(
                           "Usuario nao encontrado! id: " +id+ ", Tipo: " + User.class.getName() ));
         }

         @Transactional
         public User create (User user){
                  // recomendado usar o trasational em funcoes que alteram algo na base de dados
                  user.setId(null);
                  user = this.userRepository.save(user);
                  return user;

         }

         @Transactional
         public User update (User user){
                  
                  User newUser = findById(user.getId());
                  newUser.setPassword(user.getPassword());
                  return this.userRepository.save(newUser);

         }

         // @Transactional
         public void delete (Long id){
                  
                  findById(id);

                  try {
                           this.userRepository.deleteById(id);
                  } catch (Exception e) {
            
                           throw new RuntimeException("Nao é possivell excluir pois ha entidades relacionadas");
                  }

         }

         

         

         
}
