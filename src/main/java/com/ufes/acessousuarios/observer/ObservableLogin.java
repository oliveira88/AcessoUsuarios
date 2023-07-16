package com.ufes.acessousuarios.observer;

import com.ufes.acessousuarios.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public abstract class ObservableLogin {
    private final List<IObserverLogin> observers = new ArrayList<>();
    
    public void addObserver(IObserverLogin... observers) {
        for(IObserverLogin observer : observers) {
            if(!this.observers.contains(observer)){
                this.observers.add(observer);
            }
        }
    }
    
    public void removeObserver(IObserverLogin observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(Usuario usuario) {
        for(IObserverLogin observer: observers) {
            observer.update(usuario);
        }
    }
}
