package br.com.biblioteca.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Entity
@Table (name = "TB_Livro")
public class Livro {
    
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @Getter
    private String titulo;
    @Getter
    private long anoPublicacao;
    @Getter
    private boolean statusEmp; 
}
