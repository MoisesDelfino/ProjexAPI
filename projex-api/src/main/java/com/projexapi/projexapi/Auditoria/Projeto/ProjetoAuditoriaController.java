package com.projexapi.projexapi.Auditoria.Projeto;


import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Projeto.ProjetoRepresentation;
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
@RequestMapping("api/auditoria/projeto")
@CrossOrigin("*")
@AllArgsConstructor
public class ProjetoAuditoriaController {

    private ProjetoAuditoriaService projetoAuditoriaService;

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarRegistros(
            @QuerydslPredicate(root = Projeto.class)
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<ProjetoAuditoria> projetoList = this.projetoAuditoriaService.buscarTodos(pageable);

        List<ProjetoAuditoriaRepresentation.Detalhes> listaFinal =
                ProjetoAuditoriaRepresentation.Detalhes.from(projetoList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(projetoList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }

    @GetMapping("/{idProjeto}")
    public ResponseEntity<Paginacao> buscarRegistrosPorProjeto(
            @PathVariable Long idProjeto,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<ProjetoAuditoria> projetoAuditoriaList = this.projetoAuditoriaService.buscarTodos(QProjetoAuditoria.projetoAuditoria.id_projeto.eq(idProjeto),pageable);

        List<ProjetoAuditoriaRepresentation.Detalhes> listaFinal =
                ProjetoAuditoriaRepresentation.Detalhes.from(projetoAuditoriaList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(projetoAuditoriaList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }
    @PostMapping("/criar")
    public ResponseEntity<Message> createProjetoAuditoria(
            @RequestBody @Valid ProjetoAuditoriaRepresentation.Criar criar) {


        ProjetoAuditoria projetoAuditoria = this.projetoAuditoriaService.criarProjetoAuditoria(criar);

        ProjetoAuditoriaRepresentation.Detalhes detalhes =
                ProjetoAuditoriaRepresentation.Detalhes.from(projetoAuditoria);

        return ResponseEntity.ok(new Message("Auditoria de projeto registrada com sucesso!"));

    }
}
