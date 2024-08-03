package com.example.controllers;

import com.example.dtos.AddPessoaDto;
import com.example.dtos.PessoaDto;
import com.example.exceptions.RequiredParameterException;
import com.example.repositories.PessoaRepository;
import com.example.usescases.PessoaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class PessoaController {


    private PessoaUseCase pessoaUseCase;

    public PessoaController() {
        var pessoaRepository = new PessoaRepository();
        this.pessoaUseCase = new PessoaUseCase(pessoaRepository);
    }

    @GetMapping(value = "pessoas/{nome}")
    public ResponseEntity<List<PessoaDto>> getPessoa(@PathVariable String nome) throws RequiredParameterException {
        var pessoas =  pessoaUseCase.getPessoaPorNome(nome);

        if (pessoas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pessoas);
    }

    @GetMapping(value = "pessoas")
    public ResponseEntity<List<PessoaDto>> getPessoas() {
        var pessoas =  pessoaUseCase.getPessoas();

        if (pessoas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pessoas);
    }

    @PostMapping(value = "pessoas")
    public ResponseEntity<PessoaDto> addPessoa(@RequestBody AddPessoaDto addPessoaDto) {
        var pessoa = pessoaUseCase.add(addPessoaDto);
        var uri = URI.create("pessoas/" + pessoa.getIdPessoa());

        return ResponseEntity.created(uri).body(pessoa);
    }
}
