package br.com.biblioteca.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.api.model.Livro;
import br.com.biblioteca.api.repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    //Método para listar todos os livros
    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    //Método para buscar buscar livro por id
    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    //Método para salvar dados do livro no banco de dados
    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    //Método para alterar livro
    public Livro atualizar(Long id, Livro livroAtualizado) {
        return livroRepository.findById(id).map(livro -> {
            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
            livro.setStatusEmp(livroAtualizado.isStatusEmp());
            return livroRepository.save(livro);
        }).orElseGet(() -> {
            livroAtualizado.setBookId(id);
            return livroRepository.save(livroAtualizado);
        });
    }

    //Método para deletar livro
    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }
}
