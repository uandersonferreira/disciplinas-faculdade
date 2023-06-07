package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements Serializable, UserDetails {
    @Id
    @NotBlank(message = "Preencha o campo usuário")
    private String Login;
    @NotBlank(message = "Preencha o campo senha")
    private  String senha;
    @ManyToMany
    @JoinTable(
            name = "tb_usuarios_roles",
            joinColumns = @JoinColumn(name = "login_fk"),
            inverseJoinColumns = @JoinColumn(name = "role_fk")
    )
    private List<Role> roles;


    public Usuario(String login, String senha, List<Role> roles) {
        Login = login;
        this.senha = senha;
        this.roles = roles;
    }

    public Usuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.Login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


}//class
