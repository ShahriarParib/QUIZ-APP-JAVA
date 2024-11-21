package com.example.quizapplication;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;

import java.util.EventListener;

public interface  newscreenlistener extends EventHandler {

    public  void  changescreen (Node node);

}