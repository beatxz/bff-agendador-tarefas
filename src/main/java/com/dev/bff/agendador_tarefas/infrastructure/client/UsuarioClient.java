package com.dev.bff.agendador_tarefas.infrastructure.client;


import com.dev.bff.agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.dev.bff.agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.dev.bff.agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.dev.bff.agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.dev.bff.agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.dev.bff.agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.dev.bff.agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader(name = "Authorization",required = false)String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO );

    @PostMapping("/login")
     String login (@RequestBody LoginRequestDTO usuarioDTO);


    @DeleteMapping ("/{email}")
    void deleteUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader(name = "Authorization",required = false)String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                           @RequestHeader(name = "Authorization",required = false)String token);


    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(name = "Authorization",required = false)String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(name = "Authorization",required = false)String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastroEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader(name = "Authorization",required = false)String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastroTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader(name = "Authorization",required = false)String token);
}

