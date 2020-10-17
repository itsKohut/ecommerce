package projarq.trabalho.com.br.ecommerce.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projarq.trabalho.com.br.ecommerce.domain.Endereco;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarClienteRequest implements Serializable {

    private String cpf;
    private String nome;
    private Endereco endereco;
    private String email;
    private String telefone;
}
