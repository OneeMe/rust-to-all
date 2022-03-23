#![cfg(target_os = "android")]
#![allow(non_snake_case)]
extern crate android_logger;

use android_logger::Config;
use log::Level;

pub fn init_log() {
    android_logger::init_once(
        Config::default()
            .with_min_level(Level::Trace)
            .with_tag("Rust"),
    );
    info!("run on android");
}
