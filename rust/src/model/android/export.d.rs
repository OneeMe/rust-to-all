use std::Vec;

struct CommandList {
    commands: Vec<Command>,
}

impl CommandList {
    fn get_commands(&self) -> &Vec<Command> {
        &self.commands
    }
}

enum CommandType {
    CreateView,
    SetChild,
}

struct Command {
    commandType: CommandType,
    tag: u32,
    class_name: String,
    root_view_tag: u32,
}

foreign_enum!(
    enum CommandType {
        CreateView = MyEnum::CreateView,
        SetChild = MyEnum::SetChild,
    }
);

type CommandList = Vec<Command>;

foreigner_class!(class CommandList {
    self_type CommandList;
    constructor create() -> CommandList {
        Vec::new()
    }
    fn CommandList::len(&self) -> usize;
    fn at(&self, i: usize) -> Command {
        &this[i]
    }
    fn push_back(&mut self, s: Command) {
        this.push(s);
    }
});

foreigner_class!(class Command {
    self_type Command;
    constructor create() -> Command {
        Command {
            commandType: CommandType::CreateView,
        }
    }
    fn Command::get_commandType(&self) -> CommandType {
        this.commandType
    }
});
