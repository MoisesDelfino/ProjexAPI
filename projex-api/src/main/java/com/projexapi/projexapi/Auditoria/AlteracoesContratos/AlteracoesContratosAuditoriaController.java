package com.projexapi.projexapi.Auditoria.AlteracoesContratos;

import com.projexapi.projexapi.Auditoria.Acao.AcaoAuditoria;
import com.projexapi.projexapi.Auditoria.Acao.AcaoAuditoriaRepresentation;
import com.projexapi.projexapi.Auditoria.Acao.AcaoAuditoriaService;
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
@RequestMapping("api/auditoria/alteracoesContratos")
@CrossOrigin("*")
@AllArgsConstructor
public class AlteracoesContratosAuditoriaController {
    private AlteracoesContratosAuditoriaService alteracoesAuditoriaService;

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarRegistros(
            @QuerydslPredicate(root = Projeto.class)
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<AlteracoesContratosAuditoria> alteracoesContratosList = this.alteracoesAuditoriaService.buscarTodos(pageable);

        List<AlteracoesContratosAuditoriaRepresentation.Detalhes> listaFinal =
                AlteracoesContratosAuditoriaRepresentation.Detalhes.from(alteracoesContratosList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(alteracoesContratosList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }
    @PostMapping("/criar")
    public ResponseEntity<Message> createAlteracoesContratosAuditoria(
            @RequestBody @Valid AlteracoesContratosAuditoriaRepresentation.Criar criar) {


        AlteracoesContratosAuditoria alteracoesContratosAuditoria = this.alteracoesAuditoriaService.criarAlteracoesContratosAuditoria(criar);

        AlteracoesContratosAuditoriaRepresentation.Detalhes detalhes =
                AlteracoesContratosAuditoriaRepresentation.Detalhes.from(alteracoesContratosAuditoria);

        return ResponseEntity.ok(new Message("Auditoria de alteração de contrato registrada com sucesso!"));

    }
}
