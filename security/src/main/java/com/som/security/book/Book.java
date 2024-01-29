package com.som.security.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_generator")
    @SequenceGenerator(name = "book_seq_generator", sequenceName = "book_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    private String author;
    private String isbn;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModified;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @PrePersist
    public void prePersist() {
        createDate = LocalDateTime.now();
        lastModified = LocalDateTime.now();
        createdBy = getCurrentUserId();
    }
    private String getCurrentUserId() {
        // Example using Spring Security's SecurityContextHolder
        // Replace this with your actual mechanism for retrieving the user ID
        // Note: Ensure that you have security-related configurations in your project
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            // Assuming your user entity has a method to retrieve the ID
            return userDetails.getUsername();
        }
        // Return a default user ID or handle the case appropriately based on your application logic
        return "N/A";
    }
}
