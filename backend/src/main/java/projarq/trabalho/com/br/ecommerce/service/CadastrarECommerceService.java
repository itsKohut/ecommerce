package projarq.trabalho.com.br.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.entity.ECommerce;
import projarq.trabalho.com.br.ecommerce.repository.ECommerceRepository;

@Service
public class CadastrarECommerceService {

    @Autowired
    private ECommerceRepository eCommerceRepository;

    public void cadastrar(ECommerce ecommerce) {
        eCommerceRepository.save(ecommerce);
    }
}
