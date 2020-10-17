package projarq.trabalho.com.br.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.domain.ECommerce;
import projarq.trabalho.com.br.ecommerce.entity.ECommerceEntity;
import projarq.trabalho.com.br.ecommerce.repository.ECommerceRepository;
import projarq.trabalho.com.br.ecommerce.response.BuscarECommerceResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarECommerceService {

    @Autowired
    private ECommerceRepository eCommerceRepository;

    public BuscarECommerceResponse buscar() {

        List<ECommerceEntity> eCommerceEntities = eCommerceRepository.findAll();

        return BuscarECommerceResponse.builder()
                .eCommerces(buildECommerces(eCommerceEntities))
                .build();
    }

    public List<ECommerce> buildECommerces(List<ECommerceEntity> eCommerceEntities) {

        if (eCommerceEntities.isEmpty()) {
            return null;
        }

        return eCommerceEntities.stream()
                .map(eCommerceEntity -> ECommerce.builder()
                        .cnpj(eCommerceEntity.getCnpj())
                        .nome(eCommerceEntity.getNome())
                        .build())
                .collect(Collectors.toList());
    }
}
