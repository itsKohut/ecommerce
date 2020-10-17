package projarq.trabalho.com.br.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportarPedidosService {

    @Autowired
    private SimularImportacaoPedidosService simularImportacaoPedidosService;

    public void importar(String cpf) {
          simularImportacaoPedidosService.simular(cpf);
    }
}
