package projarq.trabalho.com.br.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projarq.trabalho.com.br.ecommerce.entity.ECommerce;
import projarq.trabalho.com.br.ecommerce.json.request.CadatrarEcommerceRequest;
import projarq.trabalho.com.br.ecommerce.json.response.BuscarECommerceResponse;
import projarq.trabalho.com.br.ecommerce.service.BuscarECommerceService;
import projarq.trabalho.com.br.ecommerce.service.CadastrarECommerceService;

import javax.validation.Valid;

@RestController
@RequestMapping("ecommerce")
public class ECommerceController {

    @Autowired
    private BuscarECommerceService buscarECommerceService;

    @Autowired
    private CadastrarECommerceService cadastrarECommerceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BuscarECommerceResponse buscar() {

        return buscarECommerceService.buscar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //@Secured("ROLE_ADMIN")
    public void cadastrar(@Valid @RequestBody CadatrarEcommerceRequest request) {

        ECommerce eCommerce = ECommerce.builder()
                .cnpj(request.getCnpj())
                .nome(request.getNome())
                .build();

        cadastrarECommerceService.cadastrar(eCommerce);
    }

}
