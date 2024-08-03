package com.example.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PessoaDto {
    private int idPessoa;
    private String nome;
    private String email;
    private String telefone;
}