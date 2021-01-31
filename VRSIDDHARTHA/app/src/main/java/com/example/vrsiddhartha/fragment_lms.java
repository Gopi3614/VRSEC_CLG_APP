package com.example.vrsiddhartha;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.FloatRange;
import androidx.fragment.app.Fragment;

import static android.content.Context.DOWNLOAD_SERVICE;

public class fragment_lms extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View myView = inflater.inflate(R.layout.fragment_lms,container,false);
        WebView mywebView = myView.findViewById(R.id.webview1);
        WebSettings webSettings = mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mywebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mywebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mywebView.getSettings().setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);
        //mywebView.getSettings().setAllowFileAccess(true);
        mywebView.getSettings().setUseWideViewPort(true);
        mywebView.loadUrl("https://lms.vrsiddhartha.ac.in/");
        mywebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String url2 = "https://lms.vrsiddhartha.ac.in/";
                if(url!=null && url.startsWith(url2)){
                    // open link in external browser to download the pdf
                    return false;
                }
                else{
                    view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                }
            }
        });
        mywebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                DownloadManager.Request myRequest = new DownloadManager.Request(Uri.parse(url));
                myRequest.allowScanningByMediaScanner();
                myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                DownloadManager Mymanager = (DownloadManager)getContext().getSystemService(DOWNLOAD_SERVICE);
                Mymanager.enqueue(myRequest);
                Toast.makeText(getContext(),"File is Downloading...",Toast.LENGTH_LONG).show();
            }
        });
        mywebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_BACK){
                        if(mywebView.canGoBack()){
                            mywebView.goBack();
                        }
                        else{
                            getActivity().onBackPressed();
                        }
                    }
                }
                return true;
            }
        });
        return myView;
    }
}
