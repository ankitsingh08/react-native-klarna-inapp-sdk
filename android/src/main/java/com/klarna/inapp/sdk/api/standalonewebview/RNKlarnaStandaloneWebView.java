package com.klarna.inapp.sdk.api.standalonewebview;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactContext;
import com.klarna.mobile.sdk.api.KlarnaEventHandler;
import com.klarna.mobile.sdk.api.standalonewebview.KlarnaStandaloneWebView;
import com.klarna.mobile.sdk.api.standalonewebview.KlarnaStandaloneWebViewClient;

public class RNKlarnaStandaloneWebView extends KlarnaStandaloneWebView {
  private final KlarnaStandaloneWebViewClient webViewClient;
  private final KlarnaEventHandler eventHandler;

  public RNKlarnaStandaloneWebView(@NonNull ReactContext context, KlarnaStandaloneWebViewClient webViewClient, KlarnaEventHandler eventHandler) {
    super(context, null, 0, webViewClient, eventHandler);

    this.webViewClient = webViewClient;
    this.eventHandler = eventHandler;
  }
}
