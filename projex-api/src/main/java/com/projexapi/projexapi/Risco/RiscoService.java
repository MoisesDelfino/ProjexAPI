package com.projexapi.projexapi.Risco;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Exceptions.NotfoundException;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RiscoService {
    private RiscoRepository riscoRepository;

    public Page<Risco> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.riscoRepository.findAll(filtroURI, pageable);

    }

    public Page<Risco> buscarTodos(Pageable pageable) {
        return this.riscoRepository.findAll(pageable);

    }
    public Risco criarRisco(RiscoRepresentation.Criar criar) {


        Risco riscoCriado = this.riscoRepository.save(Risco.builder()
                .nome(criar.getNome())
                .descricao(criar.getDescricao())
                .nivel(criar.getNivel())
                .ativo(Ativo.A)
                .build());


        return riscoCriado;
    }
    public Risco buscarUmRisco(Long idRisco) {

        return this.getRisco(idRisco);

    }

    public Risco atualizar(
            Long idRisco,
            RiscoRepresentation.Atualizar atualizar) {

        this.getRisco(idRisco);

        Risco riscoParaAtualizar = Risco.builder()
                .id(idRisco)
                .nome(atualizar.getNome())
                .descricao(atualizar.getDescricao())
                .nivel(atualizar.getNivel())
                .ativo(atualizar.getAtivo())
                .build();

        return this.riscoRepository.save(riscoParaAtualizar);

    }
    public Risco inativar(Long idRisco) {

        Risco risco = this.buscarUmRisco(idRisco);


        return this.riscoRepository.save(Risco.builder()
                .id(idRisco)
                .nome(risco.getNome())
                .descricao(risco.getDescricao())
                .nivel(risco.getNivel())
                .ativo(Ativo.I)
                .build());
    }

    private Risco getRisco(Long idRisco) {

        Optional<Risco> riscoAtual =
                this.riscoRepository.findById(idRisco);

        if (riscoAtual.isPresent()) {
            return riscoAtual.get();
        } else {
            throw new NotfoundException("Risco não encontrado");
        }
    }
}
