pub mod property;
mod view;

#[cfg(target_os = "android")]
use super::platform::android::*;
use property::*;
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
            PropertyMap::new(
                json!(
                    {
                        "width": "100",
                        "height": "100",
                        "backgroundColor": -65536,
                    }
                )
                .as_object()
                .unwrap()
                .to_owned(),
            ),
        );
        // vec![
        //     Command::new(
        //         CommandType::CreateView,
        //         2,
        //         "RCTView".to_owned(),
        //         1,
        //         PropertyMap::new(vec![
        //             "backgroundColor".to_owned(),
        //             "-65536".to_owned(),
        //             "width".to_owned(),
        //             "100".to_owned(),
        //             "height".to_owned(),
        //             "100".to_owned(),
        //         ]),
        //     ),
        //     Command::new(
        //         CommandType::SetChild,
        //         1,
        //         "".to_owned(),
        //         2,
        //         PropertyMap::new(vec![]),
        //     ),
        // ]
    }
}

pub trait UIManager {
    fn create_view(
        &self,
        tag: u16,
        class_name: &str,
        root_view_tag: u16,
        properties: PropertyMap,
    ) -> ();
    fn set_children(&self, tag: u16, children: PropertyList) -> ();
    fn on_batch_complete(&self) -> ();
}
