package com.ufes.acessousuarios.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private final List<IObserver> observers = new ArrayList<>();
    
    public void addObserver(IObserver... observers) {
        for(IObserver observer : observers) {
            if(!this.observers.contains(observer)){
                this.observers.add(observer);
            }
        }
    }
    
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers() {
        for(IObserver observer: observers) {
            observer.update();
        }
    }
}
