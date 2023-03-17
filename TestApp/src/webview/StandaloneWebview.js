import React from 'react';
import {ScrollView, Text, useColorScheme, View} from 'react-native';
import styles, {backgroundStyle, Colors} from '../Styles';
import testProps from '../TestProps';
import {RNKlarnaWebView} from "./RNKlarnaWebView";

const StandAloneWebview = ({navigation}) => {
  const isDarkMode = useColorScheme() === 'dark';

  return (
    <ScrollView
      contentInsetAdjustmentBehavior="automatic"
      style={backgroundStyle(isDarkMode)}>
      <View
        style={{
          backgroundColor: isDarkMode ? Colors.black : Colors.white,
        }}>
        <RNKlarnaWebView
          
        /> 
      </View>
    </ScrollView>
  );
};

export default StandAloneWebview;
