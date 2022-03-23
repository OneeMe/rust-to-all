#[macro_use]
extern crate log;

mod core;
#[cfg(target_os = "android")]
mod export;
mod platform;
