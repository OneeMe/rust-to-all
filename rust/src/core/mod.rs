pub mod buffer;
pub mod collection;
pub mod property;
mod view;

use std::vec;

use crate::bench::*;

use self::buffer::com::onee::rusty::model::Display as BufferDisplay;
use self::buffer::com::onee::rusty::model::FlexDirection as BufferFlexDirection;
use self::buffer::com::onee::rusty::model::FlexWrap as BufferFlexWrap;
use self::buffer::com::onee::rusty::model::ViewProperty as BufferViewProperty;
use self::buffer::com::onee::rusty::model::ViewPropertyArgs;
use self::property::*;

#[cfg(target_os = "android")]
use super::platform::android::*;

use collection::*;
use serde_json::json;

pub struct Engine {
    manager: Box<dyn UIManager>,
}

impl Engine {
    pub fn new(manager: Box<dyn UIManager>) -> Self {
        Engine { manager }
    }
    pub fn launch(&self) -> () {
        init_log();
        info!("launched");
    }
    pub fn run_bench(&self, bench: Box<dyn FromRustToJavaBench>) -> FromJavaToRustBench {
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
            &ViewPropertyArgs {
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
        let mut s = flexbuffers::FlexbufferSerializer::new();
        let view_property_clone = view_property.clone();
        bench_call(3000, &"empty", || {
            bench.call_empty();
        });
        bench_call(3000, &"json-no-read", || {
            bench.call_use_json(serde_json::to_string(&view_property).unwrap(), false);
        });
        bench_call(3000, &"json-read", || {
            bench.call_use_json(serde_json::to_string(&view_property).unwrap(), true);
        });
        bench_call(3000, &"flexbuffers-no-read", || {
            bench.call_use_flexbuffer(builder.finished_data().to_vec(), false);
        });
        bench_call(3000, &"flexbuffers-read", || {
            bench.call_use_flexbuffer(builder.finished_data().to_vec(), true);
        });
        let vp = &view_property;
        bench_call(3000, "flapigen-no-read", || {
            bench.call_use_flapigen(vp.to_owned(), false);
        });
        let vpc = &view_property_clone;
        bench_call(3000, "flapigen-read", || {
            bench.call_use_flapigen(vpc.to_owned(), true);
        });
        FromJavaToRustBench {}
    }
    pub fn run_app(&self, app_id: i32) -> () {
        info!("app id is {}", app_id);
        self.complex_demo();
        self.manager.on_batch_complete();
    }

    fn simple_demo(&self) {
        self.manager.create_view(
            2,
            view::VIEW,
            1,
            CollectionMap::new(
                json!(
                    {
                        "width": 100,
                        "height": 100,
                        "backgroundColor": -65536,
                    }
                )
                .as_object()
                .unwrap()
                .to_owned(),
            ),
        );
        self.manager.set_children(1, vec![2]);
    }

    fn complex_demo(&self) {
        let count = 3000;
        let mut items = Vec::new();
        for i in 0..count {
            let item_tag = i * 2 + 3;
            items.push(item_tag);
            self.manager.create_view(
                item_tag,
                view::VIEW,
                1,
                CollectionMap::new(
                    json!(
                        {
                            "width": 20,
                            "height": 20,
                            "backgroundColor": -16777216,
                            "margin": 2,
                        }
                    )
                    .as_object()
                    .unwrap()
                    .to_owned(),
                ),
            );
        }

        let container_tag = count * 2 + 3;
        self.manager.create_view(
            container_tag,
            view::VIEW,
            1,
            CollectionMap::new(
                json!({
                    "flexWrap": "wrap",
                    "display": "flex",
                    "backgroundColor": -1,
                    "flexDirection": "row",
                })
                .as_object()
                .unwrap()
                .to_owned(),
            ),
        );
        self.manager.set_children(container_tag, items);

        let root_tag = container_tag + 2;
        self.manager.create_view(
            root_tag,
            view::VIEW,
            1,
            CollectionMap::new(
                json!({
                    "flex": 1,
                    "collapsable": true,
                    "pointerEvents": "box-none",
                })
                .as_object()
                .unwrap()
                .to_owned(),
            ),
        );
        self.manager.set_children(root_tag, vec![container_tag]);

        let yet_root_tag = root_tag + 2;
        self.manager.create_view(
            yet_root_tag,
            view::VIEW,
            1,
            CollectionMap::new(
                json!({
                    "flex": 1,
                    "pointerEvents": "box-none",
                })
                .as_object()
                .unwrap()
                .to_owned(),
            ),
        );
        self.manager.set_children(yet_root_tag, vec![root_tag]);

        self.manager.set_children(1, vec![yet_root_tag]);
    }
}

pub trait UIManager {
    fn create_view(
        &self,
        tag: i32,
        class_name: &str,
        root_view_tag: i32,
        properties: CollectionMap,
    ) -> ();
    fn set_children(&self, tag: i32, children: Vec<i32>) -> ();
    fn update_view(&self, tag: i32, class_name: &str, properties: CollectionMap) -> ();
    fn manage_children(
        &self,
        tag: i32,
        move_from: Vec<i32>,
        move_to: Vec<i32>,
        added_children: Vec<i32>,
        add_at_indices: Vec<i32>,
        remove_from: Vec<i32>,
    ) -> ();
    fn on_batch_complete(&self) -> ();
}
