package br.com.biblioteca.api.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ReturnModel {
    
    private String mensagem;
}

//Ideia de tabela com a função de retornar uma mensagem positiva ou negativa no momento do empréstimo