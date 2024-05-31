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
@Table (name = "TB_Usuario")
public class Usuario {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Getter
    private String nome;
    @Getter
    private long cpf;
    @Getter
    private String senha;
    
}
