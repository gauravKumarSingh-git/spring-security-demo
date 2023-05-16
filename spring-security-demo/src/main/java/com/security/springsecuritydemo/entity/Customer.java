package com.security.springsecuritydemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String mobileNumber;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)      // so that password can only be sent from UI to backend and not the other way round
    private String password;
    @JsonIgnore                 // used because I dont want to send role data to UI with customer data it will be sent with Authority
    private String role;
    private String createDt;
}
