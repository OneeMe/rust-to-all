pub mod command;

#[cfg(target_os = "android")]
use super::platform::android::*;
use command::*;

pub struct Engine {}

impl Engine {
    pub fn new() -> Self {
        Engine {}
    }
    pub fn launch(&self) {
        init_log();
        info!("launched");
    }
    pub fn run_app(&self, app_id: i32) -> CommandList {
        info!("app id is {}", app_id);
        CommandList::new(vec![
            Command::new(
                CommandType::CreateView,
                2,
                "RCTView".to_owned(),
                1,
                PropertyList::new(vec![
                    "backgroundColor".to_owned(),
                    "-65536".to_owned(),
                    "width".to_owned(),
                    "100".to_owned(),
                    "height".to_owned(),
                    "100".to_owned(),
                ]),
            ),
            Command::new(
                CommandType::SetChild,
                1,
                "".to_owned(),
                2,
                PropertyList::new(vec![]),
            ),
        ])
    }
}
