package projarq.trabalho.com.br.ecommerce.response;

import lombok.Builder;
import projarq.trabalho.com.br.ecommerce.domain.ECommerce;

import java.io.Serializable;
import java.util.List;

@Builder
public class BuscarECommerceResponse implements Serializable {

    private List<ECommerce> eCommerces;
}
