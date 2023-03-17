package com.klarna.inapp.sdk.api.standalonewebview;

import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.EVENT_ON_KLARNA_MESSAGE;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.postEvent;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.klarna.mobile.sdk.KlarnaMobileSDKError;
import com.klarna.mobile.sdk.api.KlarnaEventHandler;
import com.klarna.mobile.sdk.api.KlarnaProductEvent;
import com.klarna.mobile.sdk.api.component.KlarnaComponent;

import java.util.HashMap;
import java.util.Map;

public class RNKlarnaStandaloneEventHandler implements KlarnaEventHandler {

  private final ReactContext reactContext;

  public RNKlarnaStandaloneEventHandler(ReactContext reactContext) {
    this.reactContext = reactContext;
  }

  @Override
  public void onError(@NonNull KlarnaComponent klarnaComponent, @NonNull KlarnaMobileSDKError klarnaMobileSDKError) {
    //Log.d("Error received");

    if (klarnaComponent instanceof RNKlarnaStandaloneWebView) {
    } else {
      //Log.w("RNKlarnaStandaloneWebView instance not found. The RNKlarnaStandaloneEventHandler instance can only work with this component.");
    }
  }

  @Override
  public void onEvent(@NonNull KlarnaComponent klarnaComponent, @NonNull KlarnaProductEvent klarnaProductEvent) {
    //Log.d("Event received");

    WritableMap eventParams = Arguments.createMap();
    eventParams.putString("action", klarnaProductEvent.getAction());
    eventParams.putMap("params", Arguments.makeNativeMap(klarnaProductEvent.getParams()));

    if (klarnaComponent instanceof RNKlarnaStandaloneWebView) {
      postEvent(reactContext, (RNKlarnaStandaloneWebView) klarnaComponent, EVENT_ON_KLARNA_MESSAGE, eventParams);
    } else {
      //Log.w("RNKlarnaStandaloneWebView instance not found. The RNKlarnaStandaloneEventHandler instance can only work with this component.");
    }
  }
}
