package com.challenge.jornadamilhasapi.services;

import com.challenge.jornadamilhasapi.dtos.depoimento.DadosAtualizacaoDepoimento;
import com.challenge.jornadamilhasapi.dtos.depoimento.DadosCadastroDepoimentoDTO;
import com.challenge.jornadamilhasapi.dtos.depoimento.DadosDetalhamentoDepoimentoDTO;
import com.challenge.jornadamilhasapi.models.Depoimento;
import com.challenge.jornadamilhasapi.repositories.DepoimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Depoimento> findAll(Pageable pageable) {
        return depoimentoRepository.findAll(pageable);
    }

    public Optional<Depoimento> findById(Integer id) {
        return depoimentoRepository.findById(id);
    }

    public DadosDetalhamentoDepoimentoDTO update(Integer id, DadosAtualizacaoDepoimento dados) {
        Optional<Depoimento> depoimentoOP = depoimentoRepository.findById(id);
        Depoimento depoimento = depoimentoOP.get();
        depoimento.setDepoimento(dados.depoimento());
        depoimentoRepository.save(depoimento);

        return new DadosDetalhamentoDepoimentoDTO(depoimento);
    }

    public void delete(Depoimento depoimento) {
        depoimentoRepository.delete(depoimento);
    }

    public List<Depoimento> getDepoimentosHome() {
        return depoimentoRepository.depoimentosAleatoriosHome();
    }
}
