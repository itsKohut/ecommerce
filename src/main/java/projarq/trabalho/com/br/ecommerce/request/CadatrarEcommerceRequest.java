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
public class CadatrarEcommerceRequest implements Serializable {

    private Long cnpj;
    private String nome;
}
