package br.com.uanderson.aula06jpaheranca.config;

import br.com.uanderson.aula06jpaheranca.model.entity.Usuario;
import br.com.uanderson.aula06jpaheranca.model.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UsuarioDetailsConfig implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUsuarioByLogin(login);
        if (usuario == null) throw new UsernameNotFoundException("Usuário não encontrado!");//sintaxe válida pois existe somente um condição a ser testada.
        return new User(
                usuario.getLogin(),
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                usuario.getAuthorities()
                );
    }//method

}//class
