package com.example.blockchaintracker.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.blockchaintracker.core.activities.BaseActivity;
import com.example.blockchaintracker.presentation.choose_mode.ChooseModeFragment;

public class MainActivity extends BaseActivity<MainRouter> implements MainRouter {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            navigateToChooseModeFragment();
        }
    }


    @Override
    public void clearStack() {
        clearbackStack();
    }

    @Override
    public void popBack() {
        onBackPressed();
    }

    @Override
    public void removeLast() {
        popBackStack();
    }

    @Override
    public void removeFragmentsExceptsLast() {
        removeFragmentsExeptLast();
    }

    @Override
    public void clearStackUntil(String tag) {
        removeFragmentsUntil(tag);
    }

    @Override
    public void navigateToChooseModeFragment() {
        replaceFragment(ChooseModeFragment.newInstance(), FragmentAnimation.ANIMATION, false);
    }

    @Override
    protected MainRouter routerInstance() {
        return this;
    }
}
