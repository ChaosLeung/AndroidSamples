package org.chaos.demos.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Timestamped;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private final ArrayList<String> mUrls = new ArrayList<>();

    {
        for (int i = 0; i <= 50; i++) {
            mUrls.add("url" + i);
            mUrls.add("url" + i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Observable<CharSequence> observable = Observable.create(new Observable.OnSubscribe<CharSequence>() {
//            @Override
//            public void call(Subscriber<? super CharSequence> subscriber) {
//                subscriber.onNext("嘿嘿嘿");
//                subscriber.onCompleted();
////                subscriber.onError(new NullPointerException("nothing"));
//            }
//        });
//        Subscriber<String> subscriber = new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                Log.d(TAG, "onCompleted() called");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError() called with: e = [" + e + "]");
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d(TAG, "onNext() called with: s = [" + s + "]");
//            }
//
//            @Override
//            public void onStart() {
//                Log.d(TAG, "onStart() called");
//            }
//        };
//        observable.map(new Func1<CharSequence, String>() {
//            @Override
//            public String call(CharSequence charSequence) {
//                return charSequence.toString();
//            }
//        }).subscribe(subscriber);

//        Observable.just("嘿嘿嘿")
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        return "pre-" + s;
//                    }
//                })
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Log.d(TAG, "call() called with: s = [" + s + "]");
//                    }
//                });

        Observable.just(mUrls)
                .flatMap(new Func1<ArrayList<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(ArrayList<String> strings) {
                        return Observable.from(strings);
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !s.equals("url1");
                    }
                })
                .take(10)
                .takeLast(5)
                .distinct()
                .debounce(1000, TimeUnit.MILLISECONDS)
//                .elementAt(2)
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "doOnNext#call() called in doOnNex : s = [" + s + "]");
                    }
                })
                .repeat(2)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "call() called with: s = [" + s + "]");
                    }
                });

        Observable.range(1, 20).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer s) {
                Log.d(TAG, "call() called with: s = [" + s + "]");
            }
        });

        Observable.range(1, 20).buffer(2, 1).map(new Func1<List<Integer>, Integer>() {
            @Override
            public Integer call(List<Integer> integers) {
                if (integers.size() > 1)
                    return integers.get(0) + integers.get(1);
                else
                    return integers.get(0);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call() called with: integer = [" + integer + "]");
            }
        });

        Observable.range(1, 200)
                .scan(2L, new Func2<Long, Integer, Long>() {
                    @Override
                    public Long call(Long aLong, Integer integer) {
                        return aLong + integer;
                    }
                })
//                .ignoreElements()
//                .sample(2, TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted() called");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError() called with: e = [" + e + "]");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "onNext() called with: aLong = [" + aLong + "]");
                    }
                });

        Observable.range(1, 20)
                .timestamp()
                .subscribe(new Action1<Timestamped<Integer>>() {
                    @Override
                    public void call(Timestamped<Integer> integerTimeInterval) {
                        Log.d(TAG, "call() called with: integerTimeInterval = [" + integerTimeInterval.getTimestampMillis() + "]");
                    }
                });

        Observable.zip(Observable.just("url1", "url2", "url3", "url4").map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return s;
                    }
                }),
                Observable.just(1, 2, 3, 4, 5),
                new Func2<String, Integer, String>() {
                    @Override
                    public String call(String s, Integer integer) {
                        return integer + s;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, "zip#call() called with: s = [" + s + "]");
                    }
                });
    }
}
