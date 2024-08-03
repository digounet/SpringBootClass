package com.example.usescases;

import com.example.domain.Pessoa;
import com.example.dtos.AddPessoaDto;
import com.example.dtos.PessoaDto;
import com.example.enums.SexoEnum;
import com.example.exceptions.RequiredParameterException;
import com.example.repositories.PessoaRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PessoaUseCase {

    private PessoaRepository pessoaRepository;

    public List<PessoaDto> getPessoaPorNome(String nome) throws RequiredParameterException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RequiredParameterException("O nome é obrigatório");
        }

        return pessoaRepository.getPessoaPorNome(nome).stream().map(p -> PessoaDto.builder()
                .nome(p.getNome())
                .idPessoa(p.getIdPessoa())
                .email(p.getEmail())
                .telefone(p.getTelefone())
                .build())
                .toList();
    }

    public List<PessoaDto> getPessoas()  {
        return pessoaRepository.getPessoas().stream().map(p -> PessoaDto.builder()
                        .nome(p.getNome())
                        .idPessoa(p.getIdPessoa())
                        .email(p.getEmail())
                        .telefone(p.getTelefone())
                        .build())
                .toList();
    }

    public PessoaDto add(AddPessoaDto addPessoaDto) {
        var pessoa = Pessoa.builder()
                .sexo(SexoEnum.valueOf(addPessoaDto.getSexo()))
                .cpf(addPessoaDto.getCpf())
                .dataNascimento(addPessoaDto.getDataNascimento())
                .email(addPessoaDto.getEmail())
                .nome(addPessoaDto.getNome())
                .telefone(addPessoaDto.getTelefone())
                .build();

        pessoaRepository.add(pessoa);

        return PessoaDto.builder()
                .nome(pessoa.getNome())
                .idPessoa(pessoa.getIdPessoa())
                .email(pessoa.getEmail())
                .telefone(pessoa.getTelefone())
                .build();
    }
}
