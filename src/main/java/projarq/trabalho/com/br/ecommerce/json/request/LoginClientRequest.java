package projarq.trabalho.com.br.ecommerce.json.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginClientRequest implements Serializable {

    private String email;
    private String password;

}

