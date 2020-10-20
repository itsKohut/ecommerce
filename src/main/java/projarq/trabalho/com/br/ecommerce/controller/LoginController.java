package projarq.trabalho.com.br.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projarq.trabalho.com.br.ecommerce.json.request.LoginClientRequest;
import projarq.trabalho.com.br.ecommerce.json.response.JwtResponse;
import projarq.trabalho.com.br.ecommerce.service.AutenticacaoService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse criaTokenAuth(@RequestBody LoginClientRequest request) {
        return autenticacaoService.criarTokenAuth(request.getEmail(), request.getPassword());
    }
}
