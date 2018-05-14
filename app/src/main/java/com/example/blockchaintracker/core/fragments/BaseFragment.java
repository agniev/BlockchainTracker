package com.example.blockchaintracker.core.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.blockchaintracker.core.activities.BaseActivity;
import com.example.blockchaintracker.core.presenter.BasePresenter;
import com.example.blockchaintracker.core.router.BaseRouter;
import com.example.blockchaintracker.core.view.BaseView;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<PRESENTER extends BasePresenter, ROUTER extends BaseRouter> extends Fragment
        implements BaseView {

    private PRESENTER presenter;
    private ROUTER router;

    private Unbinder unbinder;
    private Toast toast;

    public void onFragmentResume() {
        resume();
        setToolbar(getBaseActivity().getSupportActionBar(), getBaseActivity().getToolbar());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        router = getBaseActivity().router();
        presenter = newPresenter();
        create(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        if (savedInstanceState != null) {
            setToolbar(getBaseActivity().getSupportActionBar(), getBaseActivity().getToolbar());
            presenter().restoreState(savedInstanceState.getParcelable("STATE"));
        }

        presenter.bind(this);
        presenter.viewCreated();

        created(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("STATE", presenter().state());
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroy();
        presenter.destroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public PRESENTER newPresenter() {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class type = (Class) superClass.getActualTypeArguments()[0];
        try {
            return (PRESENTER) type.newInstance();
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            throw new Error("Error creating PRESENTER instance of " + type.getSimpleName() + "\n=======================\n" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public BaseActivity<ROUTER> getBaseActivity() {
        return (BaseActivity<ROUTER>) getActivity();
    }

    public void showToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
            toast.show();
        } else {
            toast.setText(message);
            toast.show();
        }
    }

    public void showToast(int message) {
        if (toast == null) {
            toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
            toast.show();
        } else {
            toast.setText(message);
            toast.show();
        }
    }

    public PRESENTER presenter() {
        return presenter;
    }

    public ROUTER router() {
        return router;
    }

    /*** BaseView method: show's default window with progress ***/
    @Override
    public void showLoading() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showProgress();
    }

    /*** BaseView method: show's default window with progress and custom message ***/
    @Override
    public void showLoading(String message) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showProgress(message);
    }

    /*** BaseView method: hide's default window with progress ***/
    @Override
    public void hideLoading() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).hideProgress();
    }

    /*** BaseView method: return's BaseActivity context ***/
    @Override
    public BaseActivity context() {
        return getBaseActivity();
    }

    /*** BaseView method: show's Toast with message ***/
    @Override
    public void showError(String message) {
        showToast(message);
    }

    @LayoutRes
    protected abstract int layoutResId();

    protected abstract void setToolbar(ActionBar actionBar, Toolbar toolbar);

    protected abstract void create(@Nullable Bundle savedInstanceState);

    protected abstract void created(View view, Bundle savedInstanceState);

    protected abstract void resume();

    protected abstract void destroy();
}

