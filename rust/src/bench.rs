use crate::core::property::*;
use jni::sys::jbyteArray;
use std::time::Instant;

use crate::core::buffer::com::onee::rusty::model::root_as_view_property;
use crate::core::buffer::com::onee::rusty::model::ViewProperty as BufferViewProperty;

pub trait FromRustToJavaBench {
    fn call_empty(&self);
    fn call_use_flapigen(&self, args: ViewProperty, read: bool);
    fn call_use_json(&self, args: String, read: bool);
    fn call_use_flexbuffer(&self, args: Vec<u8>, read: bool);
}

pub struct FromJavaToRustBench {}

impl FromJavaToRustBench {
    fn log(&self, name: &str, content: &str) {
        info!("{} is {}", name, content);
    }
    pub fn call_empty(&self) {}
    pub fn call_use_flapigen(&self, args: ViewProperty, read: bool) {
        if read {
            self.log("flapigen-read", &args.width.to_string());
            self.log("flapigen-read", &args.height.to_string());
            self.log("flapigen-read", &args.margin_left.to_string());
            self.log("flapigen-read", &args.margin_right.to_string());
            self.log("flapigen-read", &args.margin_top.to_string());
            self.log("flapigen-read", &args.margin_bottom.to_string());
            self.log("flapigen-read", &args.flex.to_string());
            self.log("flapigen-read", &(format!("{:?}", args.display)));
            self.log("flapigen-read", &(format!("{:?}", args.flex_direction)));
            self.log("flapigen-read", &args.background_color.to_string());
            self.log("flapigen-read", &(format!("{:?}", args.flex_wrap)));
            self.log("flapigen-read", &args.content);
            self.log("flapigen-read", &args.a);
            self.log("flapigen-read", &args.b);
            self.log("flapigen-read", &args.c);
            self.log("flapigen-read", &args.d);
            self.log("flapigen-read", &args.e);
            self.log("flapigen-read", &args.f);
            self.log("flapigen-read", &args.g);
            self.log("flapigen-read", &args.h);
            self.log("flapigen-read", &args.i);
            self.log("flapigen-read", &args.j);
            self.log("flapigen-read", &args.k);
            self.log("flapigen-read", &args.l);
            self.log("flapigen-read", &args.m);
            self.log("flapigen-read", &args.n);
            self.log("flapigen-read", &args.o);
        }
    }
    pub fn call_use_json(&self, args: String, read: bool) {
        if read {
            let view_property: ViewProperty = serde_json::from_str(&args).unwrap();
            self.log("json-read", &view_property.width.to_string());
            self.log("json-read", &view_property.height.to_string());
            self.log("json-read", &view_property.margin_left.to_string());
            self.log("json-read", &view_property.margin_right.to_string());
            self.log("json-read", &view_property.margin_top.to_string());
            self.log("json-read", &view_property.margin_bottom.to_string());
            self.log("json-read", &view_property.flex.to_string());
            self.log("json-read", &(format!("{:?}", view_property.display)));
            self.log(
                "json-read",
                &(format!("{:?}", view_property.flex_direction)),
            );
            self.log("json-read", &view_property.background_color.to_string());
            self.log("json-read", &(format!("{:?}", view_property.flex_wrap)));
            self.log("json-read", &view_property.content);
            self.log("json-read", &view_property.a);
            self.log("json-read", &view_property.b);
            self.log("json-read", &view_property.c);
            self.log("json-read", &view_property.d);
            self.log("json-read", &view_property.e);
            self.log("json-read", &view_property.f);
            self.log("json-read", &view_property.g);
            self.log("json-read", &view_property.h);
            self.log("json-read", &view_property.i);
            self.log("json-read", &view_property.j);
            self.log("json-read", &view_property.k);
            self.log("json-read", &view_property.l);
            self.log("json-read", &view_property.m);
            self.log("json-read", &view_property.n);
            self.log("json-read", &view_property.o);
        }
    }
    pub fn call_use_flatbuffer(&self, args: &[u8], read: bool) {
        if read {
            let view_property: BufferViewProperty = root_as_view_property(args).unwrap();
            self.log("flatbuffer-read", &view_property.width().to_string());
            self.log("flatbuffer-read", &view_property.height().to_string());
            self.log("flatbuffer-read", &view_property.margin_left().to_string());
            self.log("flatbuffer-read", &view_property.margin_right().to_string());
            self.log("flatbuffer-read", &view_property.margin_top().to_string());
            self.log(
                "flatbuffer-read",
                &view_property.margin_bottom().to_string(),
            );
            self.log("flatbuffer-read", &view_property.flex().to_string());
            self.log(
                "flatbuffer-read",
                &(format!("{:?}", view_property.display())),
            );
            self.log(
                "flatbuffer-read",
                &(format!("{:?}", view_property.flex_direction())),
            );
            self.log(
                "flatbuffer-read",
                &view_property.background_color().to_string(),
            );
            self.log(
                "flatbuffer-read",
                &(format!("{:?}", view_property.flex_wrap())),
            );
            self.log("flatbuffer-read", &view_property.content().unwrap());
            self.log("flatbuffer-read", &view_property.a().unwrap());
            self.log("flatbuffer-read", &view_property.b().unwrap());
            self.log("flatbuffer-read", &view_property.c().unwrap());
            self.log("flatbuffer-read", &view_property.d().unwrap());
            self.log("flatbuffer-read", &view_property.e().unwrap());
            self.log("flatbuffer-read", &view_property.f().unwrap());
            self.log("flatbuffer-read", &view_property.g().unwrap());
            self.log("flatbuffer-read", &view_property.h().unwrap());
            self.log("flatbuffer-read", &view_property.i().unwrap());
            self.log("flatbuffer-read", &view_property.j().unwrap());
            self.log("flatbuffer-read", &view_property.k().unwrap());
            self.log("flatbuffer-read", &view_property.l().unwrap());
            self.log("flatbuffer-read", &view_property.m().unwrap());
            self.log("flatbuffer-read", &view_property.n().unwrap());
            self.log("flatbuffer-read", &view_property.o().unwrap());
        }
    }
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
