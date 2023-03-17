import { Ref } from 'react'
import { HostComponent } from 'react-native'

export interface RNKlarnaWebViewState {
    url: string
    title: string
    progress: number
}

export interface RNKlarnaWebViewRenderProcessGoneEvent {
    nativeEvent?: {
        message?: string
    }
}

export interface RNKlarnaWebViewNavigationError {
    nativeEvent?: null
}

export interface RNKlarnaWebViewNavigationEvent {
    nativeEvent?: {
        event: 'willLoad' | 'loadStarted' | 'loadEnded'
        newUrl: string
        webViewState: RNKlarnaWebViewState
    }
}

export interface RNKlarnaWebViewProgressEvent {
    nativeEvent?: {
        webViewState: RNKlarnaWebViewState
    }
}
export interface RNKlarnaWebViewKlarnaMessageEvent {
    nativeEvent?: {
        action: string
        params?: { [key: string]: any }
    }
}
export interface RNKlarnaWebViewProps {
    ref?: Ref<any>
    returnUrl: string
    style?: Record<string, any>
    onBeforeLoad?: (event: RNKlarnaWebViewNavigationEvent) => void
    onLoad?: (event: RNKlarnaWebViewNavigationEvent) => void
    onLoadError?: (event: RNKlarnaWebViewNavigationError) => void
    onProgressChange?: (event: RNKlarnaWebViewProgressEvent) => void
    onKlarnaMessage?: (event: RNKlarnaWebViewKlarnaMessageEvent) => void
    onRenderProcessGone?: (event: RNKlarnaWebViewRenderProcessGoneEvent) => void
}

export type RNKlarnaWebViewType = HostComponent<RNKlarnaWebViewProps>

export interface RNKlarnaWebViewNativeCommands {
    load: (viewRef: React.ElementRef<RNKlarnaWebViewType>, url?: string) => void
    goForward: (viewRef: React.ElementRef<RNKlarnaWebViewType>) => void
    goBack: (viewRef: React.ElementRef<RNKlarnaWebViewType>) => void
    reload: (viewRef: React.ElementRef<RNKlarnaWebViewType>) => void
}