package com.example.blockchaintracker.presentation.police;

import com.example.blockchaintracker.api.dto.AddBlockRq;
import com.example.blockchaintracker.api.dto.EmptyRs;
import com.example.blockchaintracker.api.dto.TestRs;
import com.example.blockchaintracker.api.requests.AddBlock;
import com.example.blockchaintracker.api.requests.GetTest;
import com.example.blockchaintracker.core.presenter.BasePresenter;
import com.example.blockchaintracker.core.presenter.EmptyState;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Роман on 15.05.2018.
 */

public class PolicePresenter extends BasePresenter<PoliceView, EmptyState> {

    private GetTest getTest;
    private AddBlock addBlock;

    @Override
    public void viewCreated() {
        super.viewCreated();
        getTest = new GetTest(view().context());
        addBlock = new AddBlock(view().context());
    }

    @Override
    public void destroyView() {
        super.destroyView();
        getTest.dispose();
    }

    public void check() {
        view().showLoading();
//        getTest.execute(new GetTestObserver(), null);

        AddBlockRq request = new AddBlockRq(1234, "sdgsv", "asdad", "data", 5);
        addBlock.execute(new AddBlockObserver(), AddBlock.Params.forQuery(request));
    }

    private class GetTestObserver extends DisposableObserver<TestRs> {

        @Override
        public void onNext(TestRs response) {
            if (view() != null) {
                view().setResult(response.getId()+" "+response.getNonce()+" "+response.getInfo());
            }
        }

        @Override
        public void onError(Throwable e) {
            if (view() != null) {
                view().showError(e.getMessage());
                view().hideLoading();
            }
        }

        @Override
        public void onComplete() {
            if (view() != null) {
                view().hideLoading();
            }
        }
    }

    private class AddBlockObserver extends DisposableObserver<EmptyRs> {

        @Override
        public void onNext(EmptyRs response) {
            if (view() != null) {
                view().setResult("OK");
            }
        }

        @Override
        public void onError(Throwable e) {
            if (view() != null) {
                view().showError(e.getMessage());
                view().hideLoading();
            }
        }

        @Override
        public void onComplete() {
            if (view() != null) {
                view().hideLoading();
            }
        }
    }
}
