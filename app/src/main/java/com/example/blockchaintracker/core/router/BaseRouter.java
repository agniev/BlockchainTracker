package com.example.blockchaintracker.core.router;

public interface BaseRouter {

    void clearStack();

    void popBack();

    void removeLast();

    void removeFragmentsExceptsLast();

    void clearStackUntil(String tag);
}
