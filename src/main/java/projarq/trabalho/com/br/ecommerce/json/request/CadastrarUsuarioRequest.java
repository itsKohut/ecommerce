package projarq.trabalho.com.br.ecommerce.json.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projarq.trabalho.com.br.ecommerce.entity.Endereco;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarUsuarioRequest implements Serializable {

    private String password;
    private String cpf;
    private String nome;
    private Endereco endereco;
    private String email;
    private String telefone;
}
