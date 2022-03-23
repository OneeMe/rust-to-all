use jni::sys::*;

use crate::core::command::*;
use crate::core::*;

// foreign_enum!(
//     enum CommandType {
//         CreateView = CommandType::CreateView,
//         SetChild = CommandType::SetChild,
//     }
// );

foreign_class!(class CommandList {
    self_type CommandList;
    constructor CommandList::new() -> CommandList {
        CommandList::new(vec![])
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
