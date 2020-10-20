package projarq.trabalho.com.br.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Table(name = "PRODUTO_INFO")
@Entity
@AllArgsConstructor
@Getter
public class ProdutoInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUTO_INFO_ID", unique = true, nullable = false)
    private Long id;

    private String nome;
    private Double valorUnidade;
    private Integer quantidade;

    public ProdutoInfo(){}
}
