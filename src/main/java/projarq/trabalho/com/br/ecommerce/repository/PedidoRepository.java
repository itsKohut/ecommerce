package projarq.trabalho.com.br.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projarq.trabalho.com.br.ecommerce.domain.StatusPedido;
import projarq.trabalho.com.br.ecommerce.entity.ClienteEntity;
import projarq.trabalho.com.br.ecommerce.entity.ECommerceEntity;
import projarq.trabalho.com.br.ecommerce.entity.PedidoEntity;

import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoEntity, Long> {

    List<PedidoEntity> findByCliente(ClienteEntity cliente);

    List<PedidoEntity>  findByClienteAndEcommerce(ClienteEntity cliente, ECommerceEntity eCommerce);

    List<PedidoEntity>  findByClienteAndEcommerceAndStatusPedido(ClienteEntity cliente, ECommerceEntity eCommerce, StatusPedido statusPedido);

}
