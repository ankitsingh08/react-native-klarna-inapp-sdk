package com.klarna.inapp.sdk.api.standalonewebview;

import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.EVENT_ON_BEFORE_LOAD;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.EVENT_ON_KLARNA_MESSAGE;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.EVENT_ON_LOAD;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.EVENT_ON_LOAD_ERROR;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.EVENT_ON_PROGRESS_CHANGE;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.EVENT_ON_RENDER_PROCESS_GONE;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.ON_LOAD_STAGE_WILL_LOAD;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.createNavigationEventArgs;
import static com.klarna.inapp.sdk.api.standalonewebview.RNKlarnaStandaloneWebViewEventEmitter.postEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;
import java.util.Objects;

public class RNKlarnaStandaloneWebViewManager extends SimpleViewManager<RNKlarnaStandaloneWebView> {

    public static final String COMMAND_RELOAD = "reload";
    public static final String COMMAND_GO_FORWARD = "goForward";
    public static final String COMMAND_GO_BACK = "goBack";
    public static final String COMMAND_LOAD = "load";

    public static final String REACT_CLASS = "RNKlarnaStandaloneWebView";

    private final ReactApplicationContext reactAppContext;

    public RNKlarnaStandaloneWebViewManager(ReactApplicationContext reactApplicationContext) {
        this.reactAppContext = reactApplicationContext;
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected RNKlarnaStandaloneWebView createViewInstance(@NonNull ThemedReactContext reactContext) {

        final RNKlarnaStandaloneWebViewClient webViewClient = new RNKlarnaStandaloneWebViewClient(reactAppContext);
        final RNKlarnaStandaloneEventHandler eventHandler = new RNKlarnaStandaloneEventHandler(reactAppContext);

        final RNKlarnaStandaloneWebView standaloneWebView = new RNKlarnaStandaloneWebView(reactAppContext,
        webViewClient, eventHandler);

        return standaloneWebView;
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
            EVENT_ON_BEFORE_LOAD, MapBuilder.of("registrationName", EVENT_ON_BEFORE_LOAD),
            EVENT_ON_LOAD, MapBuilder.of("registrationName", EVENT_ON_LOAD),
            EVENT_ON_LOAD_ERROR, MapBuilder.of("registrationName", EVENT_ON_LOAD_ERROR),
            EVENT_ON_PROGRESS_CHANGE, MapBuilder.of("registrationName", EVENT_ON_PROGRESS_CHANGE),
            EVENT_ON_KLARNA_MESSAGE, MapBuilder.of("registrationName", EVENT_ON_KLARNA_MESSAGE),
            EVENT_ON_RENDER_PROCESS_GONE, MapBuilder.of("registrationName", EVENT_ON_RENDER_PROCESS_GONE)
        );
    }

    @ReactProp(name = "returnUrl")
    public void setReturnUrl(RNKlarnaStandaloneWebView standaloneWebView, String returnUrl) {
        standaloneWebView.setReturnURL(returnUrl);
    }

    @Override
    public void receiveCommand(@NonNull RNKlarnaStandaloneWebView standaloneWebView, String commandId, @Nullable ReadableArray args) {
        //Log.d("Received command : " + commandId + " ");

        if (Objects.equals(commandId, COMMAND_LOAD)) {
            receivedLoadCommand(standaloneWebView, args);
        }

        if (Objects.equals(commandId, COMMAND_RELOAD)) {
            standaloneWebView.reload();
        }

        if (Objects.equals(commandId, COMMAND_GO_BACK)) {
            standaloneWebView.goBack();
        }

        if (Objects.equals(commandId, COMMAND_GO_FORWARD)) {
            standaloneWebView.goForward();
        }

    }

    private void receivedLoadCommand(RNKlarnaStandaloneWebView standaloneWebView, ReadableArray args) {
        final String url = args.getString(0);
        if (url != null) {
            //send event 'onBeforeLoad'
            postEvent(reactAppContext, standaloneWebView, EVENT_ON_BEFORE_LOAD,
                createNavigationEventArgs(standaloneWebView, ON_LOAD_STAGE_WILL_LOAD, url));

            //load
            standaloneWebView.loadUrl(url, null);
        }
    }
}
