package projarq.trabalho.com.br.ecommerce.json.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import projarq.trabalho.com.br.ecommerce.entity.ProdutoInfo;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class DetalhesPedidoResponse {

    private List<ProdutoInfo> produtoInfos;

}
