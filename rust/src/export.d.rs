use jni::sys::*;

extern crate android_logger;

use android_logger::Config;
use log::Level;

use std::vec::Vec;

struct Engine {}

impl Engine {
    fn new() -> Self {
        Engine {}
    }
    fn launch(&self) {
        android_logger::init_once(
            Config::default()
                .with_min_level(Level::Trace)
                .with_tag("Rust"),
        );
        debug!("launched");
    }
    fn run_app(&self, app_id: i32) -> CommandList {
        CommandList {
            commands: vec![Command {
                command_type: CommandType::CreateView,
                tag: 2,
                class_name: "RCTView".to_owned(),
                root_view_tag: 1,
            }],
        }
    }
}

struct CommandList {
    commands: Vec<Command>,
}

impl CommandList {
    fn new(commands: Vec<Command>) -> Self {
        CommandList { commands }
    }
    fn length(&self) -> usize {
        self.commands.len()
    }
    fn at(&self, index: usize) -> Command {
        self.commands[index].clone()
    }
}

#[derive(Clone)]
enum CommandType {
    CreateView,
    SetChild,
}

#[derive(Clone)]
struct Command {
    command_type: CommandType,
    tag: u32,
    class_name: String,
    root_view_tag: u32,
}

impl Command {
    fn new(command_type: CommandType, tag: u32, class_name: String, root_view_tag: u32) -> Self {
        Command {
            command_type,
            tag,
            class_name,
            root_view_tag,
        }
    }
}

// foreign_enum!(
//     enum CommandType {
//         CreateView = CommandType::CreateView,
//         SetChild = CommandType::SetChild,
//     }
// );

foreign_class!(class CommandList {
    self_type CommandList;
    constructor CommandList::new() -> CommandList {
        CommandList {
            commands: vec![],
        }
    }
    // fn CommandList::length(&self) -> usize;
    // fn at(&self, index: usize) -> Command;
});

// foreign_class!(class Command {
//     self_type Command;
//     constructor Command::new() -> Command;
//     fn Command::commandType(&self) -> CommandType {
//         self.command_type
//     }
//     fn Command::tag(&self) -> u32 {
//         self.tag
//     }
//     fn Command::className(&self) -> String {
//         self.class_name.clone()
//     }
//     fn Command::rootViewTag(&self) -> u32 {
//         self.root_view_tag
//     }
// });

foreign_class!(class Engine {
    self_type Engine;
    constructor Engine::new() -> Engine;
    fn Engine::launch(&self) -> ();
    fn Engine::run_app(&self, _: i32) -> CommandList;
});
