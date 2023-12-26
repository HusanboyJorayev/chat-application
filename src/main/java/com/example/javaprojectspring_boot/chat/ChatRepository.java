package com.example.javaprojectspring_boot.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("""
select c from Chat as c where c.getPhone=:g and c.sendPhone=:s and c.getPhone!=c.sendPhone and c.deletedAt is null 
""")
    List<Chat>getAllGetPhoneAndSendPhone(@Param(value = "g") String getPhone,@Param(value = "s") String sendPhone);

    Optional<Chat>findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select c from Chat as c
            """)
    List<Chat> getAllChats();
}
