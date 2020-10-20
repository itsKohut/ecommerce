package projarq.trabalho.com.br.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.entity.Usuario;
import projarq.trabalho.com.br.ecommerce.repository.UsuarioRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        if (username.toLowerCase().contains("admin")) {
            return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
        }

        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}