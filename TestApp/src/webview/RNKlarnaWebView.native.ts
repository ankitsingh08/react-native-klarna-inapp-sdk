import { requireNativeComponent } from 'react-native'

// eslint-disable-next-line import/default
import codegenNativeCommands from 'react-native/Libraries/Utilities/codegenNativeCommands'

import { RNKlarnaWebViewNativeCommands, RNKlarnaWebViewType } from './types'

export const RNKlarnaWebView: RNKlarnaWebViewType = requireNativeComponent(
  'RNKlarnaStandaloneWebView'
)

export const RNKlarnaWebViewCommands = codegenNativeCommands<RNKlarnaWebViewNativeCommands>({
  supportedCommands: ['load', 'goForward', 'goBack', 'reload'],
})