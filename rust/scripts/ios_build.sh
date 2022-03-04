#!/usr/bin/env bash
# 生成头文件
cbindgen src/lib.rs -l c > rustlib.h
# 生成二进制
cargo lipo --release
# run！
pushd ../ios
  pod install
popd
