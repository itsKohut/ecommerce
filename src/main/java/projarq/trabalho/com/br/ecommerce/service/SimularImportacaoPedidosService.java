package projarq.trabalho.com.br.ecommerce.service;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.entity.ECommerce;
import projarq.trabalho.com.br.ecommerce.entity.Pedido;
import projarq.trabalho.com.br.ecommerce.entity.ProdutoInfo;
import projarq.trabalho.com.br.ecommerce.entity.Usuario;
import projarq.trabalho.com.br.ecommerce.repository.ECommerceRepository;
import projarq.trabalho.com.br.ecommerce.repository.PedidoRepository;
import projarq.trabalho.com.br.ecommerce.repository.UsuarioRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;
import static projarq.trabalho.com.br.ecommerce.entity.StatusPedido.*;

@Service
@Transactional
public class SimularImportacaoPedidosService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ECommerceRepository eCommerceRepository;

    @Autowired
    private Faker faker;

    public void simular(String email) {

        Usuario cliente = usuarioRepository.findByEmail(email);

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

            Pedido novoPedido = Pedido.builder()
                    .cliente(cliente)
                    .ecommerce(escolherECommerce())
                    .dataPedido(pedido.getDataPedido())
                    .dataEstimativaEntrega(pedido.getDataEstimativaEntrega())
                    .dataEntrega(pedido.getDataEntrega())
                    .statusPedido(pedido.getStatusPedido())
                    .produtoInfos(pedido.getProdutoInfos())
                    .valorTotalCompra(pedido.getValorTotalCompra())
                    .build();

            pedidoRepository.save(novoPedido);

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
            pedido.setStatusPedido(ATRASADO);
            return;
        }

        pedido.setStatusPedido(ENTREGUE);
    }

    private ECommerce escolherECommerce() {

        List<ECommerce> eCommerces = eCommerceRepository.findAll();
        Random rand = new Random();
        ECommerce eCommerce = eCommerces.get(rand.nextInt(eCommerces.size()));

        return eCommerce;
    }
}
