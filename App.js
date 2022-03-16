

import React, { Component } from 'react';
import {
  StyleSheet,
  Text,
  View,
} from 'react-native';

class App extends Component {
  render() {
    return (
      <View
        style={styles.item}
      >
        <Text>test</Text>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  item: {
    width: 100,
    height: 100,
    backgroundColor: 'red',
  },
});

export default App;
