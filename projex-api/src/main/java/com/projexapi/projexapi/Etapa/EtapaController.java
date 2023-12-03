package com.projexapi.projexapi.Etapa;


import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Projeto.*;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/etapa")
@CrossOrigin("*")
@AllArgsConstructor
public class EtapaController {
    private EtapaService etapaService;

    private ProjetoService projetoService;

    @GetMapping("/")
    public ResponseEntity<Paginacao> buscarEtapas(
            @QuerydslPredicate(root = Etapa.class) Predicate filtroURI,
            @RequestParam(name="tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada) {
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Etapa> etapaList = Objects.isNull(filtroURI) ?
                this.etapaService.buscarTodos(pageable):
                this.etapaService.buscarTodos(filtroURI,pageable);

        List<EtapaRepresentation.Lista> listaFinal =
                EtapaRepresentation.Lista.from(etapaList.getContent());
        Paginacao paginacao = Paginacao.builder()
                .conteudo(listaFinal)
                .paginaSelecionada(paginaSelecionada)
                .proximaPagina(etapaList.hasNext())
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);

    }

    @GetMapping("/{idEtapa}")
    public ResponseEntity<EtapaRepresentation.Detalhes> buscarUmaEtapa(
            @PathVariable Long idEtapa) {

        Etapa etapa = this.etapaService.buscarUmaEtapa(idEtapa);

        EtapaRepresentation.Detalhes detalhes =
                EtapaRepresentation.Detalhes
                        .from(etapa);

        return ResponseEntity
                .ok(detalhes);
    }

    @PostMapping("/{idProjeto}")
    public ResponseEntity<Message> createEtapa(
            @PathVariable Long idProjeto,
            @RequestBody @Valid EtapaRepresentation.Criar criar) {

        LocalDateTime data_previsao_inicio = criar.getData_previsao_inicio();

        LocalDateTime data_previsao_fim = criar.getData_previsao_fim();


        if (data_previsao_fim.isAfter(data_previsao_inicio) || data_previsao_fim.isEqual(data_previsao_inicio)) {
            this.etapaService.criarEtapa(projetoService, idProjeto, criar);

            this.projetoService.atualizarDatasProjeto(idProjeto);

            this.projetoService.incrementaCustoPrevisto(idProjeto);

            return ResponseEntity.ok(new Message("Etapa cadastrada com sucesso!"));
        } else {
            return ResponseEntity.badRequest().body(new Message("A data de início da etapa deve ser menor que a data de finalização!"));
        }

    }

    @PutMapping("/{idEtapa}")
    public ResponseEntity<Message> atualizarEtapa(
            @PathVariable Long idEtapa,
            @RequestBody EtapaRepresentation.Atualizar atualizar) {

           this.etapaService.atualizar(idEtapa, atualizar);

           return ResponseEntity.ok(new Message("Etapa atualizada com sucesso!"));

    }
    @PostMapping("/{idEtapa}/iniciarEtapa")
    public ResponseEntity<Message> iniciarEtapa(@PathVariable Long idEtapa) {

        Etapa etapa = this.etapaService.buscarUmaEtapa(idEtapa);

            if (this.etapaService.verificaSeEtapaEstaIniciada(idEtapa))
                return ResponseEntity.badRequest().body(new Message("Etapa não pode ser iniciada pois já está iniciada!"));
            else if (this.etapaService.verificaSePodeIniciarEtapaPorStatus(idEtapa)){
                        if (this.etapaService.verificaSePrimeiraEtapaDoProjeto(idEtapa)) {
                            this.etapaService.iniciarEtapa(idEtapa);
                            this.projetoService.iniciarProjeto(etapa.getProjeto().getId());
                            return ResponseEntity.ok(new Message("Etapa e projeto iniciados com sucesso!"));
                        } else {
                            this.etapaService.iniciarEtapa(idEtapa);
                            return ResponseEntity.ok(new Message("Etapa iniciada com sucesso!"));
                        }
                } else {
                return ResponseEntity.badRequest().body(new Message("Etapa não pode ser iniciada pois já foi finalizada ou cancelada!"));
                }
    }

    @PostMapping("/{idEtapa}/finalizarEtapa")
    public ResponseEntity<Message> finalizarEtapa(@PathVariable Long idEtapa){
        Etapa etapa = this.etapaService.buscarUmaEtapa(idEtapa);

        if (etapa.getStatus() != Status.C && etapa.getStatus() != Status.F){
            this.etapaService.finalizarEtapa(idEtapa);
            this.projetoService.calculaPercentualConclusao(etapa.getProjeto().getId());
        } else {
            return ResponseEntity.badRequest().body(new Message("Etapa não pode ser finalizada pois está cancelada ou finalizada."));
        }

        return ResponseEntity.ok(new Message("Etapa finalizada com sucesso!"));
    }


    @DeleteMapping("/{idEtapa}")
    public ResponseEntity<Message> inativarEtapa(
            @PathVariable Long idEtapa) {

        this.etapaService.inativar(idEtapa);

        this.projetoService.decrementaCustoPrevisto(idEtapa);

        return ResponseEntity.ok(new Message("Etapa inativada com sucesso!"));

    }

}
