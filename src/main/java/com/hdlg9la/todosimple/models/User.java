package com.hdlg9la.todosimple.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         @Column(name = "id", unique = true)
         private Long id;

         @Column(name = "username", length = 100, nullable = false, unique = true)
         @NotNull
         @NotEmpty
         @Size(min = 2, max = 50)
         private String username;

         @Column(name = "username", length = 60, nullable = false)
         @NotNull
         @NotEmpty
         @Size(min = 4, max = 8)
         private String password;

}
// Paramos no minuto 18