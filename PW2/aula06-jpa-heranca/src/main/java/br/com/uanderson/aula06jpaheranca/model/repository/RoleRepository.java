package br.com.uanderson.aula06jpaheranca.model.repository;

import br.com.uanderson.aula06jpaheranca.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findRoleById(Long id);

}
