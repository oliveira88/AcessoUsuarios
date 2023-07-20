package com.ufes.acessousuarios.Util;


public class ValidadorSenha {

    public static void validar(String senha) {
        var erros = new com.pss.senha.validacao.ValidadorSenha().validar(senha);
        if(!erros.isEmpty()) {
            throw new RuntimeException(erros.get(0));
        }
    }
       
}
