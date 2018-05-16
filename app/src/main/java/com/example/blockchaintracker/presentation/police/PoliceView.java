package com.example.blockchaintracker.presentation.police;

import com.example.blockchaintracker.core.view.BaseView;

public interface PoliceView extends BaseView {

    void setResult(boolean isGuilty);

    void setResultVisibility(boolean isVisible);
}
