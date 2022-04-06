mod buffer_property_generated;

use buffer_property_generated::flatbuffer_generate::*;

use super::property::*;
use std::time::Duration;
use std::time::Instant;

pub trait FromRustToJavaBench {
    fn call_empty(&self);
    fn call_use_flapigen(&self, args: ViewProperty, read: bool);
    fn call_use_json(&self, args: String, read: bool);
    fn call_use_flatbuffer(&self, args: Vec<u8>, read: bool);
}

pub struct FromJavaToRustBench {}

impl FromJavaToRustBench {
    pub fn new() -> Self {
        FromJavaToRustBench {}
    }

    fn log(&self, name: &str, content: &str) {
        #[cfg(debug_assertions)]
        info!("{} is {}", name, content);
    }
    pub fn run_bench(&self, bench: Box<dyn FromRustToJavaBench>) {
        bench_call(&"empty", || {
            bench.call_empty();
        });
        bench_call(&"json-no-read", || {
            let view_property = create_view_property();
            bench.call_use_json(serde_json::to_string(&view_property).unwrap(), false);
        });
        bench_call(&"json-read", || {
            let view_property = create_view_property();
            bench.call_use_json(serde_json::to_string(&view_property).unwrap(), true);
        });
        bench_call(&"flatbuffers-no-read", || {
            let builder = create_buffer_view_property();
            bench.call_use_flatbuffer(builder.finished_data().to_vec(), false);
        });
        bench_call(&"flatbuffers-read", || {
            let builder = create_buffer_view_property();
            bench.call_use_flatbuffer(builder.finished_data().to_vec(), true);
        });
        bench_call("flapigen-no-read", || {
            let vp = create_view_property();
            bench.call_use_flapigen(vp, false);
        });
        bench_call("flapigen-read", || {
            let vp = create_view_property();
            bench.call_use_flapigen(vp, true);
        });
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
            let view_property: BufferViewProperty = root_as_buffer_view_property(args).unwrap();
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

fn create_buffer_view_property<'a>() -> flatbuffers::FlatBufferBuilder<'a> {
    let mut builder = flatbuffers::FlatBufferBuilder::with_capacity(1024);
    let content = builder.create_string("hello_world");
    let a = builder.create_string("a");
    let b = builder.create_string("b");
    let c = builder.create_string("c");
    let d = builder.create_string("d");
    let e = builder.create_string("e");
    let f = builder.create_string("f");
    let g = builder.create_string("g");
    let h = builder.create_string("h");
    let i = builder.create_string("i");
    let j = builder.create_string("j");
    let k = builder.create_string("k");
    let l = builder.create_string("l");
    let m = builder.create_string("m");
    let n = builder.create_string("n");
    let o = builder.create_string("o");
    let buffer_view_property = BufferViewProperty::create(
        &mut builder,
        &BufferViewPropertyArgs {
            width: 100.0,
            height: 100.0,
            margin_left: 10.0,
            margin_right: 10.0,
            margin_top: 10.0,
            margin_bottom: 10.0,
            flex: 1,
            display: BufferDisplay::flex,
            flex_direction: BufferFlexDirection::column,
            background_color: -1,
            flex_wrap: BufferFlexWrap::nowrap,
            content: Some(content),
            a: Some(a),
            b: Some(b),
            c: Some(c),
            d: Some(d),
            e: Some(e),
            f: Some(f),
            g: Some(g),
            h: Some(h),
            i: Some(i),
            j: Some(j),
            k: Some(k),
            l: Some(l),
            m: Some(m),
            n: Some(n),
            o: Some(o),
        },
    );
    builder.finish(buffer_view_property, None);
    builder
}

fn create_view_property() -> ViewProperty {
    let view_property = ViewProperty {
        width: 100.0,
        height: 100.0,
        margin_left: 10.0,
        margin_right: 10.0,
        margin_top: 10.0,
        margin_bottom: 10.0,
        flex: 1,
        display: Display::flex,
        flex_direction: FlexDirection::column,
        background_color: -1,
        flex_wrap: FlexWrap::nowrap,
        content: "hello_world".to_string(),
        a: "a".to_string(),
        b: "b".to_string(),
        c: "c".to_string(),
        d: "d".to_string(),
        e: "e".to_string(),
        f: "f".to_string(),
        g: "g".to_string(),
        h: "h".to_string(),
        i: "i".to_string(),
        j: "j".to_string(),
        k: "k".to_string(),
        l: "l".to_string(),
        m: "m".to_string(),
        n: "n".to_string(),
        o: "o".to_string(),
    };
    view_property
}

pub fn bench_call<F>(name: &str, block: F)
where
    F: Fn() -> (),
{
    let before = Instant::now();
    let mut count = 0;
    let mut vector = vec![];
    let time_limit = Duration::from_secs(1);
    let mut total_time = Duration::ZERO;
    while total_time.as_millis() < 1000 {
        // while count < 30000 {
        let local_before = Instant::now();
        block();
        let duration = local_before.elapsed();
        total_time += duration;
        vector.push(duration.as_nanos());
        count += 1;
    }
    info!(
        "[Bench-Rust-to-Java] {} count: {}, average duration: {} ns",
        name,
        count,
        total_time.as_nanos() / &count
    );
    // info!("[Bench-Rust-to-Java-Detail] {}: {:?}", name, vector);
}
