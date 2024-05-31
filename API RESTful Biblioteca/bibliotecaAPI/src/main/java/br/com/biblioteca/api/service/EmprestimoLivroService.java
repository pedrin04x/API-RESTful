package br.com.biblioteca.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.api.model.EmprestimoLivro;
import br.com.biblioteca.api.model.Livro;
import br.com.biblioteca.api.model.Usuario;
import br.com.biblioteca.api.repository.EmprestimoLivroRepository;

import java.util.List;
import java.time.LocalDate;

@Service
public class EmprestimoLivroService {
    
    @Autowired
    private EmprestimoLivroRepository emprestimoLivroRepository;

    // Regra para controlar o número máximo de empréstimos por usuário
    public boolean verificarLimiteEmprestimos(Usuario usuario) {
        List<EmprestimoLivro> emprestimosPendentes = emprestimoLivroRepository.findByUsuarioAndEntregaRealizadaFalse(usuario);
        return emprestimosPendentes.size() < 3;
    }

    // Regra para verificar se já existe um empréstimo pendente para o livro por esse usuário
    public boolean verificarEmprestimoPendente(Usuario usuario, Livro livro) {
        return emprestimoLivroRepository.existsByUsuarioAndLivroAndEntregaRealizadaFalse(usuario, livro);
    }

    // Regra para realizar um empréstimo de livro
    public String realizarEmprestimo(Usuario usuario, Livro livro, LocalDate dataDeEntrega) {
        if (!verificarLimiteEmprestimos(usuario)) {
            return "Usuário atingiu o limite de empréstimos pendentes.";
        }
        if (verificarEmprestimoPendente(usuario, livro)) {
            return "Este livro já foi emprestado para o usuário e ainda não foi devolvido.";
        }
        EmprestimoLivro emprestimo = new EmprestimoLivro();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataDeEntrega(dataDeEntrega);
        emprestimo.setEntregaRealizada(false);
        emprestimoLivroRepository.save(emprestimo);
        return "Empréstimo realizado com sucesso.";
    }

    // Regra para realizar a devolução de um livro
    public String realizarDevolucao(Usuario usuario, Livro livro) {
        EmprestimoLivro emprestimo = emprestimoLivroRepository.findByUsuarioAndLivroAndEntregaRealizadaFalse(usuario, livro);
        if (emprestimo == null) {
            return "Nenhum empréstimo pendente encontrado para este livro e usuário.";
        }
        emprestimo.setEntregaRealizada(true);
        emprestimoLivroRepository.save(emprestimo);
        return "Livro devolvido com sucesso.";
    }
}
