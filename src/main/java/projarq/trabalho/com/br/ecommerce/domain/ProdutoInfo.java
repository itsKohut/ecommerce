package projarq.trabalho.com.br.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoInfo {

    private String nome;
    private Double valorUnidade;
    private Integer quantidade;
}
