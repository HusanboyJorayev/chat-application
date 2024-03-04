package com.example.javaprojectspring_boot.chat;

import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query(
            nativeQuery = true,
            value = "select * from chat as c where c.get_phone=:p or c.send_phone=:p"
    )
    List<Chat> getAllUsersByChattingByOneUser(@Param(value = "p") String phone);

    Optional<Chat> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select c from Chat as c
            """)
    List<Chat> getAllChats();


}
