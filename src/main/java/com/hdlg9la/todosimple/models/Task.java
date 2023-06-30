package com.hdlg9la.todosimple.models;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

// import org.hibernate.mapping.ManyToOne;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = Task.TABLE_NAME)
// Anotacoes do lomboque
@AllArgsConstructor // Adiciona construtor com tos atributos
@NoArgsConstructor // construtor vazio
@Getter // adiciona todos getters
@Setter // todos setters
@EqualsAndHashCode(callSuper = true) // Adiciona os metodos equals e hascode
public class Task extends RepresentationModel<Task> implements Serializable {

         public static final String TABLE_NAME = "task";

         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         @Column(name = "id", unique = true)
         private Long id;

         @jakarta.persistence.ManyToOne
         @JoinColumn(name = "user_id", nullable = false, updatable = false)
         private User user;

         @Column(name = "description", length = 255, nullable = false)
         @NotNull()
         @NotEmpty()
         @Size(min = 2, max = 250)
         private String description;

        

}
