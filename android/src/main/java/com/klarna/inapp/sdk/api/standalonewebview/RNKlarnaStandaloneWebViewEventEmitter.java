package com.klarna.inapp.sdk.api.standalonewebview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.klarna.mobile.sdk.api.standalonewebview.KlarnaStandaloneWebView;

public class RNKlarnaStandaloneWebViewEventEmitter {

  public static final String EVENT_ON_BEFORE_LOAD = "onBeforeLoad";
  public static final String EVENT_ON_LOAD = "onLoad";
  public static final String EVENT_ON_LOAD_ERROR = "onLoadError";
  public static final String EVENT_ON_PROGRESS_CHANGE = "onProgressChange";
  public static final String EVENT_ON_KLARNA_MESSAGE = "onKlarnaMessage";
  public static final String EVENT_ON_RENDER_PROCESS_GONE = "onRenderProcessGone";

  public static final String ON_LOAD_STAGE_WILL_LOAD = "willLoad";
  public static final String ON_LOAD_STAGE_LOAD_STARTED = "loadStarted";
  public static final String ON_LOAD_STAGE_LOAD_ENDED = "loadEnded";


  public static void postEvent(ReactContext reactContext, KlarnaStandaloneWebView standaloneWebView,
                         @NonNull String eventName, @Nullable WritableMap additionalParams) {
    //Log.d("Post event " + eventName);

    if (standaloneWebView != null) {

      RCTEventEmitter eventEmitter = reactContext.getJSModule(RCTEventEmitter.class);
      if (eventEmitter != null) {
        eventEmitter.receiveEvent(standaloneWebView.getId(), eventName, additionalParams);
      }
    }
  }

  public static WritableMap createNavigationEventArgs(KlarnaStandaloneWebView standaloneWebView, String onLoadStage, String newUrl) {
    WritableMap eventArguments = Arguments.createMap();
    eventArguments.putString("event", onLoadStage);
    eventArguments.putString("newUrl", newUrl);
    eventArguments.putMap("webViewState", createWebViewStateArgs(standaloneWebView));
    return eventArguments;
  }

  public static WritableMap createWebViewStateArgs(KlarnaStandaloneWebView standaloneWebView) {
    WritableMap webViewStateMap = Arguments.createMap();
    webViewStateMap.putString("url", standaloneWebView.getUrl());
    webViewStateMap.putString("title", standaloneWebView.getTitle());
    webViewStateMap.putInt("progress", standaloneWebView.getProgress());
    return webViewStateMap;
  }
}
