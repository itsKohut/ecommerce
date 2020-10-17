package projarq.trabalho.com.br.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import projarq.trabalho.com.br.ecommerce.domain.StatusPedido;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Table(name = "PEDIDO")
@Entity
@AllArgsConstructor
public class PedidoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PEDIDO_ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENTE_ID")
    private ClienteEntity cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ECOMMERCE_ID")
    private ECommerceEntity ecommerce;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataPedido;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataEstimativaEntrega;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PEDIDO_ID", referencedColumnName = "PEDIDO_ID")
    private List<ProdutoInfoEntity> produtoInfos;

    private BigDecimal valorTotalCompra;

    public PedidoEntity(){}
}
