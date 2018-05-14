package com.example.blockchaintracker.core.presenter;


import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.example.blockchaintracker.core.utils.SPWrapper;
import com.example.blockchaintracker.core.view.BaseView;

import java.lang.reflect.ParameterizedType;

public abstract class BasePresenter<VIEW extends BaseView, STATE extends Parcelable> implements PresenterLifecycle {

    private SPWrapper spWrapper = SPWrapper.INSTANCE;
    private VIEW view;
    private STATE state = newState();

    @SuppressWarnings("unchecked")
    public void bind(BaseView view) {
        this.view = (VIEW) view;
    }

    @Override
    public void viewCreated() {

    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroyView() {
        view = null;
    }

    @Override
    public void destroy() {

    }

    public VIEW view() {
        return view;
    }

    public STATE state() {
        return state;
    }

    public void restoreState(STATE state) {
        this.state = state;
    }

    protected SPWrapper spWrapper() {
        return spWrapper;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    private STATE newState() {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class type = (Class) superClass.getActualTypeArguments()[1];
        try {
            return (STATE) type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new Error("Error creating STATE instance of " + type.getSimpleName() + "\n=======================\n" + e.getMessage());
        }
    }
}
