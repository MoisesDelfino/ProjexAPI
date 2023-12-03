package com.projexapi.projexapi.Auditoria.Acao;

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
@RequestMapping("api/auditoria/acao")
@CrossOrigin("*")
@AllArgsConstructor
public class AcaoAuditoriaController {
    private AcaoAuditoriaService acaoAuditoriaService;

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarRegistros(
            @QuerydslPredicate(root = Projeto.class)
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<AcaoAuditoria> acaoList = this.acaoAuditoriaService.buscarTodos(pageable);

        List<AcaoAuditoriaRepresentation.Detalhes> listaFinal =
                AcaoAuditoriaRepresentation.Detalhes.from(acaoList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(acaoList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }
    @PostMapping("/criar")
    public ResponseEntity<Message> createAcaoAuditoria(
            @RequestBody @Valid AcaoAuditoriaRepresentation.Criar criar) {


        AcaoAuditoria acaoAuditoria = this.acaoAuditoriaService.criarAcaoAuditoria(criar);

        AcaoAuditoriaRepresentation.Detalhes detalhes =
                AcaoAuditoriaRepresentation.Detalhes.from(acaoAuditoria);

        return ResponseEntity.ok(new Message("Auditoria de ação registrada com sucesso!"));

    }
}
