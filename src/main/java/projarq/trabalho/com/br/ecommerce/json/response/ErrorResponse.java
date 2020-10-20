package projarq.trabalho.com.br.ecommerce.json.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {

    private String mensagem;
    private int httpStatus;
    private Long timeStamp;
}