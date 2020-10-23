package projarq.trabalho.com.br.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.entity.ECommerce;
import projarq.trabalho.com.br.ecommerce.json.response.BuscarECommerceResponse;
import projarq.trabalho.com.br.ecommerce.repository.ECommerceRepository;

import java.util.List;

@Service
public class BuscarECommerceService {

    @Autowired
    private ECommerceRepository eCommerceRepository;

    public BuscarECommerceResponse buscar() {

        List<ECommerce> eCommerces = eCommerceRepository.findAll();

        return BuscarECommerceResponse.builder()
                .eCommerces(eCommerces)
                .build();
    }
}
