package projarq.trabalho.com.br.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.entity.Usuario;
import projarq.trabalho.com.br.ecommerce.json.response.DetalhesPedidoResponse;
import projarq.trabalho.com.br.ecommerce.repository.PedidoRepository;
import projarq.trabalho.com.br.ecommerce.repository.UsuarioRepository;

@Service
public class DetalharPedidoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public DetalhesPedidoResponse detalhar(String email, Long pedidoID) {

        Usuario usuario = usuarioRepository.findByEmail(email);

        return DetalhesPedidoResponse.builder()
                .produtoInfos(pedidoRepository.findDetailsByUsuarioAndPedido(usuario, pedidoID))
                .build();
    }

}
