

import React, { Component } from 'react';
import {
  StyleSheet,
  Text,
  View,
} from 'react-native';

class App extends Component {
  render() {
    const items = Array(1).fill(0).map((_, index) => {
      return (
        <View style={styles.item} key={index} />
      );
    });
    return (
      <View
        style={styles.container}
      >
        {items}
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    flexWrap: 'wrap',
    backgroundColor: 'white',
    display: 'flex',
    flexDirection: 'row',
  },
  item: {
    width: 20,
    height: 20,
    backgroundColor: 'black',
    margin: 2,
  },
});

export default App;
