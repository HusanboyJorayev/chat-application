package com.example.javaprojectspring_boot.contact;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-06T12:07:12+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class ContactMapperImpl extends ContactMapper {

    @Override
    public Contact toEntity(ContactDto dto) {
        if ( dto == null ) {
            return null;
        }

        Contact.ContactBuilder contact = Contact.builder();

        contact.phoneNumber( dto.getPhoneNumber() );
        contact.username( dto.getUsername() );
        contact.userId( dto.getUserId() );

        return contact.build();
    }

    @Override
    public ContactDto toDto(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactDto.ContactDtoBuilder contactDto = ContactDto.builder();

        contactDto.id( contact.getId() );
        contactDto.phoneNumber( contact.getPhoneNumber() );
        contactDto.username( contact.getUsername() );
        contactDto.userId( contact.getUserId() );
        contactDto.createdAt( contact.getCreatedAt() );
        contactDto.updatedAt( contact.getUpdatedAt() );
        contactDto.deletedAt( contact.getDeletedAt() );

        return contactDto.build();
    }

    @Override
    public void update(Contact contact, ContactDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            contact.setId( dto.getId() );
        }
        if ( dto.getPhoneNumber() != null ) {
            contact.setPhoneNumber( dto.getPhoneNumber() );
        }
        if ( dto.getUsername() != null ) {
            contact.setUsername( dto.getUsername() );
        }
        if ( dto.getUserId() != null ) {
            contact.setUserId( dto.getUserId() );
        }
        if ( dto.getCreatedAt() != null ) {
            contact.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            contact.setUpdatedAt( dto.getUpdatedAt() );
        }
        if ( dto.getDeletedAt() != null ) {
            contact.setDeletedAt( dto.getDeletedAt() );
        }
    }
}
