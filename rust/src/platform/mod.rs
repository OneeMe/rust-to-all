/// cbindgen:ignore
#[cfg(target_os = "android")]
pub mod android;

#[cfg(target_os = "ios")]
pub mod ios;

/// cbindgen:ignore
#[cfg(target_arch = "wasm32")]
pub mod web;
