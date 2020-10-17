package projarq.trabalho.com.br.ecommerce.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {

    @JsonIgnore
    private Cliente cliente;

    private ECommerce eCommerce;
    private Date dataPedido;
    private Date dataEstimativaEntrega;
    private Date dataEntrega;
    private StatusPedido statusPedido;
    private List<ProdutoInfo> produtoInfos;
    private BigDecimal valorTotalCompra;

}
