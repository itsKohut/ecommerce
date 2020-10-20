package projarq.trabalho.com.br.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedido {

     EM_ANDAMENTO("Em andamento"),
     ENTREGUE_COM_ATRASO("Entregue com atrasdo"),
     ENTREGUE("Entregue");

     private String descricao;
}
