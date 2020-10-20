package projarq.trabalho.com.br.ecommerce.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Data
@Table(name = "ENDERECO")
@AllArgsConstructor
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ENDERECO_ID", unique = true, nullable = false)
    private Long id;
    private Long cep;
    private Long numero;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco() {}
}