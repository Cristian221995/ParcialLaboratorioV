package com.example.SimulacroParcial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Publicacion {
    @Id
    @GeneratedValue
    private Integer id;

    private String titulo;
    private String descripcion;
    private String foto;
    private LocalDate fecha;
    private Integer liked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "publicacion")
    private List<Comentario> Comentarios;

    @PrePersist
    private void setearFecha(){
        this.setFecha(LocalDate.now());
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        //this.setFecha(LocalDate.parse(formatter.format(LocalDate.now())));
    }
}
