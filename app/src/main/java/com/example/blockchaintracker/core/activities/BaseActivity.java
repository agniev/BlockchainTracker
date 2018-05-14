package com.example.blockchaintracker.core.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blockchaintracker.R;
import com.example.blockchaintracker.core.fragments.BaseFragment;
import com.example.blockchaintracker.core.router.BaseRouter;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity<ROUTER extends BaseRouter> extends AppCompatActivity {

    private static final int FRAGMENT_START_DELAY = 100;
    private static final int PROGRESS_SPINNER_DELAY = 300;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.progress)
    FrameLayout mProgress;
    @BindView(R.id.progress_text)
    TextView progressText;
    @BindView(R.id.progress_spinner)
    CardView progressSpinner;

    private AlphaAnimation progressFadeInAnimation = new AlphaAnimation(0f, 1f);
    private Handler mProgressHandler = new Handler(Looper.getMainLooper());
    private Handler mFragmentsHandler = new Handler(Looper.getMainLooper());
    private Runnable mProgressRunnable;

    protected Fragment mCurrentFragment;
    private FragmentManager manager;

    private Toast mServerErrorToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_layout);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        getSupportFragmentManager().addOnBackStackChangedListener(getListener());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                hideProgress();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public <T extends Fragment> void replaceFragment(T fragment, boolean withDelay) {
        String tag = fragment.getClass().getSimpleName();
        startFragment(fragment, true, tag, false, null, withDelay);
    }

    public <T extends Fragment> void replaceFragment(T fragment, FragmentAnimation anim, boolean withDelay) {
        String tag = fragment.getClass().getSimpleName();
        startFragment(fragment, true, tag, false, anim, withDelay);
    }

    public <T extends Fragment> void replaceFragment(T fragment, boolean addToStack, boolean withDelay) {
        String tag = fragment.getClass().getSimpleName();
        startFragment(fragment, addToStack, tag, false, null, withDelay);
    }

    public <T extends Fragment> void addFragment(T fragment, boolean withDelay) {
        String tag = fragment.getClass().getSimpleName();
        startFragment(fragment, true, tag, true, null, withDelay);
    }

    public <T extends Fragment> void addFragment(T fragment, FragmentAnimation anim, boolean withDelay) {
        String tag = fragment.getClass().getSimpleName();
        startFragment(fragment, true, tag, true, anim, withDelay);
    }

    public <T extends Fragment> void startFragment(final T fragment, boolean addToStack, String tag, boolean shouldAdd, FragmentAnimation anim, boolean withDelay) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (anim != null) {
            transaction.setCustomAnimations(
                    anim.getInAnimation1(), anim.getInAnimation2(),
                    anim.getOutAnimation1(), anim.getOutAnimation2());
        }

        if (shouldAdd) {
            if (tag == null) {
                transaction.add(R.id.core_content_frame, fragment);
            } else {
                transaction.add(R.id.core_content_frame, fragment, tag);
            }
        } else {
            if (tag == null) {
                transaction.replace(R.id.core_content_frame, fragment);
            } else {
                transaction.replace(R.id.core_content_frame, fragment, tag);
            }
        }

        if (addToStack) {
            transaction.addToBackStack(tag);
        }

        if (withDelay) {
            hideKeyboard();
            mFragmentsHandler.postDelayed(() -> {
                transaction.commitAllowingStateLoss();

                mCurrentFragment = fragment;
            }, FRAGMENT_START_DELAY);
        } else {
            transaction.commitAllowingStateLoss();

            mCurrentFragment = fragment;
        }
    }

    public <T extends Fragment> void startFragment(T fragment, boolean withDelay) {
        String tag = fragment.getClass().getSimpleName();
        startFragment(fragment, true, tag, false, null, withDelay);
    }

    public <T extends Fragment> void startFragment(T fragment, boolean addToBackStack, boolean withDelay) {
        String tag = fragment.getClass().getSimpleName();
        startFragment(fragment, addToBackStack, tag, false, null, withDelay);
    }

    public <T extends Fragment> void startFragment(T fragment, boolean addToBackStack, FragmentAnimation anim, boolean withDelay) {
        String tag = fragment.getClass().getSimpleName();
        startFragment(fragment, addToBackStack, tag, false, anim, withDelay);
    }

    public <T extends Fragment> void startFragment(T fragment, FragmentAnimation anim, boolean withDelay) {
        String tag = fragment.getClass().getSimpleName();
        startFragment(fragment, true, tag, false, anim, withDelay);
    }

    public void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    public void hideKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    private void clearBackStack() {
        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void removeFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commitNow();
        manager.popBackStack();
    }

    @Override
    public void onBackPressed() {
        hideProgress();
        hideKeyboard();
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public void showProgress(String progressText) {
        this.progressText.setText(progressText);
        runProgress();
    }

    public void showProgress() {
        this.progressText.setText(getString(R.string.please_wait));
        runProgress();
    }

    public void setProgressText(String progressText) {
        this.progressText.setText(progressText);
    }

    private void runProgress() {
        runOnUiThread(() -> {
            if (mProgress != null) {
                if (mProgressRunnable == null) {
                    mProgressRunnable = () -> progressSpinner.setVisibility(View.VISIBLE);
                }
                progressFadeInAnimation.setDuration(PROGRESS_SPINNER_DELAY);
                mProgress.setAnimation(progressFadeInAnimation);
                mProgress.setVisibility(View.VISIBLE);
                progressFadeInAnimation.start();
                mProgressHandler.postDelayed(mProgressRunnable, PROGRESS_SPINNER_DELAY);
            }
        });
    }

    public void hideProgress() {
        runOnUiThread(() -> {
            if (mProgress != null && isProgressShown()) {
                mProgress.setVisibility(View.GONE);
                progressSpinner.setVisibility(View.GONE);
                mProgressHandler.removeCallbacks(mProgressRunnable);
            }
        });
    }

    public boolean isProgressShown() {
        return mProgress.getVisibility() == View.VISIBLE;
    }

    public void removeFragmentsExeptLast() {
        int entryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (entryCount > 1)
            for (int i = 1; i < entryCount; i++) {
                removeFragment(getCurrentFragment());
            }
    }

    public void removeFragmentsUntil(String tag) {
        int entryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (entryCount > 1)
            for (int i = entryCount - 1; i > 1; i--) {
                Log.e("Tags fragments", getSupportFragmentManager().getBackStackEntryAt(i).getName() + " : " + tag);
                if (getSupportFragmentManager().getBackStackEntryAt(i).getName().equals(tag))
                    break;
                removeFragment(getCurrentFragment());
            }
    }

    public enum FragmentAnimation {
        ANIMATION;

        public int getInAnimation1() {
            return R.anim.fragment_slide_enter;
        }

        public int getInAnimation2() {
            return R.anim.fragment_slide_exit;
        }

        public int getOutAnimation1() {
            return R.anim.fragment_slide_pop_enter;
        }

        public int getOutAnimation2() {
            return R.anim.fragment_slide_pop_exit;
        }
    }

    public ROUTER router() {
        return routerInstance();
    }

    protected abstract ROUTER routerInstance();

    public void clearbackStack() {
        clearBackStack();
    }

    private FragmentManager.OnBackStackChangedListener getListener() {
        return () -> {
            manager = getSupportFragmentManager();

            if (manager != null) {
                Fragment currFrag = manager.findFragmentById(R.id.core_content_frame);
                if (currFrag instanceof BaseFragment) {
                    ((BaseFragment) currFrag).onFragmentResume();
                }
            }
        };
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.core_content_frame);
    }

    public void showToast(String text) {
        if (mServerErrorToast == null) {
            mServerErrorToast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        }
        mServerErrorToast.setText(text);
        mServerErrorToast.setDuration(Toast.LENGTH_LONG);
        this.runOnUiThread(() -> mServerErrorToast.show());
    }
}
