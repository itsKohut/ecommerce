package projarq.trabalho.com.br.ecommerce.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarEnderecoRequest implements Serializable {

    private Long cpfCliente;
    private Long cep;
    private Long numero;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

}
