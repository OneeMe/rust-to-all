#[macro_use]
extern crate log;

mod bench;
mod core;
#[cfg(target_os = "android")]
mod flapigen;
mod platform;
