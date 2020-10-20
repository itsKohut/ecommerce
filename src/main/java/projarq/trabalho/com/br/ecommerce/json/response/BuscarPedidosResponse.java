package projarq.trabalho.com.br.ecommerce.json.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import projarq.trabalho.com.br.ecommerce.entity.Pedido;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BuscarPedidosResponse implements Serializable {

    private List<Pedido> pedidos;

}
