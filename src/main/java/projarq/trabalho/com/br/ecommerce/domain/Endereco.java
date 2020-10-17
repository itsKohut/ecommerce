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
public class Endereco implements Serializable {

    private Long cep;
    private Long numero;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

}
