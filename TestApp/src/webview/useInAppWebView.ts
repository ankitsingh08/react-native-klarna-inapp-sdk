import { MutableRefObject, useRef, useState } from 'react'

import {
  RNKlarnaWebViewNavigationEvent,
  RNKlarnaWebViewRenderProcessGoneEvent,
} from './types'

import { RNKlarnaWebViewCommands } from './RNKlarnaWebView'

const LOAD_TIMEOUT = 30 * 1000 // 30 seconds

export const useInAppWebView = () => {
  const [webViewHasError, setWebViewHasError] = useState<boolean>(false)
  //const loadWebViewTimeout = useRef<TimeoutId | null>(null)

  const load = (webViewRef: MutableRefObject<any>, url?: string) => {
    if (webViewRef.current) {
      RNKlarnaWebViewCommands.load(webViewRef.current, url)
    }
  }

  const goForward = (webViewRef: MutableRefObject<any>) => {
    if (webViewRef.current) {
      RNKlarnaWebViewCommands.goForward(webViewRef.current)
    }
  }

  const goBack = (webViewRef: MutableRefObject<any>) => {
    if (webViewRef.current) {
      RNKlarnaWebViewCommands.goBack(webViewRef.current)
    }
  }

  const reload = (webViewRef: MutableRefObject<any>) => {
    if (webViewRef.current) {
      RNKlarnaWebViewCommands.reload(webViewRef.current)
    }
  }

  const onBeforeLoad = (event: RNKlarnaWebViewNavigationEvent) => {
    // logEvent(webViewPageLoadInitiated)

    // const loadWebViewTimeoutId: TimeoutId = setTimeout(() => {
    //   setWebViewHasError(true)
    //   logEvent(webViewPageLoadTimedOut)
    //   logEvent(webViewPageLoadFailed)
    // }, LOAD_TIMEOUT)

    // loadWebViewTimeout.current = loadWebViewTimeoutId
  }

  const onRenderProcessGone = (event: RNKlarnaWebViewRenderProcessGoneEvent) => {
    // logEvent(webViewPageLoadFailed)

    // captureMessage(event.nativeEvent?.message ?? 'onRenderProcessGone: no details', {
    //   level: 'error',
    //   tags: { feature: 'onePurchaseFlow' },
    //   fingerprint: ['App Handover load page', 'error'],
    // })
  }

  const onLoadedMessage = () => {
    // if (loadWebViewTimeout.current) {
    //   clearTimeout(loadWebViewTimeout.current)
    // }
  }

  return {
    load,
    goForward,
    goBack,
    reload,
    onBeforeLoad,
    onRenderProcessGone,
    onLoadedMessage,
    webViewHasError,
  }
}
