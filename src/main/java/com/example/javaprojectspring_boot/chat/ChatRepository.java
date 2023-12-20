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
    select c from Chat as c where c.senderId=:senderId and c.getterId=:getterId and c.getterId!=c.senderId and c.deletedAt is null 
""")
    List<Chat>AllByGetterIdAndSenderIdAndDeletedAtIsNull(Integer getterId, Integer senderId);
    //List<Chat>AllByGetterIdAndSenderIdAndDeletedAtIsNull(Integer getterId, Integer senderId);
    Optional<Chat> findBySenderIdAndAndDeletedAtIsNull(Integer senderId);

    Optional<Chat> findByGetterIdAndAndDeletedAtIsNull(Integer getterId);


    Optional<Chat>findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select c from Chat as c
            """)
    List<Chat> getAllChats();
}
