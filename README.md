# Rust to All

一个用于尝试将 Rust 带入到各个平台的 Playground。

## Android 环境准备

```shell
# 只安装比较常用的两个 target
rutup target add aarch64-linux-android x86_64-linux-android
# 用于生成符合 ndk 标准的 so
cargo install cargo-ndk
```



## iOS

环境准备：

```shell
# 只安装比较常用的两个 target
rustup target add aarch64-apple-ios x86_64-apple-ios
# 用于生成 universal binary
cargo install cargo-lipo
# 用于自动生成头文件
cargo install cbindgen
```

编译构建：

```shell
# 生成头文件
cbindgen src/lib.rs -l c > rustylib.h
# 生成二进制
cargo lipo --release
# run！
cd ../ios && pod install && xc
```
