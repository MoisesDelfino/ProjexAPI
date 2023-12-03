package com.projexapi.projexapi.Responsavel;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Exceptions.NotfoundException;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ResponsavelService {
    private ResponsavelRepository responsavelRepository;

    public Page<Responsavel> buscarTodos(Pageable pageable) {
        return this.responsavelRepository.findAll(pageable);
    }

    public Page<Responsavel> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.responsavelRepository.findAll(filtroURI, pageable);

    }
    public Responsavel buscarUmResponsavel(Long idResponsavel) {

        return this.getResponsavel(idResponsavel);

    }

    public Responsavel criarResponsavel(ResponsavelRepresentation.Criar criar) {


        Responsavel responsavelCriado = this.responsavelRepository.save(Responsavel.builder()
                .nome(criar.getNome())
                .telefone(criar.getTelefone())
                .email(criar.getEmail())
                .ativo(Ativo.A)
                .build());


        return responsavelCriado;
    }
    private Responsavel getResponsavel(Long idResponsavel) {

        Optional<Responsavel> responsavelAtual =
                this.responsavelRepository.findById(idResponsavel);

        if (responsavelAtual.isPresent()) {
            return responsavelAtual.get();
        } else {
            throw new NotfoundException("Responsável não encontrado");
        }
    }
    public Responsavel atualizar(
            Long idResponsavel,
            ResponsavelRepresentation.Atualizar atualizar) {

        this.getResponsavel(idResponsavel);

        Responsavel responsavelParaAtualizar = Responsavel.builder()
                .id(idResponsavel)
                .nome(atualizar.getNome())
                .telefone(atualizar.getTelefone())
                .email(atualizar.getEmail())
                .ativo(atualizar.getAtivo())
                .build();

        return this.responsavelRepository.save(responsavelParaAtualizar);

    }
    public Responsavel inativar(Long idResponsavel) {

        Responsavel responsavel = this.buscarUmResponsavel(idResponsavel);


        return this.responsavelRepository.save(Responsavel.builder()
                .id(idResponsavel)
                .nome(responsavel.getNome())
                .telefone(responsavel.getTelefone())
                .email(responsavel.getEmail())
                .ativo(Ativo.I)
                .build());
    }
    public Boolean verificaSeResponsavelTemProjetos(Long idResponsavel) {
        Responsavel responsavel = this.buscarUmResponsavel(idResponsavel);
        if (Objects.isNull(responsavel.getProjetoList()) || responsavel.getProjetoList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
