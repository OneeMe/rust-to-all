use std::vec::Vec;

pub struct CommandList {
    pub commands: Vec<Command>,
}

impl CommandList {
    pub fn new(commands: Vec<Command>) -> Self {
        CommandList { commands }
    }
    pub fn length(&self) -> usize {
        self.commands.len()
    }
    pub fn get(&self, index: usize) -> Command {
        self.commands[index].clone()
    }
}

#[derive(Clone, Copy)]
pub enum CommandType {
    CreateView,
    SetChild,
}

#[derive(Clone)]
pub struct Command {
    pub command_type: CommandType,
    pub tag: u32,
    pub class_name: String,
    pub root_view_tag: u32,
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

    pub fn command_type(&self) -> CommandType {
        self.command_type
    }

    pub fn tag(&self) -> u32 {
        self.tag
    }

    pub fn class_name(&self) -> String {
        self.class_name.clone()
    }

    pub fn root_view_tag(&self) -> u32 {
        self.root_view_tag
    }
}
