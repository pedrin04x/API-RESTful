package br.com.biblioteca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.api.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
}
