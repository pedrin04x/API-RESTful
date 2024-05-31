package br.com.biblioteca.api.dto.response;

import lombok.Getter;

@Getter
public class UsuarioResponseDTO {

    private long userId;
    private String nome;
    private long cpf;
    private String senha;
    
    public UsuarioResponseDTO(Long userId, String nome, long cpf, String senha) {
        this.userId = userId;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }
}
