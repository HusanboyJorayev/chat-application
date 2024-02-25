package com.example.javaprojectspring_boot.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByIdAndDeletedAtIsNull(Long id);

    @Query("""
            select c from Contact as c
            """)
    List<Contact> getAllContacts();

}
