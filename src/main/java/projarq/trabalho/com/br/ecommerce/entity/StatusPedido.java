package projarq.trabalho.com.br.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedido {

     EM_ANDAMENTO("Em andamento"),
     ATRASADO("Entregue com atraso"),
     ENTREGUE("Entregue");

     private String descricao;
}
