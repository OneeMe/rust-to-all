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
        tag: u16,
        class_name: &str,
        root_view_tag: u16,
        properties: CollectionMap,
    ) -> ();
    fn set_children(&self, tag: u16, children: Vec<i32>) -> ();
    fn on_batch_complete(&self) -> ();
}
