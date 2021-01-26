package br.com.beatrizshl.loteria.controller;

import br.com.beatrizshl.loteria.service.LoteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoteriaController {

    @Autowired
    private LoteriaService loteriaService;

    @GetMapping("listar-numeros/{email}")
    public List<Integer> listarNumerosUsuarioEmail(@PathVariable String email) {
        return loteriaService.listarNumerosPorEmail(email);
    }

    @PostMapping("cadastrar-usuario/{email}")
    public List<Integer> cadastrarUsuarioEmail(@PathVariable String email) {
        return loteriaService.cadastrarUsuario(email);
    }

}
