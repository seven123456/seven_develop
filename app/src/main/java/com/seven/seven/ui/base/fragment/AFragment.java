package com.seven.seven.ui.base.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.seven.seven.R;
import com.seven.seven.common.Model.BannerInfos;
import com.seven.seven.common.Model.Infos;
import com.seven.seven.common.network.ApiRetrofit;
import com.seven.seven.common.network.ThreadSchedulersHelper;
import com.seven.seven.common.utils.ToastUtils;
import com.seven.seven.mvp.view.TestActivity3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created  on 2018-02-05.
 * author:seven
 * email:seven2016s@163.com
 */

public class AFragment extends BaseFragment {

    private Observable<List<Infos>> observable;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
       /* Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("");
                e.onComplete();
                e.onNext("我是在oncomplete之后的");
            }
        });*/
//        Observable<String> observable = Observable.just("我是用just发送的数据");
//        Observable<String> observable = Observable.fromArray("我是用array发送的数据");
        long a = 1000;
      /*  Observable.interval(1, TimeUnit.SECONDS).take(6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                *//*d.isDisposed();
                Log.d("AFragment--->", "onSubscribe===" + "我是未发送之前的");*//*
                        Log.d("AFragment--->", "onSubscribe===" + d.toString());
                    }

                    @Override
                    public void onNext(Long s) {
                        Log.d("AFragment--->", "onNext===" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("AFragment--->", "onError===" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("AFragment--->", "onComplete");
                    }
                });*/
       /* Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onComplete();
            }
        }).map(new Function<String, Float>() {
            @Override
            public Float apply(String integer) throws Exception {
                return Float.parseFloat(integer);
            }
        }).subscribe(new Consumer<Float>() {
            @Override
            public void accept(Float aFloat) throws Exception {
                Log.d("AFragment--->", "accept===" + aFloat);
            }
        });*/
        /*Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onComplete();
            }
        }).flatMap(new Function<String, ObservableSource<Float>>() {
            @Override
            public ObservableSource<Float> apply(String s) throws Exception {
                List<Float> list = new ArrayList<>();
                list.add(Float.valueOf(s));
                list.add(Float.valueOf(s));
                list.add(Float.valueOf(s));
                list.add(Float.valueOf(s));
                return Observable.fromIterable(list).delay(10,TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<Float>() {
            @Override
            public void accept(Float aFloat) throws Exception {
                Log.d("AFragment--->", "accept===" + aFloat);
            }
        });*/
        /*Observable.create(new )*/
        ApiRetrofit.getApiRetrofit().getApiServis().getInfos()
                .compose(ThreadSchedulersHelper.<Infos>rxSchedulers())
                .subscribe(new Consumer<Infos>() {
                    @Override
                    public void accept(Infos infos) throws Exception {
                        ToastUtils.showToast("onNext===" + infos.getData().toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showToast("throwable===" + throwable.getMessage());
                    }
                });
       /* new Observer<Infos>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Infos infos) {
                ToastUtils.showToast("onNext" + infos.getData().toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("AFragment--->", "onError===" + e.getMessage());
                ToastUtils.showToast(e.getMessage());
            }

            @Override
            public void onComplete() {
//                        ToastUtils.showToast("oncomplete");
            }
        }*/
       /* Observer<Long> stringObserver = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                *//*d.isDisposed();
                Log.d("AFragment--->", "onSubscribe===" + "我是未发送之前的");*//*
                Log.d("AFragment--->", "onSubscribe===" + d);
            }

            @Override
            public void onNext(Long s) {
                Log.d("AFragment--->", "onNext===" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("AFragment--->", "onError===" + e);
            }

            @Override
            public void onComplete() {
                Log.d("AFragment--->", "onComplete");
            }
        };
        observable.subscribe(stringObserver);*/
        TextView textView = rootView.findViewById(R.id.text);
        textView.setText("我是A");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),TestActivity3.class));
            }
        });
        /*Integer a = 5;
        Integer b = new Integer(5);
        String a1 = "a";
        String b1 = new String("a");
        a.equals(a1);
        Log.d("AFragment--->", (a == b) + "");
        Log.d("AFragment--->", (a.equals(b)) + "");*/
//     TextView textView = rootView.findViewById(R.id.text);
//     textView.setText("我是A");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment;
    }
}
