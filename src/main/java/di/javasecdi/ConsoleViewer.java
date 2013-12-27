package com.darwinsys.javasecdi;

import javax.inject.Inject;

public class ConsoleViewer implements View {
    @Inject @MyModel
    private String message;
    
    @Override
    public void renderMessage() {
        System.out.println(message);
    }
}
