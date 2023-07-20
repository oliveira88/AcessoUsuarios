package com.ufes.acessousuarios.state.main;

import com.ufes.acessousuarios.command.manternotificacao.ObterQuantidadeNotificacoesCommand;
import com.ufes.acessousuarios.presenter.BuscarNotificacoesPresenter;
import com.ufes.acessousuarios.presenter.BuscarUsuariosPresenter;
import com.ufes.acessousuarios.presenter.LoginPresenter;
import com.ufes.acessousuarios.presenter.MainPresenter;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import com.ufes.acessousuarios.service.NotificacaoServiceFactory;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;

public class LogadoState extends MainPresenterState {
    public LogadoState(MainPresenter mainPresenter) {
        super(mainPresenter);
        registerObserver();
    }
    
    @Override
    public void initComponentes() {
        super.initComponentes();
        boolean isAdmin = UsuarioServiceFactory.getInstance().getService().getUsuarioLogado().isAdmin();
        if(isAdmin) {
            this.view.setTitle("Admin");
            this.view.getTipoUsuario().setText("Administrador");
        } else {
            this.view.setTitle("Usuário");
            this.view.getTipoUsuario().setText("Usuário");
            this.view.getMenuUsuarios().setVisible(false);
        }
        this.view.getBarraMenu().setVisible(true);     
        this.view.getjLabelTipo().setVisible(true);
        this.view.getjPanel1().setVisible(true);
        this.view.getjLabelUsuario().setVisible(true);
        this.view.getNameUsuario().setVisible(true);
        this.view.getTipoUsuario().setVisible(true);
        this.view.getBtnNotificacoes().setVisible(true);
        this.view.getNameUsuario().setText(UsuarioServiceFactory.getInstance().getService().getUsuarioLogado().getNome());
        this.view.getBtnNotificacoes().setText("Notificações: " + NotificacaoServiceFactory.getInstance().getService().obterNotificacoes().size());
    }
    
    private void registerObserver() {
        NotificacaoServiceFactory.getInstance().getService().addObserver(presenter);
    }

    @Override
    public void cadastrarUsuario() {
        new ManterUsuarioPresenter(presenter);
    }

    @Override
    public void buscarUsuarios() {
        new BuscarUsuariosPresenter(presenter);
    }

    @Override
    public void buscarNotificacoes() {
        new BuscarNotificacoesPresenter(presenter);
    }

    @Override
    public void obterQuantidadeNotificacoes() {
        new ObterQuantidadeNotificacoesCommand(presenter).executar();
    }
    
    @Override
    public void logout() {
        presenter.fecharTodasJanelas();
        presenter.setState(new NaoLogadoState(presenter));
        new LoginPresenter(presenter);
    }

    
    public void configurarLog() {
        throw new RuntimeException("Não é possível a configuração de log.");
    }
}
