#[macro_use]
extern crate log;

/// cbindgen:ignore
#[cfg(target_os = "android")]
mod android;

/// cbindgen:ignore
#[cfg(target_os = "android")]
mod android_glue;

#[cfg(target_os = "ios")]
mod ios;

/// cbindgen:ignore
#[cfg(target_arch = "wasm32")]
mod web;
