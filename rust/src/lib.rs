#[macro_use]
extern crate log;

// #[macro_use]
// mod bench;
// mod core;
#[cfg(target_os = "android")]
mod flapigen;
mod platform;
