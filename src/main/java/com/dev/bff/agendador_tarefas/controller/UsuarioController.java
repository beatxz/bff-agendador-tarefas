package com.dev.bff.agendador_tarefas.controller;

import com.dev.bff.agendador_tarefas.business.UsuarioService;
import com.dev.bff.agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.dev.bff.agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.dev.bff.agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.dev.bff.agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.dev.bff.agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.dev.bff.agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.dev.bff.agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.dev.bff.agendador_tarefas.business.dto.out.ViaCepDTOResponse;
import com.dev.bff.agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login e usuarios")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar Usuários",description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO ){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }
    @PostMapping("/login")
    @Operation(summary = "Login Usuários",description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login (@RequestBody LoginRequestDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }
    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por Email",description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado ")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email")String email,
                                                                    @RequestHeader(name = "Authorization",required = false)String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email,token));
    }
    @DeleteMapping ("/{email}")
    @Operation(summary = "Deletar Usuários por id",description = "Deleta  usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso ")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<Void> deleteUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization",required = false)String token){
        usuarioService.deleteUsuarioPorEmail(email,token);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    @Operation(summary = "Atualizar dados de Usuário",description = "Atualizar dados de Usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                                                  @RequestHeader(name = "Authorization",required = false)String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token,dto));
    }
    @PutMapping("/endereco")
    @Operation(summary = "Atualizar Endereço de Usuários",description = "Atualizar Endereço de Usuários")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization",required = false)String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,dto,token));
    }
    @PutMapping("/telefone")
    @Operation(summary = "Atualizar Telefone de Usuários",description = "Atualizar Telefone de Usuários")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization",required = false)String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id,dto,token));
    }
    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuários",description = "Salva Endereço de Usuários")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<EnderecoDTOResponse>cadastroEndereco(@RequestBody EnderecoDTORequest dto,
                                                               @RequestHeader(name = "Authorization",required = false)String token){
        return ResponseEntity.ok(usuarioService.cadastroEndereco(token, dto));
    }
    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuários",description = "Salva Telefone de Usuários")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<TelefoneDTOResponse>cadastroTelefone(@RequestBody TelefoneDTORequest dto,
                                                               @RequestHeader(name = "Authorization",required = false)String token){
        return ResponseEntity.ok(usuarioService.cadastroTelefone(token,dto));
    }
    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca dados por cep", description = "Busca Dados por cep")
    @ApiResponse(responseCode = "200", description = "Dados de endereço retornados com sucesso")
    @ApiResponse(responseCode = "403", description = "Cep inválido")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ViaCepDTOResponse>buscarEndereco(@PathVariable("cep") String cep){

        return ResponseEntity.ok(usuarioService.buscarEnderecoPorCep(cep));
    }
}
