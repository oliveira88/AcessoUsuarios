package com.ufes.acessousuarios.command.manterusuario;

import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.MainPresenter;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.state.main.LogadoState;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class IncluirUsuarioCommand extends ManterUsuarioCommand {
    private final MainPresenter mainPresenter;
    public IncluirUsuarioCommand(Usuario usuario, MainPresenter mainPresenter) {
        super(usuario);
        this.mainPresenter = mainPresenter;
    }

    @Override
    public void executar() {
        try {
            if(usuarioService.getUsuarioLogado() != null){ //É ADM
                this.usuarioService.criarUsuario(usuario, true);
                JOptionPane.showMessageDialog(mainPresenter.getView(), "Incluído com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                var primeiroUsuario = this.usuarioService.getUsuarios().isEmpty();
                if(primeiroUsuario) {
                    this.usuarioService.criarUsuario(usuario, true);
                    var usuarios = usuarioService.obterTodosUsuarios();
                    var usuarioLogado = usuarios.get(0);
                    usuarioService.setUsuarioLogado(usuarioLogado);
                    mainPresenter.setState(new LogadoState(mainPresenter));
                } else {
                    this.usuarioService.criarUsuario(usuario, false);
                    
                    JOptionPane.showMessageDialog(
                        mainPresenter.getView(), 
                        "Solicitação para acesso enviada para o administrador", 
                        "Solicitação enviada", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
//                    var usuarios = usuarioService.obterTodosUsuarios();
//                    var usuarioLogado = usuarios.get(usuarios.size() - 1);
//                    usuarioService.setUsuarioLogado(usuarioLogado);
//                    mainPresenter.setState(new LogadoState(mainPresenter));
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IncluirUsuarioCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
