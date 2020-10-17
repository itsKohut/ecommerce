package projarq.trabalho.com.br.ecommerce.service;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.domain.Pedido;
import projarq.trabalho.com.br.ecommerce.domain.ProdutoInfo;
import projarq.trabalho.com.br.ecommerce.entity.ClienteEntity;
import projarq.trabalho.com.br.ecommerce.entity.ECommerceEntity;
import projarq.trabalho.com.br.ecommerce.entity.PedidoEntity;
import projarq.trabalho.com.br.ecommerce.entity.ProdutoInfoEntity;
import projarq.trabalho.com.br.ecommerce.repository.ClienteRepository;
import projarq.trabalho.com.br.ecommerce.repository.ECommerceRepository;
import projarq.trabalho.com.br.ecommerce.repository.PedidoRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static projarq.trabalho.com.br.ecommerce.domain.StatusPedido.*;

@Service
@Transactional
public class SimularImportacaoPedidosService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ECommerceRepository eCommerceRepository;

    @Autowired
    private Faker faker;

    public void simular(String cpf) {

        ClienteEntity cliente = clienteRepository.findByCpf(cpf);

        final int quantidadePedidosRealizado = RandomUtils.nextInt(1, 11);

        for (int i = 0; i < quantidadePedidosRealizado; i++) {
            Pedido.PedidoBuilder pedidoBuilder = Pedido.builder();
            simularDataPedido(pedidoBuilder);
            simularDataEstimativaEntrega(pedidoBuilder);
            simularDataEntrega(pedidoBuilder);
            simulacaoProdutos(pedidoBuilder);
            Pedido pedido = pedidoBuilder.build();
            calcularValorPedido(pedido);
            calcularStatusPedido(pedido);

            PedidoEntity pedidoEntity = PedidoEntity.builder()
                    .cliente(cliente)
                    .ecommerce(escolherECommerce())
                    .dataPedido(pedido.getDataPedido())
                    .dataEstimativaEntrega(pedido.getDataEstimativaEntrega())
                    .dataEntrega(pedido.getDataEntrega())
                    .statusPedido(pedido.getStatusPedido())
                    .produtoInfos(buildProdutoInfoEntityList(pedido.getProdutoInfos()))
                    .valorTotalCompra(pedido.getValorTotalCompra())
                    .build();

            pedidoRepository.save(pedidoEntity);


        }
    }

    private void simulacaoProdutos(Pedido.PedidoBuilder pedidoBuilder) {

        final int quantidadeItensCompradosPorPedido = RandomUtils.nextInt(1, 11);

        List<ProdutoInfo> produtoInfoList = new ArrayList<>();
        ProdutoInfo.ProdutoInfoBuilder produtoInfoBuilder = ProdutoInfo.builder();

        for (int j = 0; j < quantidadeItensCompradosPorPedido; j++) {

            produtoInfoBuilder
                    .nome(faker.book().title())
                    .quantidade(faker.number().numberBetween(1, 10))
                    .valorUnidade(faker.number().randomDouble(2, 10, 50));

            produtoInfoList.add(produtoInfoBuilder.build());
        }

        pedidoBuilder.produtoInfos(produtoInfoList);
    }

    private void simularDataPedido(Pedido.PedidoBuilder pedidoBuilder) {

        int horasReferencia = faker.number().numberBetween(24, 240);
        Date dataPedido = faker.date().past(horasReferencia, TimeUnit.HOURS);
        pedidoBuilder.dataPedido(dataPedido);
    }

    private void simularDataEstimativaEntrega(Pedido.PedidoBuilder pedidoBuilder) {

        int horasReferencia = faker.number().numberBetween(24, 240);
        Date dataPedido = faker.date().future(horasReferencia, TimeUnit.HOURS);
        pedidoBuilder.dataEstimativaEntrega(dataPedido);
    }

    private void simularDataEntrega(Pedido.PedidoBuilder pedidoBuilder) {

        double chance = faker.number().numberBetween(0, 10);

        if (chance > 5) {
            int horasReferencia = faker.number().numberBetween(24, 480);
            Date dataPedido = faker.date().future(horasReferencia, TimeUnit.HOURS);
            pedidoBuilder.dataEntrega(dataPedido);
        } else {
            pedidoBuilder.dataEntrega(null);
        }
    }

    private void calcularValorPedido(Pedido pedido) {

        double valor = pedido.getProdutoInfos().stream()
                .mapToDouble(produtoInfo -> produtoInfo.getQuantidade() * produtoInfo.getValorUnidade())
                .sum();

        pedido.setValorTotalCompra(new BigDecimal(valor));
    }

    private void calcularStatusPedido(Pedido pedido) {

        if (isNull(pedido.getDataEntrega())) {
            pedido.setStatusPedido(EM_ANDAMENTO);
            return;
        }

        if (pedido.getDataEntrega().after(pedido.getDataEstimativaEntrega())) {
            pedido.setStatusPedido(ENTREGUE_COM_ATRASO);
            return;
        }

        pedido.setStatusPedido(ENTREGUE);
    }


    private List<ProdutoInfoEntity> buildProdutoInfoEntityList(List<ProdutoInfo> produtoInfos) {
        return produtoInfos.stream()
                .map(produtoInfo -> ProdutoInfoEntity.builder()
                        .nome(produtoInfo.getNome())
                        .quantidade(produtoInfo.getQuantidade())
                        .valorUnidade(produtoInfo.getValorUnidade())
                        .build())
                .collect(Collectors.toList());
    }

    private ECommerceEntity escolherECommerce() {

        List<ECommerceEntity> eCommerceEntities = eCommerceRepository.findAll();
        Random rand = new Random();
        ECommerceEntity eCommerceEntity = eCommerceEntities.get(rand.nextInt(eCommerceEntities.size()));

        return eCommerceEntity;
    }
}
