package projarq.trabalho.com.br.ecommerce.json.response;

import lombok.Builder;
import lombok.Data;
import projarq.trabalho.com.br.ecommerce.entity.ProdutoInfo;

import java.util.List;

@Data
@Builder
public class DetalhesPedidoResponse {

    private Double valorTotal;
    private List<ProdutoInfo> produtoInfos;

}
