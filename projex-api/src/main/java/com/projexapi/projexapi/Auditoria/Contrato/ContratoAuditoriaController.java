package com.projexapi.projexapi.Auditoria.Contrato;


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
@RequestMapping("api/auditoria/contrato")
@CrossOrigin("*")
@AllArgsConstructor
public class ContratoAuditoriaController {
    private ContratoAuditoriaService contratoAuditoriaService;
    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarRegistros(
            @QuerydslPredicate(root = Projeto.class)
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<ContratoAuditoria> contratoList = this.contratoAuditoriaService.buscarTodos(pageable);

        List<ContratoAuditoriaRepresentation.Detalhes> listaFinal =
                ContratoAuditoriaRepresentation.Detalhes.from(contratoList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(contratoList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }
    @PostMapping("/criar")
    public ResponseEntity<Message> createContratoAuditoria(
            @RequestBody @Valid ContratoAuditoriaRepresentation.Criar criar) {

        this.contratoAuditoriaService.criarContratoAuditoria(criar);

        return ResponseEntity.ok(new Message("Auditoria de contrato registrada com sucesso!"));

    }
}
