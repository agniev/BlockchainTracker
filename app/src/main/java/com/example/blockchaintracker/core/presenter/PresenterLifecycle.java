package com.example.blockchaintracker.core.presenter;

public interface PresenterLifecycle {

    void viewCreated();

    void start();

    void resume();

    void pause();

    void stop();

    void destroy();

    void destroyView();

}
