package projarq.trabalho.com.br.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import projarq.trabalho.com.br.ecommerce.entity.StatusPedido;
import projarq.trabalho.com.br.ecommerce.json.response.BuscarPedidosResponse;
import projarq.trabalho.com.br.ecommerce.json.response.DetalhesPedidoResponse;
import projarq.trabalho.com.br.ecommerce.service.BuscarPedidosService;
import projarq.trabalho.com.br.ecommerce.service.DetalharPedidoService;
import projarq.trabalho.com.br.ecommerce.service.ImportarPedidosService;

@RestController
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private BuscarPedidosService buscarPedidosService;

    @Autowired
    private ImportarPedidosService importarPedidosService;

    @Autowired
    private DetalharPedidoService detalharPedidoService;

    @GetMapping
    public BuscarPedidosResponse buscar(@RequestParam("ecommerce") String eCommerce,
                                        @RequestParam(value = "status", required = false) StatusPedido statusPedido) {

        final UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return buscarPedidosService.buscar(userDetails.getUsername(), eCommerce, statusPedido);
    }

    @PostMapping("/importar")
    @ResponseStatus(HttpStatus.OK)
    public void importar() {

        final UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        importarPedidosService.importar(userDetails.getUsername());
    }


    @GetMapping("/detalhes/{pedidoID}")
    @ResponseStatus(HttpStatus.OK)
    public DetalhesPedidoResponse detalhar(@PathVariable("pedidoID")Long pedidoID) {

        final UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return detalharPedidoService.detalhar(userDetails.getUsername(), pedidoID);
    }
}
