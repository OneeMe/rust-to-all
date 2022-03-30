use crate::core::property::*;
use std::time::Instant;

pub trait FromRustToJavaBench {
    fn call_empty(&self);
    fn call_use_flapigen(&self, args: ViewProperty, read: bool);
    fn call_use_json(&self, args: String, read: bool);
    fn call_use_flexbuffer(&self, args: Vec<u8>, read: bool);
}

pub fn bench_call<F>(count: i32, name: &str, block: F)
where
    F: Fn() -> (),
{
    let before = Instant::now();
    for _ in 0..count {
        block();
    }
    info!("{} Elapsed time: {:.2?}", name, before.elapsed());
}
