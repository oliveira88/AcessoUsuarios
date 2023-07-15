package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.command.manterusuario.IncluirUsuarioCommand;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;

public class IncluirUsuarioState extends ManterUsuarioState {
    public IncluirUsuarioState(ManterUsuarioPresenter presenter) {
        super(presenter);
        initComponents();
    }

     public void initComponents(){
        view.setTitle("Cadastrar Usuário");
        view.getBtnEditar().setVisible(false);
        view.getBtnExcluir().setVisible(false);
        view.getChkAdmin().setVisible(false);
        view.getLblDataCadastroPlaceholder().setVisible(false);
        view.getLblDataCadastro().setVisible(false);
    }
    @Override
    public void salvar() {
        new IncluirUsuarioCommand(presenter).executar();
    }
    
    
}
