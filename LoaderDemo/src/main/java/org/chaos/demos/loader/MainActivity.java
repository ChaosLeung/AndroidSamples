package org.chaos.demos.loader;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LoaderDemos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Data>() {
            @Override
            public Loader<Data> onCreateLoader(int id, Bundle args) {
                return new SimpleLoader(getApplicationContext());
            }

            @Override
            public void onLoadFinished(Loader<Data> loader, Data data) {
                Log.d(TAG, "onLoadFinished() called with: " + "loader = [" + loader + "], data = [" + data + "]");

                getSupportLoaderManager().destroyLoader(0);
                getSupportLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Data2>() {
                    @Override
                    public Loader<Data2> onCreateLoader(int id, Bundle args) {
                        return new SimpleLoader2(getApplicationContext());
                    }

                    @Override
                    public void onLoadFinished(Loader<Data2> loader, Data2 data) {
                        Log.d(TAG, "onLoadFinished() called with: " + "loader2 = [" + loader + "], data2 = [" + data + "]");
                    }

                    @Override
                    public void onLoaderReset(Loader<Data2> loader) {
                        Log.d(TAG, "onLoaderReset() called with: " + "loader2 = [" + loader + "]");
                    }
                });
            }

            @Override
            public void onLoaderReset(Loader<Data> loader) {
                Log.d(TAG, "onLoaderReset() called with: " + "loader = [" + loader + "]");
            }
        });
    }

    private static class SimpleLoader extends Loader<Data>
            implements Loader.OnLoadCanceledListener<Data>, Loader.OnLoadCompleteListener<Data> {

        public SimpleLoader(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            Log.d(TAG, "onStartLoading() called with: " + "");
            deliverResult(new Data(1));
            Log.d(TAG, "onStartLoading: " + (Looper.myLooper() == Looper.getMainLooper()));
        }

        @Override
        protected boolean onCancelLoad() {
            return super.onCancelLoad();
        }

        @Override
        protected void onForceLoad() {
            Log.d(TAG, "onForceLoad() called with: " + "");
        }

        @Override
        protected void onStopLoading() {
            Log.d(TAG, "onStopLoading() called with: " + "");
        }

        @Override
        protected void onAbandon() {
            Log.d(TAG, "onAbandon() called with: " + "");
        }

        @Override
        protected void onReset() {
            Log.d(TAG, "onReset() called with: " + "");
        }

        @Override
        public void onContentChanged() {
            Log.d(TAG, "onContentChanged() called with: " + "");
        }

        @Override
        public void onLoadCanceled(Loader<Data> loader) {
            Log.d(TAG, "onLoadCanceled() called with: ");
        }

        @Override
        public void onLoadComplete(Loader<Data> loader, Data data) {
            Log.d(TAG, "onLoadComplete() called with: " + "data = [" + data + "]");
        }
    }

    private static class Data {
        private int length;

        public Data(int length) {
            this.length = length;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "length=" + length +
                    '}';
        }
    }

    private static class SimpleLoader2 extends Loader<Data2>
            implements Loader.OnLoadCanceledListener<Data2>, Loader.OnLoadCompleteListener<Data2> {

        public SimpleLoader2(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            Log.d(TAG, "onStartLoading() called with: " + "");
            deliverResult(new Data2(1));
            Log.d(TAG, "onStartLoading: " + (Looper.myLooper() == Looper.getMainLooper()));
        }

        @Override
        protected boolean onCancelLoad() {
            return super.onCancelLoad();
        }

        @Override
        protected void onForceLoad() {
            Log.d(TAG, "onForceLoad() called with: " + "");
        }

        @Override
        protected void onStopLoading() {
            Log.d(TAG, "onStopLoading() called with: " + "");
        }

        @Override
        protected void onAbandon() {
            Log.d(TAG, "onAbandon() called with: " + "");
        }

        @Override
        protected void onReset() {
            Log.d(TAG, "onReset() called with: " + "");
        }

        @Override
        public void onContentChanged() {
            Log.d(TAG, "onContentChanged() called with: " + "");
        }

        @Override
        public void onLoadCanceled(Loader<Data2> loader) {
            Log.d(TAG, "onLoadCanceled() called with: ");
        }

        @Override
        public void onLoadComplete(Loader<Data2> loader, Data2 data) {
            Log.d(TAG, "onLoadComplete() called with: " + "data = [" + data + "]");
        }
    }

    private static class Data2 {
        private int length;

        public Data2(int length) {
            this.length = length;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "length=" + length +
                    '}';
        }
    }
}
