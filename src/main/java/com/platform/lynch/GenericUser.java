package com.platform.lynch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GenericUser {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    
}
