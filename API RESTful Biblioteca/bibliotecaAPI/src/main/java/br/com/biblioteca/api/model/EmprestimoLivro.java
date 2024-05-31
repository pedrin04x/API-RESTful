package br.com.biblioteca.api.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Table (name = "TB_EmprestimoLivro")
public class EmprestimoLivro {
    
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    private String titulo;
    @Getter
    private long anoPublicacao;
    
    @ManyToOne
    @JoinColumn(name = "userId_TB_usuario")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "bookId_TB_livro")
    private Livro livro;

    @Getter
    private LocalDate dataDeEntrega;
    @Getter
    private boolean entregaRealizada;

}

//@ManyToOne serve pra identificar que aqueles atributos são chaves estrangeiras e se relacionam com outras tabelas.
