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
            value = "select * from chat as c where ((c.send_phone='+998941490908' and c.get_phone='+998') or (c.send_phone='+998' or c.get_phone='+998941490908'))\n" +
                    "                          and c.get_phone!=c.send_phone and c.deleted_at isnull"
    )
    List<Chat> getAllGetPhoneAndSendPhone(@Param(value = "g") String getPhone, @Param(value = "s") String sendPhone);

    Optional<Chat> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select c from Chat as c
            """)
    List<Chat> getAllChats();


    @Query("""
                  select  c from Chat as c where c.userId=:id
            """)
    List<Chat> getChatsWithId(Integer id);

}
