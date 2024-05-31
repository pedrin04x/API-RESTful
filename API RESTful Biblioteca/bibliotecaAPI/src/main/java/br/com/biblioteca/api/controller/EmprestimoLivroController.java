package br.com.biblioteca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.biblioteca.api.model.Livro;
import br.com.biblioteca.api.model.Usuario;
import br.com.biblioteca.api.service.EmprestimoLivroService;
import br.com.biblioteca.api.repository.UsuarioRepository;
import br.com.biblioteca.api.repository.LivroRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoLivroController {

    @Autowired
    private EmprestimoLivroService emprestimoLivroService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping("/realizar")
    public ResponseEntity<Map<String, String>> realizarEmprestimo(@RequestBody Map<String, Object> payload) {
        Long usuarioId = ((Number) payload.get("usuarioId")).longValue();
        Long livroId = ((Number) payload.get("livroId")).longValue();
        LocalDate dataDeEntrega = LocalDate.parse(payload.get("dataDeEntrega").toString());

        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Livro livro = livroRepository.findById(livroId).orElse(null);

        if (usuario == null || livro == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuário ou livro não encontrado.");
            return ResponseEntity.badRequest().body(response);
        }

        String resultado = emprestimoLivroService.realizarEmprestimo(usuario, livro, dataDeEntrega);

        Map<String, String> response = new HashMap<>();
        response.put("message", resultado);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/devolver")
    public ResponseEntity<Map<String, String>> realizarDevolucao(@RequestBody Map<String, Object> payload) {
        Long usuarioId = ((Number) payload.get("usuarioId")).longValue();
        Long livroId = ((Number) payload.get("livroId")).longValue();

        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Livro livro = livroRepository.findById(livroId).orElse(null);

        if (usuario == null || livro == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuário ou livro não encontrado.");
            return ResponseEntity.badRequest().body(response);
        }

        String resultado = emprestimoLivroService.realizarDevolucao(usuario, livro);

        Map<String, String> response = new HashMap<>();
        response.put("message", resultado);

        return ResponseEntity.ok(response);
    }
}
