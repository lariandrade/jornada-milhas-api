package com.challenge.jornadamilhasapi.services;

import com.challenge.jornadamilhasapi.dtos.DadosCadastroDepoimentoDTO;
import com.challenge.jornadamilhasapi.dtos.DadosDetalhamentoDepoimentoDTO;
import com.challenge.jornadamilhasapi.models.Depoimento;
import com.challenge.jornadamilhasapi.repositories.DepoimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepoimentoService {

    @Autowired
    private DepoimentoRepository depoimentoRepository;

    public DadosDetalhamentoDepoimentoDTO save(DadosCadastroDepoimentoDTO dados) {
        Depoimento depoimento = new Depoimento(dados);
        depoimentoRepository.save(depoimento);
        return new DadosDetalhamentoDepoimentoDTO(depoimento);
    }

    public List<Depoimento> findall() {
        return depoimentoRepository.findAll();
    }

    public Optional<Depoimento> findById(Integer id) {
        return depoimentoRepository.findById(id);
    }
}
