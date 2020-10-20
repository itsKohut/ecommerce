package projarq.trabalho.com.br.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Builder
@Table(name = "ECOMMERCE")
@AllArgsConstructor
public class ECommerce implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ECOMMERCE_ID", unique = true, nullable = false)
    private Long id;

    @Column(unique = true)
    private Long cnpj;

    @Column(unique = true)
    private String nome;

    public ECommerce(){}
}
