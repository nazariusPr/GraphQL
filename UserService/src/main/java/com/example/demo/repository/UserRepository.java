package com.example.demo.repository;

import com.example.demo.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  @Query("SELECT u FROM User u WHERE u.email = :email")
  Optional<User> findByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.username = :username")
  Optional<User> findByUsername(String username);
}
