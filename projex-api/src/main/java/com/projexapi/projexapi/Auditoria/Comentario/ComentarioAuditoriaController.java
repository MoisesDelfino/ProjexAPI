package com.projexapi.projexapi.Auditoria.Comentario;

import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Utils.Message;
import com.projexapi.projexapi.Utils.Paginacao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/auditoria/comentario")
@CrossOrigin("*")
@AllArgsConstructor
public class ComentarioAuditoriaController {

    private ComentarioAuditoriaService comentarioAuditoriaService;
    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarRegistros(
            @QuerydslPredicate(root = Projeto.class)
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<ComentarioAuditoria> comentarioList = this.comentarioAuditoriaService.buscarTodos(pageable);

        List<ComentarioAuditoriaRepresentation.Detalhes> listaFinal =
                ComentarioAuditoriaRepresentation.Detalhes.from(comentarioList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(comentarioList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }
    @PostMapping("/criar")
    public ResponseEntity<Message> createComentarioAuditoria(
            @RequestBody @Valid ComentarioAuditoriaRepresentation.Criar criar) {

        this.comentarioAuditoriaService.criarComentarioAuditoria(criar);

        return ResponseEntity.ok(new Message("Auditoria de comentário registrada com sucesso!"));

    }
}
