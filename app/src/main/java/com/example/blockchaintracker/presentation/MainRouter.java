package com.example.blockchaintracker.presentation;


import com.example.blockchaintracker.core.router.BaseRouter;

public interface MainRouter extends BaseRouter {

    void navigateToChooseModeFragment();

    void navigateToPoliceFragment();

    void navigateToTrackerUserFragment();
}
