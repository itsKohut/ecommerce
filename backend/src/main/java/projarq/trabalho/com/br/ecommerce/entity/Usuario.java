package projarq.trabalho.com.br.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@Table(name = "CLIENTE")
@Getter
@AllArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENTE_ID", unique = true, nullable = false)
    private Long id;

    @JsonIgnore
    private String password;

    @Column(unique = true)
    private String cpf;

    private String nome;

    @Column(unique = true)
    private String email;

    private String telefone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "CLIENTE_ENDERECO")
    private Endereco endereco;

    public Usuario() {}
}
