package com.security.springsecuritydemo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
    @Id
    private Long contactId;
    private String contactName;
    private String contactEmail;
    private String subject;
    private String message;
    private LocalDate createDt;
    
}
