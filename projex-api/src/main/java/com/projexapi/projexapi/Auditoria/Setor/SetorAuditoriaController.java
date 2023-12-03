package com.projexapi.projexapi.Auditoria.Setor;

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
@RequestMapping("api/auditoria/setor")
@CrossOrigin("*")
@AllArgsConstructor
public class SetorAuditoriaController {
    private SetorAuditoriaService setorAuditoriaService;

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarRegistros(
            @QuerydslPredicate(root = Projeto.class)
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<SetorAuditoria> setorList = this.setorAuditoriaService.buscarTodos(pageable);

        List<SetorAuditoriaRepresentation.Detalhes> listaFinal =
                SetorAuditoriaRepresentation.Detalhes.from(setorList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(setorList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }
    @PostMapping("/criar")
    public ResponseEntity<Message> createSetorAuditoria(
            @RequestBody @Valid SetorAuditoriaRepresentation.Criar criar) {


        this.setorAuditoriaService.criarSetorAuditoria(criar);


        return ResponseEntity.ok(new Message("Auditoria de setor registrada com sucesso!"));

    }
}
