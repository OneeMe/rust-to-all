#[macro_use]
extern crate log;

#[cfg(target_os = "android")]
mod export;
mod platform;
