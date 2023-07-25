package com.challenge.jornadamilhasapi.services;

import com.challenge.jornadamilhasapi.dtos.destino.DadosAtualizacaoDestino;
import com.challenge.jornadamilhasapi.dtos.destino.DadosDetalhamentoDestinoDTO;
import com.challenge.jornadamilhasapi.dtos.destino.DadosCadastroDestinoDTO;
import com.challenge.jornadamilhasapi.models.Destino;
import com.challenge.jornadamilhasapi.repositories.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    public DadosDetalhamentoDestinoDTO save(DadosCadastroDestinoDTO dados) {
        Destino destino = new Destino(dados);
        destinoRepository.save(destino);
        return new DadosDetalhamentoDestinoDTO(destino);
    }

    public Page<Destino> findAll(Pageable pageable) {
        return destinoRepository.findAll(pageable);
    }

    public Optional<Destino> findById(Integer id) {
        return destinoRepository.findById(id);
    }

    public DadosDetalhamentoDestinoDTO update(Integer id, DadosAtualizacaoDestino dados) {
        Optional<Destino> destinoOP = destinoRepository.findById(id);
        Destino destino = destinoOP.get();

        destino.atualizarInformacoes(dados);
        destinoRepository.save(destino);

        return new DadosDetalhamentoDestinoDTO(destino);

    }

    public void delete(Destino destino) {
        destinoRepository.delete(destino);
    }

    public List<Destino> findByNome(String nome) {
        return destinoRepository.findByNome(nome);
    }
}
