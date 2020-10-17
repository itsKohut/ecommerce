package projarq.trabalho.com.br.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.domain.ECommerce;
import projarq.trabalho.com.br.ecommerce.domain.Pedido;
import projarq.trabalho.com.br.ecommerce.domain.ProdutoInfo;
import projarq.trabalho.com.br.ecommerce.domain.StatusPedido;
import projarq.trabalho.com.br.ecommerce.entity.ClienteEntity;
import projarq.trabalho.com.br.ecommerce.entity.ECommerceEntity;
import projarq.trabalho.com.br.ecommerce.entity.PedidoEntity;
import projarq.trabalho.com.br.ecommerce.entity.ProdutoInfoEntity;
import projarq.trabalho.com.br.ecommerce.repository.ClienteRepository;
import projarq.trabalho.com.br.ecommerce.repository.ECommerceRepository;
import projarq.trabalho.com.br.ecommerce.repository.PedidoRepository;
import projarq.trabalho.com.br.ecommerce.response.BuscarPedidosResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class BuscarPedidosService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ECommerceRepository eCommerceRepository;

    public BuscarPedidosResponse buscar(String cpf, String eCommerce, StatusPedido statusPedido) {

        ClienteEntity clienteEntity = clienteRepository.findByCpf(cpf);

        ECommerceEntity eCommerceEntity = eCommerceRepository.findByNome(eCommerce);

        List<PedidoEntity> pedidoEntities = null;

        if (isNull(statusPedido)) {
            pedidoEntities = pedidoRepository.findByClienteAndEcommerce(clienteEntity, eCommerceEntity);

        } else {
            pedidoEntities = pedidoRepository.findByClienteAndEcommerceAndStatusPedido(clienteEntity, eCommerceEntity, statusPedido);
        }

        return BuscarPedidosResponse.builder()
                .pedidos(buildPedidos(pedidoEntities))
                .build();

    }

    private List<Pedido> buildPedidos(List<PedidoEntity> pedidoEntities) {

        if (pedidoEntities.isEmpty()) {
            return new ArrayList<>();
        }

        return pedidoEntities.stream()
                .map(pedidoEntity -> Pedido.builder()
                        .dataPedido(pedidoEntity.getDataPedido())
                        .dataEntrega(pedidoEntity.getDataEntrega())
                        .dataEstimativaEntrega(pedidoEntity.getDataEstimativaEntrega())
                        .produtoInfos(buildProdutoInfoList(pedidoEntity.getProdutoInfos()))
                        .valorTotalCompra(pedidoEntity.getValorTotalCompra())
                        .eCommerce(buildEcommerce(pedidoEntity.getEcommerce()))
                        .statusPedido(pedidoEntity.getStatusPedido())
                        .build())
                .collect(Collectors.toList());
    }

    private List<ProdutoInfo> buildProdutoInfoList(List<ProdutoInfoEntity> produtoInfoEntities) {

        if (produtoInfoEntities.isEmpty()) {
            return new ArrayList<>();
        }

        return produtoInfoEntities.stream()
                .map(produtoInfoEntity -> ProdutoInfo.builder()
                        .nome(produtoInfoEntity.getNome())
                        .quantidade(produtoInfoEntity.getQuantidade())
                        .valorUnidade(produtoInfoEntity.getValorUnidade())
                        .build())
                .collect(Collectors.toList());
    }

    private ECommerce buildEcommerce(ECommerceEntity eCommerceEntity) {

        return ECommerce.builder()
                .cnpj(eCommerceEntity.getCnpj())
                .nome(eCommerceEntity.getNome())
                .build();
    }
}
