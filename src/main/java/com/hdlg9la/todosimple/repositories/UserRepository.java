package com.hdlg9la.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.hdlg9la.todosimple.models.User;
// import java.util.List;

// @Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
