package projarq.trabalho.com.br.ecommerce.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projarq.trabalho.com.br.ecommerce.entity.*;

import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    List<Pedido> findByClienteAndEcommerce(Usuario cliente, ECommerce eCommerce);

    List<Pedido> findByClienteAndEcommerceAndStatusPedido(Usuario cliente, ECommerce eCommerce, StatusPedido statusPedido);

    @Query("SELECT pi FROM Pedido p  " +
            "JOIN p.cliente c " +
            "JOIN p.produtoInfos pi " +
            "WHERE p.cliente = :usuarioEntity and p.id = :pedidoID")
    List<ProdutoInfo> findDetailsByUsuarioAndPedido(Usuario usuario, Long pedidoID);


}
