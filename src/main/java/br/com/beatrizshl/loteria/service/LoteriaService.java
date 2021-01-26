package br.com.beatrizshl.loteria.service;

import br.com.beatrizshl.loteria.model.NumeroSorteado;
import br.com.beatrizshl.loteria.model.Usuario;
import br.com.beatrizshl.loteria.repository.NumeroSorteadoRepository;
import br.com.beatrizshl.loteria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class LoteriaService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private NumeroSorteadoRepository numeroSorteadoRepository;

    public List<Integer> listarNumerosPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com esse e-mail"));

        List<NumeroSorteado> numeroSorteados = numeroSorteadoRepository.findAllByUsuarioId(usuario.getId());

        if (numeroSorteados.size() > 0)
            return numeroSorteados.stream().map(NumeroSorteado::getNumero).collect(Collectors.toList());

        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    public List<Integer> cadastrarUsuario(String email) {
        if (usuarioRepository.existsByEmail(email))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já cadastrado com esse e-mail");

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario = usuarioRepository.save(usuario);

        for (int i = 0; i < 5; i++) {
            NumeroSorteado numeroSorteado = new NumeroSorteado();
            numeroSorteado.setNumero(geraNumeroAleatorio(usuario.getId()));
            numeroSorteado.setUsuario(usuario);

            numeroSorteadoRepository.save(numeroSorteado);
        }

        List<NumeroSorteado> numeroSorteados = numeroSorteadoRepository.findAllByUsuarioId(usuario.getId());

        return numeroSorteados.stream().map(NumeroSorteado::getNumero).collect(Collectors.toList());
    }

    private Integer geraNumeroAleatorio(Integer id) {
        Random random = new Random();
        Integer numeroAleatorio = random.nextInt(99 - 1);

        if (numeroSorteadoRepository.existsByUsuarioIdAndNumero(id, numeroAleatorio))
            geraNumeroAleatorio(id);

        return numeroAleatorio;
    }

}
