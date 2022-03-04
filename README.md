# Rust to All

一个用于尝试将 Rust 带入到各个平台的 Playground。

## Android

环境准备：

```shell
# 只安装比较常用的两个 target
rutup target add aarch64-linux-android x86_64-linux-android
# 用于生成符合 ndk 标准的 so
cargo install cargo-ndk
```

编译构建：

```shell
./scripts/ios_build.sh
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
./scripts/ios_build.sh
```
