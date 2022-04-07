# Rust to All

一个用于尝试将 Rust 带入到各个平台的 Playground。

## How to Begin?

在根目录下执行（目前一部分代码依赖于 ReactNative，这一步是为了安装 ReactNative 相关依赖）：

```shell
yarn install
```

然后用 vscode 打开根目录下的 rust-to-all.code-workspace 以开始开发。

## Android

环境准备：

```shell
# 只安装比较常用的两个 target
rustup target add aarch64-linux-android x86_64-linux-android
```

运行 App：

```shell
# 在 android 目录
open . -a /Applications/Android\ Studio.app
```

启动后会有三个 RunConfig，分别为：

- RN：运行 RN app，需要先在根目录执行 `yarn start`
- Rust: 运行 Rust 驱动的 App
- Rust-Bench: 运行 Rust 中的一些 Benchmark 测试

## iOS

环境准备：

```shell
# 只安装比较常用的两个 target
rustup target add aarch64-apple-ios x86_64-apple-ios
```

编译构建：

```shell
./scripts/ios_build.sh
```

运行 App：

```shell
# 在 ios 目录
open ios.xcworkspace
```

## Web

环境准备：

```shell
 cargo install wasm-pack
```

编译构建：

```shell
./scripts/web_build.sh
```

运行 App：

```shell
# 在 web 目录
yarn install
yarn serve
```
