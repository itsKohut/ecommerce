package projarq.trabalho.com.br.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.entity.*;
import projarq.trabalho.com.br.ecommerce.json.response.BuscarPedidosResponse;
import projarq.trabalho.com.br.ecommerce.repository.ECommerceRepository;
import projarq.trabalho.com.br.ecommerce.repository.PedidoRepository;
import projarq.trabalho.com.br.ecommerce.repository.UsuarioRepository;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

@Service
public class BuscarPedidosService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ECommerceRepository eCommerceRepository;

    public BuscarPedidosResponse buscarPorEcommerce(String email, String eCommerce, StatusPedido statusPedido) {

        final Usuario usuario = usuarioRepository.findByEmail(email);

        final ECommerce eCommerceEntity = eCommerceRepository.findByNome(ECommerceType.valueOf(eCommerce));

        List<Pedido> pedidos = null;

        if (isNull(statusPedido)) {
            pedidos = pedidoRepository.findByClienteAndEcommerce(usuario, eCommerceEntity);

        } else {
            pedidos = pedidoRepository.findByClienteAndEcommerceAndStatusPedido(usuario, eCommerceEntity, statusPedido);
        }

        return BuscarPedidosResponse.builder()
                .pedidos(pedidos)
                .build();

    }

    public BuscarPedidosResponse buscarPorData(String email, Date data, StatusPedido statusPedido) {

        final Usuario usuario = usuarioRepository.findByEmail(email);

        List<Pedido> pedidos = null;

        if (isNull(statusPedido)) {
            pedidos = pedidoRepository.findByClienteAndDataPedido(usuario, data);

        } else {
            pedidos = pedidoRepository.findByClienteAndDataPedidoAndStatusPedido(usuario, data, statusPedido);
        }

        return BuscarPedidosResponse.builder()
                .pedidos(pedidos)
                .build();
    }

    public BuscarPedidosResponse buscarPorAgilidadeDeEntrega(String email) {

        final Usuario usuario = usuarioRepository.findByEmail(email);

        List<Pedido> pedidos = null;

        pedidos = pedidoRepository.findByUsuarioAndPedidosEntreguesMaisRapidos(usuario, StatusPedido.ENTREGUE);

        return BuscarPedidosResponse.builder()
                .pedidos(pedidos)
                .build();
    }

    public BuscarPedidosResponse buscarPorCumprimentoDeEntrega(String email, Boolean entregue) {

        final Usuario usuario = usuarioRepository.findByEmail(email);

        List<Pedido> pedidos = null;

        if (isNull(entregue) || isFalse(entregue)) {
            pedidos = pedidoRepository.findByUsuarioAndPedidosDentroDoPrazo(usuario, StatusPedido.ATRASADO);

        } else {
            pedidos = pedidoRepository.findByClienteAndPedidosEntreguesDentroDoPrazoDeEntrega(usuario, StatusPedido.ENTREGUE);
        }

        return BuscarPedidosResponse.builder()
                .pedidos(pedidos)
                .build();
    }

}
