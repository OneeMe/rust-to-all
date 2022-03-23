#![cfg(target_os = "android")]
#![allow(non_snake_case)]

extern crate android_logger;

use android_logger::Config;
use jni::objects::JClass;
use jni::JNIEnv;
use log::Level;

#[no_mangle]
pub extern "system" fn Java_com_onee_rusty_RustyEngine_launch(_env: JNIEnv, _class: JClass) -> () {
    android_logger::init_once(
        Config::default()
            .with_min_level(Level::Trace)
            .with_tag("Rust"),
    );
    debug!("launched");
}

pub fn hello() -> String {
    "test".to_string()
}
