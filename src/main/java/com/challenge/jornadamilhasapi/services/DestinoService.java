package com.challenge.jornadamilhasapi.services;

import com.challenge.jornadamilhasapi.dtos.destino.DadosAtualizacaoDestino;
import com.challenge.jornadamilhasapi.dtos.destino.DadosDetalhamentoDestinoDTO;
import com.challenge.jornadamilhasapi.dtos.destino.DadosCadastroDestinoDTO;
import com.challenge.jornadamilhasapi.dtos.destino.DadosDetalhamentoDestinoPorIdDTO;
import com.challenge.jornadamilhasapi.models.Destino;
import com.challenge.jornadamilhasapi.repositories.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    @Autowired
    private ChatService chatService;

    private Destino getDestinoById(Integer id) {
        return destinoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum destino foi encontrado com esse id."));
    }

    private Destino getDestinoByNome(String nome) {
        return destinoRepository.findByNome(nome)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum destino foi encontrado."));
    }

    public DadosDetalhamentoDestinoDTO save(DadosCadastroDestinoDTO dados) {
        Destino destino = new Destino(dados);
        dados.textoDescritivo();

        if (dados.textoDescritivo() == null) {
            String textoGerado = chatService.gerarTexto(dados.nome());
            destino.setTextoDescritivo(textoGerado);
        }

        destinoRepository.save(destino);
        return new DadosDetalhamentoDestinoDTO(destino);
    }

    public Page<Destino> findAll(Pageable pageable) {
        return destinoRepository.findAll(pageable);
    }

    public ResponseEntity<DadosDetalhamentoDestinoPorIdDTO> findById(Integer id) {
        Destino destino = getDestinoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoDestinoPorIdDTO(destino));
    }

    public DadosDetalhamentoDestinoDTO update(Integer id, DadosAtualizacaoDestino dados) {
        Destino destino = getDestinoById(id);
        destino.atualizarInformacoes(dados);
        destinoRepository.save(destino);
        return new DadosDetalhamentoDestinoDTO(destino);
    }

    public void delete(Integer id) {
        Destino destino = getDestinoById(id);
        destinoRepository.delete(destino);
    }

    public ResponseEntity<DadosDetalhamentoDestinoDTO> findByNome(String nome) {
        Destino destinos = getDestinoByNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoDestinoDTO(destinos));
    }
}
