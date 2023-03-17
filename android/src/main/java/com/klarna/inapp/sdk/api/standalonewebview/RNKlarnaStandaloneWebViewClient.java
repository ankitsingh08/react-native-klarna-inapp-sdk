package com.klarna.inapp.sdk.api.standalonewebview;

import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.EVENT_ON_LOAD;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.EVENT_ON_LOAD_ERROR;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.EVENT_ON_PROGRESS_CHANGE;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.EVENT_ON_RENDER_PROCESS_GONE;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.ON_LOAD_STAGE_LOAD_ENDED;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.ON_LOAD_STAGE_LOAD_STARTED;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.createNavigationEventArgs;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.createWebViewStateArgs;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.postEvent;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.klarna.mobile.sdk.api.standalonewebview.KlarnaStandaloneWebView;
import com.klarna.mobile.sdk.api.standalonewebview.KlarnaStandaloneWebViewClient;

public class RNKlarnaStandaloneWebViewClient extends KlarnaStandaloneWebViewClient {

  private final ReactContext reactContext;

  public RNKlarnaStandaloneWebViewClient(ReactContext reactContext) {
    this.reactContext = reactContext;
  }

  @Override
  public void onPageFinished(@Nullable KlarnaStandaloneWebView klarnaStandaloneWebView, @Nullable String loadedUrl) {
    //Log.d("onPageFinished ");

    postEvent(reactContext, klarnaStandaloneWebView, EVENT_ON_LOAD,
      createNavigationEventArgs(klarnaStandaloneWebView, ON_LOAD_STAGE_LOAD_ENDED, loadedUrl));
  }

  @Override
  public void onPageStarted(@Nullable KlarnaStandaloneWebView klarnaStandaloneWebView, @Nullable String newUrl, @Nullable Bitmap bitmap) {
    //Log.d("onPageStarted");

    postEvent(reactContext, klarnaStandaloneWebView, EVENT_ON_LOAD,
      createNavigationEventArgs(klarnaStandaloneWebView, ON_LOAD_STAGE_LOAD_STARTED, newUrl));
  }

  @Override
  public void onProgressChanged(@Nullable KlarnaStandaloneWebView klarnaStandaloneWebView, int i) {
    //Log.d("onProgressChanged");

    WritableMap eventArguments = Arguments.createMap();
    eventArguments.putMap("webViewState", createWebViewStateArgs(klarnaStandaloneWebView));

    postEvent(reactContext, klarnaStandaloneWebView, EVENT_ON_PROGRESS_CHANGE, eventArguments);
  }

  @Override
  public void onReceivedError(@Nullable KlarnaStandaloneWebView klarnaStandaloneWebView, @Nullable WebResourceRequest webResourceRequest, @Nullable WebResourceError webResourceError) {
    postEvent(reactContext, klarnaStandaloneWebView, EVENT_ON_LOAD_ERROR, null);
  }

  @Override
  public void onReceivedError(@Nullable KlarnaStandaloneWebView klarnaStandaloneWebView, int i, @Nullable String s, @Nullable String s1) {
    //Log.d("onReceivedError");

    postEvent(reactContext, klarnaStandaloneWebView, EVENT_ON_LOAD_ERROR, null);
  }

  @Override
  public void onReceivedHttpError(@Nullable KlarnaStandaloneWebView klarnaStandaloneWebView, @Nullable WebResourceRequest webResourceRequest, @Nullable WebResourceResponse webResourceResponse) {
    //Log.d("onReceivedError");

    postEvent(reactContext, klarnaStandaloneWebView, EVENT_ON_LOAD_ERROR, null);
  }

  @Override
  public void onReceivedSslError(@Nullable KlarnaStandaloneWebView klarnaStandaloneWebView, @Nullable SslErrorHandler sslErrorHandler, @Nullable SslError sslError) {
    //Log.d("onReceivedSslError");

    postEvent(reactContext, klarnaStandaloneWebView, EVENT_ON_LOAD_ERROR, null);
  }

  @Override
  public void onRenderProcessGone(@Nullable KlarnaStandaloneWebView klarnaStandaloneWebView, @Nullable RenderProcessGoneDetail detail) {
    //Log.d("onRenderProcessGone");

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
      String message = "onRenderProcessGone: no details";

      WritableMap eventArguments = Arguments.createMap();
      eventArguments.putString("message", message);
      postEvent(reactContext, klarnaStandaloneWebView, EVENT_ON_RENDER_PROCESS_GONE, eventArguments);
      return;
    }

    String message = "onRenderProcessGone: didCrash=" + detail.didCrash()
      + " killed=" + !detail.didCrash()
      + ": rendererPriorityAtExit : " + detail.rendererPriorityAtExit();

    WritableMap eventArguments = Arguments.createMap();
    eventArguments.putString("message", message);
    postEvent(reactContext, klarnaStandaloneWebView, EVENT_ON_RENDER_PROCESS_GONE, eventArguments);
  }
}
