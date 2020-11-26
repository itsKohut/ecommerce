package projarq.trabalho.com.br.ecommerce.service.importacao;

import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.entity.ECommerceType;

import static projarq.trabalho.com.br.ecommerce.entity.ECommerceType.AMAZON;

@Service
public class ImportarPedidosAmazonService extends ImportarPedidos {

    @Override
    public ECommerceType getECommerce() {
        return AMAZON;
    }
}
