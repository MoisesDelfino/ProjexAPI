package com.projexapi.projexapi.Comentario;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Exceptions.NotfoundException;
import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Projeto.ProjetoService;
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
public class ComentarioService {
    private ComentarioRepository comentarioRepository;

    public Page<Comentario> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.comentarioRepository.findAll(filtroURI, pageable);

    }

    public Page<Comentario> buscarTodos(Pageable pageable) {
        return this.comentarioRepository.findAll(pageable);

    }
    public Comentario buscarUmComentario(Long idComentario) {

        return this.getComentario(idComentario);

    }
    private Comentario getComentario(Long idComentario) {

        Optional<Comentario> comentarioAtual =
                this.comentarioRepository.findById(idComentario);

        if (comentarioAtual.isPresent()) {
            return comentarioAtual.get();
        } else {
            throw new NotfoundException("Comentário não encontrado");
        }
    }
    public Comentario inativar(Long idComentario) {

        Comentario comentario = this.buscarUmComentario(idComentario);

        return this.comentarioRepository.save(Comentario.builder()
                .id(idComentario)
                .projeto(comentario.getProjeto())
                .setor(comentario.getSetor())
                .conteudo(comentario.getConteudo())
                .data_inclusao(comentario.getData_inclusao())
                .ativo(Ativo.I)
                .build());
    }
    public Comentario atualizar(
            Long idComentario,
            ComentarioRepresentation.Atualizar atualizar) {

        this.getComentario(idComentario);

        Comentario comentarioParaAtualizar = Comentario.builder()
                .id(idComentario)
                .setor(atualizar.getSetor())
                .conteudo(atualizar.getConteudo())
                .data_inclusao(atualizar.getData_inclusao())
                .ativo(atualizar.getAtivo())
                .build();

        return this.comentarioRepository.save(comentarioParaAtualizar);

    }
    public Comentario criarComentario(ProjetoService projetoService,
                                  Long idProjeto,
                                  ComentarioRepresentation.Criar criar) {


        Projeto projeto = projetoService.buscarUmProjeto(idProjeto);

        Comentario comentarioCriado = this.comentarioRepository.save(Comentario.builder()
                .projeto(projeto)
                .setor(criar.getSetor())
                .conteudo(criar.getConteudo())
                .data_inclusao(criar.getData_inclusao())
                .ativo(Ativo.A)
                .build());


        return comentarioCriado;
    }

}
