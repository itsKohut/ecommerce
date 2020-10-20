package projarq.trabalho.com.br.ecommerce.json.response;

import lombok.Builder;
import projarq.trabalho.com.br.ecommerce.entity.ECommerce;

import java.io.Serializable;
import java.util.List;

@Builder
public class BuscarECommerceResponse implements Serializable {

    private List<ECommerce> eCommerces;
}
