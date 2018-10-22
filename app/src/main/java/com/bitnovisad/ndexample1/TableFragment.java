package com.bitnovisad.ndexample1;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class TableFragment extends Fragment {

    protected boolean isNetworkConnected() {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return (mNetworkInfo == null) ? false : true;

        }catch (NullPointerException e){
            return false;

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //toast message to handle if there is no internet connection
        if(isNetworkConnected() == false){
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();

        }

        //inflate layout for fragment
        View v = inflater.inflate(R.layout.table_fragment, container, false);

        final ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        final WebView webWiev = (WebView) v.findViewById(R.id.tableWebView);
        webWiev.getSettings().setJavaScriptEnabled(true);
        webWiev.setWebViewClient(new WebViewClient(){
            //start progress bar when web page start loading
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }
            //end progress bar when page i fully loaded
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

        });

        //implements go back functionality on webview web pages
        webWiev.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && webWiev.canGoBack())
                {
                    webWiev.goBack();
                    return true;
                }
                return false;
            }
        });

        //load desired web page
        webWiev.loadUrl("https://www.smeh.rs/");
        //webWiev.loadUrl("https://www.google.rs/search?biw=1366&bih=657&ei=z3rNW5HsCsu3swHbkZuoCA&q=FK+Cement+Beo%C4%8Din+tabela&oq=FK+Cement+Beo%C4%8Din+tabela&gs_l=psy-ab.3..0i22i30k1.38077.39937.0.41172.7.7.0.0.0.0.106.591.6j1.7.0....0...1c.1.64.psy-ab..0.7.588...38j35i39k1.0.VsBimJtErAU#sie=t;/m/02x2lm8;2;/m/02vyxk8;mt;fp;1");

        return v;
    }
}
