package projarq.trabalho.com.br.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projarq.trabalho.com.br.ecommerce.domain.StatusPedido;
import projarq.trabalho.com.br.ecommerce.response.BuscarPedidosResponse;
import projarq.trabalho.com.br.ecommerce.service.BuscarPedidosService;
import projarq.trabalho.com.br.ecommerce.service.ImportarPedidosService;

@RestController
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private BuscarPedidosService buscarPedidosService;

    @Autowired
    private ImportarPedidosService importarPedidosService;

    @GetMapping
    public BuscarPedidosResponse buscar(@RequestParam("cpf") String cpf,
                                        @RequestParam("ecommerce") String eCommerce,
                                        @RequestParam(value = "status", required = false) StatusPedido statusPedido) {

        return buscarPedidosService.buscar(cpf, eCommerce, statusPedido);
    }

    @PostMapping("/importar/{cpf}")
    public void importar(@PathVariable("cpf")String cpf) {
        importarPedidosService.importar(cpf);
    }

}
