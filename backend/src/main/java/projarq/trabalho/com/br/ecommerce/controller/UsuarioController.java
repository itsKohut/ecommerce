package projarq.trabalho.com.br.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projarq.trabalho.com.br.ecommerce.entity.Usuario;
import projarq.trabalho.com.br.ecommerce.json.request.CadastrarUsuarioRequest;
import projarq.trabalho.com.br.ecommerce.service.CadastrarUsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private CadastrarUsuarioService cadastrarUsuarioService;

    @PostMapping(value = "/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody CadastrarUsuarioRequest request) {

        Usuario usuario = Usuario.builder()
                .password(request.getPassword())
                .cpf(request.getCpf())
                .endereco(request.getEndereco())
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .build();

        cadastrarUsuarioService.cadastrar(usuario);
    }
}
