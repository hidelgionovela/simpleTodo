package com.hdlg9la.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hdlg9la.todosimple.models.Task;
import java.util.List;



@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

//  Todo:      Exemplo1 queremos buscar a lista de tarefas que temos na BD 
// usando as classes do jpa
// Aqui o nome da funcao nao pode ser mudado para ser interpretado pelo jpa
List<Task> findByUser_Id(Long id);

//  Todo:      Exemplo2 queremos buscar a lista de tarefas que temos na BD 
// usando as classes do jpql(que ee mais proximo ao sql puro)
// Aqui o nome da funcao pode ser mudadoo que importa ee a query
// @Query(value = "SELECT t FROM Task t WHERE t.user.id = :user_id")
// List<Task> findByUserId(@Param("user_id") Long user_id);

//  Todo:      Exemplo3 queremos buscar a lista de tarefas que temos na BD 
// usando o sql puro (nativo)
// Aqui o nome da funcao pode ser mudadoo que importa ee a query
// @Query( value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
// List<Task> findByUserId(@Param("id") Long id);
   
}
