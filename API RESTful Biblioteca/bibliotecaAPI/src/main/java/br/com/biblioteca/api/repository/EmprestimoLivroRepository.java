package br.com.biblioteca.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biblioteca.api.model.EmprestimoLivro;
import br.com.biblioteca.api.model.Livro;
import br.com.biblioteca.api.model.Usuario;

public interface EmprestimoLivroRepository extends JpaRepository<EmprestimoLivro, Long> {
    List<EmprestimoLivro> findByUsuarioAndEntregaRealizadaFalse(Usuario usuario);
    boolean existsByUsuarioAndLivroAndEntregaRealizadaFalse(Usuario usuario, Livro livro);
    EmprestimoLivro findByUsuarioAndLivroAndEntregaRealizadaFalse(Usuario usuario, Livro livro);
}

