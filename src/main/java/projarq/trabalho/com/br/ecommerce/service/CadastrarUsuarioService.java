package projarq.trabalho.com.br.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.entity.Usuario;
import projarq.trabalho.com.br.ecommerce.repository.UsuarioRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class CadastrarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public void cadastrar(Usuario usuario) {

        Usuario usuarioEntity = Usuario.builder()
                .password(bcryptEncoder.encode(usuario.getPassword()))
                .endereco(usuario.getEndereco())
                .cpf(usuario.getCpf())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .telefone(usuario.getTelefone())
                .build();

        usuarioRepository.save(usuarioEntity);
    }

}
