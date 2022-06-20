import { NativeModules } from 'react-native';

const { OpenDefaultBrowser } = NativeModules;

export function openBrowser() {
  return OpenDefaultBrowser.openBrowser();
}
