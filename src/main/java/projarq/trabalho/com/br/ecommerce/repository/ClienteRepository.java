package projarq.trabalho.com.br.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projarq.trabalho.com.br.ecommerce.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteEntity, Long> {

    ClienteEntity findByCpf(String cpf);
}
