use std::vec::Vec;

pub struct CommandList {
    commands: Vec<Command>,
}

impl CommandList {
    pub fn new(commands: Vec<Command>) -> Self {
        CommandList { commands }
    }
    pub fn length(&self) -> usize {
        self.commands.len()
    }
    pub fn at(&self, index: usize) -> Command {
        self.commands[index].clone()
    }
}

#[derive(Clone)]
pub enum CommandType {
    CreateView,
    SetChild,
}

#[derive(Clone)]
pub struct Command {
    command_type: CommandType,
    tag: u32,
    class_name: String,
    root_view_tag: u32,
}

impl Command {
    pub fn new(
        command_type: CommandType,
        tag: u32,
        class_name: String,
        root_view_tag: u32,
    ) -> Self {
        Command {
            command_type,
            tag,
            class_name,
            root_view_tag,
        }
    }
}
