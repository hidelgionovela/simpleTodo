package com.hdlg9la.todosimple.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

// import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@AllArgsConstructor // Adiciona construtor com tos atributos
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter // adiciona todos getters
@Setter
public class User extends RepresentationModel<User> implements Serializable{

         public interface CreateUser {

         }

         public interface UpdateUser {

         }

         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         @Column(name = "id", unique = true)
         private Long id;

         @Column(name = "username", length = 100, nullable = false, unique = true)
         @NotNull(groups = { CreateUser.class, UpdateUser.class })
         @NotEmpty(groups = { CreateUser.class, UpdateUser.class })
         @Size(groups = CreateUser.class, min = 4, max = 50)
         private String username;

         @JsonProperty(access = Access.WRITE_ONLY)
         @Column(name = "password", length = 60, nullable = false)
         @NotNull(groups = { CreateUser.class, UpdateUser.class })
         @NotEmpty(groups = { CreateUser.class, UpdateUser.class })
         @Size(groups = { CreateUser.class, UpdateUser.class }, min = 4, max = 15)
         private String password;

         @OneToMany(mappedBy = "user")
         @JsonProperty(access = Access.WRITE_ONLY)
         private List<Task> tasks = new ArrayList<Task>();

         
         // Usando getters e setters  colocamos @jsonIgnore para nao trazer as tasks na api e usando lombok  @JsonProperty(access = Access.WRITE_ONLY)
         // @JsonIgnore
         // public List<Task> getTasks() {
         //          return tasks;
         // }

        
}