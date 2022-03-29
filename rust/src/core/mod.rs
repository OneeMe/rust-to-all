pub mod collection;
mod view;

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
        self.manager.on_batch_complete();
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
