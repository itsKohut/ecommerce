package projarq.trabalho.com.br.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
@Setter
public class Pedido implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PEDIDO_ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENTE_ID")
    private Usuario cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ECOMMERCE_ID")
    private ECommerce ecommerce;

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

    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PEDIDO_ID", referencedColumnName = "PEDIDO_ID")
    private List<ProdutoInfo> produtoInfos;

    private BigDecimal valorTotalCompra;

    public Pedido(){}
}
