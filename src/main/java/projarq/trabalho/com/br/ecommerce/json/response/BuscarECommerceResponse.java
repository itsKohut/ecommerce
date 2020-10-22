package projarq.trabalho.com.br.ecommerce.json.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import projarq.trabalho.com.br.ecommerce.entity.ECommerce;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BuscarECommerceResponse implements Serializable {

    private List<ECommerce> eCommerces;
}
