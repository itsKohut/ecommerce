package projarq.trabalho.com.br.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projarq.trabalho.com.br.ecommerce.entity.ECommerce;
import projarq.trabalho.com.br.ecommerce.entity.ECommerceType;

import java.util.List;

@Repository
public interface ECommerceRepository extends CrudRepository<ECommerce, Long> {

    List<ECommerce> findAll();

    ECommerce findByNome(ECommerceType eCommerce);
}
