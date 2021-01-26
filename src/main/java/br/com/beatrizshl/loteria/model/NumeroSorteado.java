package br.com.beatrizshl.loteria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NUMERO_SORTEADO")
public class NumeroSorteado {

    @Id
    @Column(name = "ID_NUMERO_SORTEADO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "NR_SORTEADO")
    private Integer numero;

}
