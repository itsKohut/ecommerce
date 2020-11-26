package projarq.trabalho.com.br.ecommerce.service.importacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.entity.ECommerceType;
import projarq.trabalho.com.br.ecommerce.entity.Usuario;
import projarq.trabalho.com.br.ecommerce.repository.UsuarioRepository;
import projarq.trabalho.com.br.ecommerce.service.importacao.ImportarPedidos;

import java.util.List;

@Service
public class ImportarPedidosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ImportarPedidos> services;

    public void importar(String email, ECommerceType eCommerceType) {

        Usuario cliente = usuarioRepository.findByEmail(email);

        ImportarPedidos importarPedidos = services.stream()
                .filter(service -> service.getECommerce() == eCommerceType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Errado"));

        importarPedidos.atualizar(cliente);
    }
}
