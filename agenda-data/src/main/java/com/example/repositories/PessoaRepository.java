package com.example.repositories;

import com.example.domain.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {

    private List<Pessoa> pessoas;

    public PessoaRepository() {
        pessoas = new ArrayList<>();
    }

    public Pessoa add(Pessoa pessoa) {
        pessoa.setIdPessoa(pessoas.size() + 1);
        pessoas.add(pessoa);
        return pessoa;
    }

    public List<Pessoa> getPessoaPorNome(String nome) {
        return pessoas.stream().filter(p -> p.getNome().equalsIgnoreCase(nome)).toList();
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }
}
