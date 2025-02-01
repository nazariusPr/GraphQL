package com.example.demo.repository;

import com.example.demo.model.Event;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
  @Query("SELECT e FROM Event e WHERE e.title = :title")
  Optional<Event> findByTitle(String title);
}
