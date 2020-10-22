package projarq.trabalho.com.br.ecommerce.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projarq.trabalho.com.br.ecommerce.entity.*;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    List<Pedido> findByClienteAndEcommerce(Usuario cliente, ECommerce eCommerce);

    List<Pedido> findByClienteAndEcommerceAndStatusPedido(Usuario cliente, ECommerce eCommerce, StatusPedido statusPedido);

    List<Pedido> findByClienteAndDataPedido(Usuario cliente, Date dataPedido);

    List<Pedido> findByClienteAndDataPedidoAndStatusPedido(Usuario cliente, Date dataPedido, StatusPedido statusPedido);

    @Query("SELECT p FROM Pedido p " +
            "JOIN p.cliente c " +
            "WHERE p.cliente = :usuario and p.statusPedido = :status and p.dataEstimativaEntrega >= p.dataEntrega " +
            "ORDER BY p.dataEntrega ASC ")
    List<Pedido> findByUsuarioAndPedidosEntreguesMaisRapidos(Usuario usuario, StatusPedido status);

    @Query("SELECT p FROM Pedido p " +
            "JOIN p.cliente c " +
            "WHERE p.cliente = :usuario AND p.statusPedido <> :status " +
            "ORDER BY p.dataEntrega ASC ")
    List<Pedido> findByUsuarioAndPedidosDentroDoPrazo(Usuario usuario, StatusPedido status);

    @Query("SELECT p FROM Pedido p " +
            "JOIN p.cliente c " +
            "WHERE p.cliente = :usuario and p.statusPedido = :status and p.dataEstimativaEntrega >= p.dataEntrega " +
            "ORDER BY p.dataEntrega ASC ")
    List<Pedido> findByClienteAndPedidosEntreguesDentroDoPrazoDeEntrega(Usuario usuario, StatusPedido status);

    @Query("SELECT pi FROM Pedido p  " +
            "JOIN p.cliente c " +
            "JOIN p.produtoInfos pi " +
            "WHERE p.cliente = :usuario and p.id = :pedidoID")
    List<ProdutoInfo> findDetailsByUsuarioAndPedido(Usuario usuario, Long pedidoID);


}
