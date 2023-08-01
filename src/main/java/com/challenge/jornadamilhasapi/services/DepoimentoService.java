package com.challenge.jornadamilhasapi.services;

import com.challenge.jornadamilhasapi.dtos.depoimento.DadosAtualizacaoDepoimento;
import com.challenge.jornadamilhasapi.dtos.depoimento.DadosCadastroDepoimentoDTO;
import com.challenge.jornadamilhasapi.dtos.depoimento.DadosDetalhamentoDepoimentoDTO;
import com.challenge.jornadamilhasapi.models.Depoimento;
import com.challenge.jornadamilhasapi.repositories.DepoimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class DepoimentoService {

    @Autowired
    private DepoimentoRepository depoimentoRepository;

    private Depoimento getDepoimentoById(Integer id) {
        return depoimentoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Nenhum depoimento foi encontrado com esse id."));
    }

    public DadosDetalhamentoDepoimentoDTO save(DadosCadastroDepoimentoDTO dados) {
        Depoimento depoimento = new Depoimento(dados);
        depoimentoRepository.save(depoimento);
        return new DadosDetalhamentoDepoimentoDTO(depoimento);
    }

    public Page<Depoimento> findAll(Pageable pageable) {
        return depoimentoRepository.findAll(pageable);
    }

    public ResponseEntity<DadosDetalhamentoDepoimentoDTO> findById(Integer id) {
        Depoimento depoimento = getDepoimentoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoDepoimentoDTO(depoimento));
    }

    public DadosDetalhamentoDepoimentoDTO update(Integer id, DadosAtualizacaoDepoimento dados) {
        Depoimento depoimento = getDepoimentoById(id);
        depoimento.setDepoimento(dados.depoimento());
        depoimentoRepository.save(depoimento);
        return new DadosDetalhamentoDepoimentoDTO(depoimento);
    }

    public void delete(Integer id) {
        Depoimento depoimento = getDepoimentoById(id);
        depoimentoRepository.delete(depoimento);
    }

    public List<Depoimento> getDepoimentosHome() {
        return depoimentoRepository.depoimentosAleatoriosHome();
    }
}
