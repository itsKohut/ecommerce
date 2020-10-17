package projarq.trabalho.com.br.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projarq.trabalho.com.br.ecommerce.domain.Cliente;
import projarq.trabalho.com.br.ecommerce.request.CadastrarClienteRequest;
import projarq.trabalho.com.br.ecommerce.service.CadastrarClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private CadastrarClienteService cadastrarClienteService;

    @PostMapping
    public void cadastrar(@RequestBody CadastrarClienteRequest request) {

        Cliente cliente = Cliente.builder()
                .cpf(request.getCpf())
                .endereco(request.getEndereco())
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .build();

        cadastrarClienteService.cadastrar(cliente);
    }
}
