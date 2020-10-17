package projarq.trabalho.com.br.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Table(name = "CLIENTE")
@AllArgsConstructor
public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENTE_ID", unique = true, nullable = false)
    private Long id;

    @Column(unique = true)
    private String cpf;

    private String nome;

    @Column(unique = true)
    private String email;

    private String telefone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "CLIENTE_ENDERECO")
    private EnderecoEntity enderecoEntity;

    public ClienteEntity() {}
}
