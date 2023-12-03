package com.projexapi.projexapi.Auditoria.Risco;


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
@RequestMapping("api/auditoria/risco")
@CrossOrigin("*")
@AllArgsConstructor
public class RiscoAuditoriaController {
    private RiscoAuditoriaService riscoAuditoriaService;

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarRegistros(
            @QuerydslPredicate(root = Projeto.class)
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<RiscoAuditoria> riscoList = this.riscoAuditoriaService.buscarTodos(pageable);

        List<RiscoAuditoriaRepresentation.Detalhes> listaFinal =
                RiscoAuditoriaRepresentation.Detalhes.from(riscoList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(riscoList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }
    @PostMapping("/criar")
    public ResponseEntity<Message> createRiscoAuditoria(
            @RequestBody @Valid RiscoAuditoriaRepresentation.Criar criar) {

        this.riscoAuditoriaService.criarRiscoAuditoria(criar);

        return ResponseEntity.ok(new Message("Auditoria de risco registrada com sucesso!"));

    }
}
