#!/usr/bin/env bash
# set the version to use the library
min_ver=21
jniLibs=../android/rusty/src/main/jniLibs

rm -rf ${jniLibs}
# verify before executing this that you have the proper targets installed
cargo ndk --target aarch64-linux-android --target x86_64-linux-android --platform ${min_ver} -o $jniLibs -- build 
