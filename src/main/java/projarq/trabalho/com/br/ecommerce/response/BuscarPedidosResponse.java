package projarq.trabalho.com.br.ecommerce.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import projarq.trabalho.com.br.ecommerce.domain.Pedido;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BuscarPedidosResponse implements Serializable {

    private List<Pedido> pedidos;

    public BuscarPedidosResponse() {
    }

}
