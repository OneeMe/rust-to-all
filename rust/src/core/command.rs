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
    pub properties: PropertyList,
}

#[derive(Clone)]
pub struct PropertyList {
    pub properties: Vec<String>,
}

impl PropertyList {
    pub fn new(properties: Vec<String>) -> Self {
        PropertyList { properties }
    }
    pub fn length(&self) -> usize {
        self.properties.len()
    }
    pub fn get(&self, index: usize) -> String {
        self.properties[index].clone()
    }
}

impl Command {
    pub fn new(
        command_type: CommandType,
        tag: u32,
        class_name: String,
        root_view_tag: u32,
        properties: PropertyList,
    ) -> Self {
        Command {
            command_type,
            tag,
            class_name,
            root_view_tag,
            properties,
        }
    }
}
