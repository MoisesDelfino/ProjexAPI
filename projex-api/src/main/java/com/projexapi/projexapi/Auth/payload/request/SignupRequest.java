package com.projexapi.projexapi.Auth.payload.request;

import com.projexapi.projexapi.Auth.Models.Role;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Setor.Setor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;

    private Setor setor;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public Setor getSetor(){ return setor;}

    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {return this.role;}
}
