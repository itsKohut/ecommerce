package projarq.trabalho.com.br.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import java.util.Date;

@RestController
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private BuscarPedidosService buscarPedidosService;

    @Autowired
    private ImportarPedidosService importarPedidosService;

    @Autowired
    private DetalharPedidoService detalharPedidoService;

    @GetMapping("/ecommerce")
    public BuscarPedidosResponse buscarPorEcommerce(@RequestParam("nome") String eCommerce,
                                                    @RequestParam(value = "status", required = false) StatusPedido statusPedido) {

        final UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return buscarPedidosService.buscarPorEcommerce(userDetails.getUsername(), eCommerce, statusPedido);
    }

    @GetMapping("/data")
    public BuscarPedidosResponse buscarPorDataPedido(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataPedido,
                                                     @RequestParam(value = "status", required = false) StatusPedido statusPedido) {

        final UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return buscarPedidosService.buscarPorData(userDetails.getUsername(), dataPedido, statusPedido);
    }

    @GetMapping("/agilidade")
    public BuscarPedidosResponse buscarPorAgilidadeDeEntrega() {

        final UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return buscarPedidosService.buscarPorAgilidadeDeEntrega(userDetails.getUsername());
    }

    @GetMapping("/prazo")
    public BuscarPedidosResponse buscarPorCumprimentoDeEntrega(@RequestParam(value = "entregue", required = false) Boolean entregue) {

        final UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return buscarPedidosService.buscarPorCumprimentoDeEntrega(userDetails.getUsername(), entregue);
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
