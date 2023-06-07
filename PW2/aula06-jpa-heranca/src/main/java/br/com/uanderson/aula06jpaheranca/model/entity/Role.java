package br.com.uanderson.aula06jpaheranca.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
public class Role implements GrantedAuthority {
    @Id
    private String role;
    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return this.role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}//class
