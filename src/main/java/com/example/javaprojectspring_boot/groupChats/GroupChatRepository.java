package com.example.javaprojectspring_boot.groupChats;

import com.example.javaprojectspring_boot.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat, Integer> {

    Optional<GroupChat> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select c from GroupChat as c
            """)
    List<GroupChat> getAllGroupChat();
}
