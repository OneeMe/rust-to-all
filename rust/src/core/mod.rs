pub mod collection;
mod view;

use std::vec;

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
