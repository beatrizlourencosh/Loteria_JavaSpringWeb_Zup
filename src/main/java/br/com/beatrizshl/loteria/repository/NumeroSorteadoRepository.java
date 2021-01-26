package br.com.beatrizshl.loteria.repository;

import br.com.beatrizshl.loteria.model.NumeroSorteado;
import br.com.beatrizshl.loteria.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NumeroSorteadoRepository extends CrudRepository<NumeroSorteado, Integer> {

    List<Integer> findNumeroByUsuario(Usuario usuario);

    boolean existsByUsuarioIdAndNumero(Integer id, Integer numeroAleatorio);

    List<NumeroSorteado> findAllByUsuarioId(Integer id);
}
