package projarq.trabalho.com.br.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {

    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private Endereco endereco;
}
