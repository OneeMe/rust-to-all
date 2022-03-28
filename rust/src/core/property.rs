use serde_json::{Map, Value};

#[derive(Copy, Clone)]
pub enum PropertyType {
    Null,
    Boolean,
    Number,
    String,
    Map,
    Array,
}

pub struct PropertyValue {
    pub value: Value,
    pub property_type: PropertyType,
}

impl PropertyValue {
    pub fn new(value: Value) -> Self {
        let property_type = match value {
            Value::Null => PropertyType::Null,
            Value::Bool(_) => PropertyType::Boolean,
            Value::Number(_) => PropertyType::Number,
            Value::String(_) => PropertyType::String,
            Value::Array(_) => PropertyType::Array,
            Value::Object(_) => PropertyType::Map,
        };
        PropertyValue {
            value,
            property_type,
        }
    }
}

pub struct PropertyMap {
    pub properties: Map<String, Value>,
}

impl PropertyMap {
    pub fn new(properties: Map<String, Value>) -> Self {
        PropertyMap { properties }
    }
    pub fn import_keys(&self) -> Vec<String> {
        self.properties.keys().map(|key| key.to_owned()).collect()
    }

    pub fn import_values(&self) -> Vec<PropertyValue> {
        self.properties
            .values()
            .map(|value| PropertyValue::new(value.to_owned()))
            .collect()
    }

    pub fn import_types(&self) -> Vec<PropertyType> {
        self.properties
            .values()
            .map(|value| match value {
                Value::Null => PropertyType::Null,
                Value::Bool(_) => PropertyType::Boolean,
                Value::Number(_) => PropertyType::Number,
                Value::String(_) => PropertyType::String,
                Value::Array(_) => PropertyType::Array,
                Value::Object(_) => PropertyType::Map,
            })
            .collect()
    }
}

pub struct PropertyList {
    pub vec: Vec<Value>,
}

impl PropertyList {
    pub fn new(vec: Vec<Value>) -> Self {
        PropertyList { vec }
    }
}
