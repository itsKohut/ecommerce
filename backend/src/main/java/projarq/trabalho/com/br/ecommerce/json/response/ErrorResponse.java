package projarq.trabalho.com.br.ecommerce.json.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private String mensagem;
    private int httpStatus;
    private Long timeStamp;
}