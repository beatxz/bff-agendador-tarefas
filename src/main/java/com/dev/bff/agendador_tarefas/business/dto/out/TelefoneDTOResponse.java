package com.dev.bff.agendador_tarefas.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TelefoneDTOResponse {

    private Long id;
    private Long numero;
    private Long ddd;
}
