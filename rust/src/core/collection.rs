use serde_json::{Map, Value};

#[derive(Copy, Clone)]
pub enum ValueType {
    Null,
    Boolean,
    Number,
    String,
    Map,
    Array,
}

pub struct CollectionValue {
    pub value: Value,
    pub property_type: ValueType,
}

impl CollectionValue {
    pub fn new(value: Value) -> Self {
        let property_type = match value {
            Value::Null => ValueType::Null,
            Value::Bool(_) => ValueType::Boolean,
            Value::Number(_) => ValueType::Number,
            Value::String(_) => ValueType::String,
            Value::Array(_) => ValueType::Array,
            Value::Object(_) => ValueType::Map,
        };
        CollectionValue {
            value,
            property_type,
        }
    }
}

pub struct CollectionMap {
    pub map: Map<String, Value>,
}

impl CollectionMap {
    pub fn new(map: Map<String, Value>) -> Self {
        CollectionMap { map }
    }
    pub fn import_keys(&self) -> Vec<String> {
        self.map.keys().map(|key| key.to_owned()).collect()
    }

    pub fn import_values(&self) -> Vec<CollectionValue> {
        self.map
            .values()
            .map(|value| CollectionValue::new(value.to_owned()))
            .collect()
    }

    pub fn import_types(&self) -> Vec<ValueType> {
        self.map
            .values()
            .map(|value| match value {
                Value::Null => ValueType::Null,
                Value::Bool(_) => ValueType::Boolean,
                Value::Number(_) => ValueType::Number,
                Value::String(_) => ValueType::String,
                Value::Array(_) => ValueType::Array,
                Value::Object(_) => ValueType::Map,
            })
            .collect()
    }
}

pub struct CollectionList {
    pub vec: Vec<Value>,
}

impl CollectionList {
    pub fn new(vec: Vec<Value>) -> Self {
        CollectionList { vec }
    }

    pub fn import_values(&self) -> Vec<CollectionValue> {
        self.vec
            .iter()
            .map(|value| CollectionValue::new(value.to_owned()))
            .collect()
    }

    pub fn import_types(&self) -> Vec<ValueType> {
        self.vec
            .iter()
            .map(|value| match value {
                Value::Null => ValueType::Null,
                Value::Bool(_) => ValueType::Boolean,
                Value::Number(_) => ValueType::Number,
                Value::String(_) => ValueType::String,
                Value::Array(_) => ValueType::Array,
                Value::Object(_) => ValueType::Map,
            })
            .collect()
    }
}
