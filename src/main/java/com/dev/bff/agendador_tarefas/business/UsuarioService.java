package com.dev.bff.agendador_tarefas.business;

import com.dev.bff.agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.dev.bff.agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.dev.bff.agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.dev.bff.agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.dev.bff.agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.dev.bff.agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.dev.bff.agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.dev.bff.agendador_tarefas.business.dto.out.ViaCepDTOResponse;
import com.dev.bff.agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO){
         return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequestDTO dto){
        return client.login(dto);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
       return client.buscarUsuarioPorEmail(email, token);
    }

    public void deleteUsuarioPorEmail(String email,String token){
         client.deleteUsuarioPorEmail(email,token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) {
        return client.atualizaDadoUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){
        return client.atualizaEndereco(enderecoDTO,idEndereco,token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token){
        return client.atualizaTelefone(telefoneDTO,idTelefone,token);
    }

    public EnderecoDTOResponse cadastroEndereco(String token, EnderecoDTORequest dto){
        return client.cadastroEndereco(dto,token);
    }

    public TelefoneDTOResponse cadastroTelefone(String token, TelefoneDTORequest dto){
        return client.cadastroTelefone(dto,token);
    }
    public ViaCepDTOResponse buscarEnderecoPorCep(String cep){
        return client.buscarDadosCep((cep));
    }

}
