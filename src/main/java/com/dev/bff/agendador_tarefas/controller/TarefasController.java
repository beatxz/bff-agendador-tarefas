package com.dev.bff.agendador_tarefas.controller;


import com.dev.bff.agendador_tarefas.business.TarefasService;
import com.dev.bff.agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.dev.bff.agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.dev.bff.agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.dev.bff.agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas do usuario)")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar tarefas de usuarios", description = "Salvar tarefas de usuarios")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse>gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                           @RequestHeader(name = "Authorization",required = false)String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }
    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por periodo", description = "Busca tarefas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization",required = false)String token){
        return ResponseEntity.ok(tarefasService.buscaTerefasAgendadasPorPeriodo(dataInicial,dataFinal,token));
    }
    @GetMapping
    @Operation(summary = "Busca tarefas por email de usuario", description = "Busca tarefas por email de usuario")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization",required = false)String token){

        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }
    @DeleteMapping
    @Operation(summary = "Deleta tarefas cadastradas por id", description = "Deleta tarefas cadastradas por id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id")String id,
                                                  @RequestHeader(name = "Authorization",required = false)String token){
       tarefasService.deletaTarefaPorId(id,token);

       return ResponseEntity.ok().build();
    }
    @PatchMapping
    @Operation(summary = "Altera status da tarefa", description = "Altera status da tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id")String id,
                                                                      @RequestHeader(name = "Authorization",required = false)String token){
        return ResponseEntity.ok(tarefasService.alterarStatus(status, id,token));
    }
    @PutMapping
    @Operation(summary = "Altera dados da tarefa", description = "Altera dados da tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id")String id,
                                                            @RequestHeader(name = "Authorization",required = false)String token){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto,id,token));
    }


}
