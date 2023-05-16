package com.security.springsecuritydemo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int noticeId;
    private String noticeSummary;
    private String noticeDetails;
    private LocalDate noticeBegDt;
    private LocalDate noticeEndDt;
    private LocalDate createDt;
    private LocalDate updateDt;
    
}
