package projarq.trabalho.com.br.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.entity.ECommerce;
import projarq.trabalho.com.br.ecommerce.entity.Pedido;
import projarq.trabalho.com.br.ecommerce.entity.StatusPedido;
import projarq.trabalho.com.br.ecommerce.entity.Usuario;
import projarq.trabalho.com.br.ecommerce.json.response.BuscarPedidosResponse;
import projarq.trabalho.com.br.ecommerce.repository.ECommerceRepository;
import projarq.trabalho.com.br.ecommerce.repository.PedidoRepository;
import projarq.trabalho.com.br.ecommerce.repository.UsuarioRepository;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class BuscarPedidosService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ECommerceRepository eCommerceRepository;

    public BuscarPedidosResponse buscar(String email, String eCommerce, StatusPedido statusPedido) {

        final Usuario usuario = usuarioRepository.findByEmail(email);

        final ECommerce eCommerceEntity = eCommerceRepository.findByNome(eCommerce);

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

}
