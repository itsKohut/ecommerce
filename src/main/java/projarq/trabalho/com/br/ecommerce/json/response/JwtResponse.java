package projarq.trabalho.com.br.ecommerce.json.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class JwtResponse implements Serializable {

    private final String token;

}