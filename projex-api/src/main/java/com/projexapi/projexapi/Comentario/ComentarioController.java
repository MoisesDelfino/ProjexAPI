package com.projexapi.projexapi.Comentario;

import com.projexapi.projexapi.Notification.NotificationService;
import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Projeto.ProjetoService;
import com.projexapi.projexapi.Setor.Setor;
import com.projexapi.projexapi.Utils.Message;
import com.projexapi.projexapi.Utils.Paginacao;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/comentario")
@CrossOrigin("*")
@AllArgsConstructor
public class ComentarioController {

    private ComentarioService comentarioService;

    private ProjetoService projetoService;

    private NotificationService notificationService;

    @PostMapping("/{idProjeto}")
    public ResponseEntity<Message> createComentario(
            @PathVariable Long idProjeto,
            @RequestBody @Valid ComentarioRepresentation.Criar criar) {

       this.comentarioService.criarComentario(projetoService, idProjeto, criar);

       Projeto projeto = this.projetoService.buscarUmProjeto(idProjeto);

        Setor setor = projeto.getSetor();

        List<String> tokens = setor.getTokensDispositivo();

        notificationService.sendNotification(tokens, setor.getUsername() + " adicionou um comentário ao projeto " + projeto.getNome() + ".", "Conteúdo: " + criar.getConteudo());

       return ResponseEntity.ok(new Message("Comentário cadastrado com sucesso!"));

    }
    @PutMapping("/{idComentario}")
    public ResponseEntity<Message> atualizarComentario(
            @PathVariable Long idComentario,
            @RequestBody ComentarioRepresentation.Atualizar atualizar) {

        this.comentarioService.atualizar(idComentario, atualizar);


        return ResponseEntity.ok(new Message("Comentário atualizado com sucesso!"));
    }
    @DeleteMapping("/{idComentario}")
    public ResponseEntity<Message> inativarComentario(
            @PathVariable Long idComentario) {

        Comentario comentarioInativo = this.comentarioService.inativar(idComentario);

        ComentarioRepresentation.Detalhes detalhes = ComentarioRepresentation.Detalhes.from(comentarioInativo);

        return ResponseEntity.ok(new Message("Comentário inativado com sucesso!"));

    }
    @GetMapping("/{idComentario}")
    public ResponseEntity<ComentarioRepresentation.Detalhes> buscarUmComentario(
            @PathVariable Long idComentario) {

        Comentario comentario = this.comentarioService.buscarUmComentario(idComentario);

        ComentarioRepresentation.Detalhes detalhes =
                ComentarioRepresentation.Detalhes
                        .from(comentario);

        return ResponseEntity
                .ok(detalhes);
    }
    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarComentarios(
            @QuerydslPredicate(root = Comentario.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Comentario> comentarioList = Objects.isNull(filtroURI) ?
                this.comentarioService.buscarTodos(pageable):
                this.comentarioService.buscarTodos(filtroURI, pageable);

        List<ComentarioRepresentation.Lista> listaFinal =
                ComentarioRepresentation.Lista.from(comentarioList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(comentarioList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }
}
