package projarq.trabalho.com.br.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projarq.trabalho.com.br.ecommerce.domain.Cliente;
import projarq.trabalho.com.br.ecommerce.domain.Endereco;
import projarq.trabalho.com.br.ecommerce.entity.ClienteEntity;
import projarq.trabalho.com.br.ecommerce.entity.EnderecoEntity;
import projarq.trabalho.com.br.ecommerce.repository.ClienteRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class CadastrarClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void cadastrar(Cliente cliente) {

        ClienteEntity clienteEntity = ClienteEntity.builder()
                .enderecoEntity(buildEndereco(cliente.getEndereco()))
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();

        clienteRepository.save(clienteEntity);
    }

    private EnderecoEntity buildEndereco(Endereco endereco) {
        return EnderecoEntity.builder()
                .bairro(endereco.getBairro())
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .build();
    }
}
