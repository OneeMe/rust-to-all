/// cbindgen:ignore
#[cfg(target_os = "android")]
mod android;

#[cfg(target_os = "ios")]
mod ios;

/// cbindgen:ignore
#[cfg(target_arch = "wasm32")]
mod web;
