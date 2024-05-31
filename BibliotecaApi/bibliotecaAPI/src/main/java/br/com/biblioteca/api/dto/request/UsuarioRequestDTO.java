package br.com.biblioteca.api.dto.request;

import lombok.Getter;

@Getter
public class UsuarioRequestDTO {

    private String nome;
    private long cpf;
    private String senha;
    
    
}
