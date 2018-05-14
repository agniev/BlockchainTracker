package com.example.blockchaintracker.core.view;


import com.example.blockchaintracker.core.activities.BaseActivity;

public interface BaseView {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Show a view with a progress bar indicating a loading process with custom message.
     */
    void showLoading(String message);

    /**
     * Hide a loading view.
     */
    void hideLoading();
    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showError(String message);

    BaseActivity context();
}
