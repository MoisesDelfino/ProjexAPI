package com.projexapi.projexapi.Auditoria.Responsavel;

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
@RequestMapping("api/auditoria/responsavel")
@CrossOrigin("*")
@AllArgsConstructor
public class ResponsavelAuditoriaController {
    private ResponsavelAuditoriaService responsavelAuditoriaService;

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarRegistros(
            @QuerydslPredicate(root = Projeto.class)
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<ResponsavelAuditoria> responsavelList = this.responsavelAuditoriaService.buscarTodos(pageable);

        List<ResponsavelAuditoriaRepresentation.Detalhes> listaFinal =
                ResponsavelAuditoriaRepresentation.Detalhes.from(responsavelList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(responsavelList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }
    @PostMapping("/criar")
    public ResponseEntity<Message> createResponsavelAuditoria(
            @RequestBody @Valid ResponsavelAuditoriaRepresentation.Criar criar) {


        ResponsavelAuditoria responsavelAuditoria = this.responsavelAuditoriaService.criarResponsavelAuditoria(criar);

        ResponsavelAuditoriaRepresentation.Detalhes detalhes =
                ResponsavelAuditoriaRepresentation.Detalhes.from(responsavelAuditoria);

        return ResponseEntity.ok(new Message("Auditoria de responsável registrada com sucesso!"));

    }
}

