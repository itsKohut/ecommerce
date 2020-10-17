package projarq.trabalho.com.br.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projarq.trabalho.com.br.ecommerce.entity.ECommerceEntity;

import java.util.List;

@Repository
public interface ECommerceRepository extends CrudRepository<ECommerceEntity, Long> {

    List<ECommerceEntity> findAll();

    ECommerceEntity findByNome(String eCommerce);
}
