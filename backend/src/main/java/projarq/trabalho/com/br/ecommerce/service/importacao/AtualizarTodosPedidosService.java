package projarq.trabalho.com.br.ecommerce.service.importacao;

import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.entity.ECommerceType;

@Service
public class AtualizarTodosPedidosService extends ImportarPedidos{

    @Override
    public ECommerceType getECommerce() {
        return null;
    }
}
