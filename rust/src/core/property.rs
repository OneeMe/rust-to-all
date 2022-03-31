use serde::{Deserialize, Serialize};

#[derive(Clone, Serialize, Deserialize)]
pub enum Display {
    flex,
    none,
}

#[derive(Clone, Serialize, Deserialize)]
pub enum FlexDirection {
    column,
    column_reverse,
    row,
    row_reverse,
}

#[derive(Clone, Serialize, Deserialize)]
pub enum FlexWrap {
    nowrap,
    wrap,
}

#[derive(Clone, Serialize, Deserialize)]
pub struct ViewProperty {
    pub width: f64,
    pub height: f64,
    pub margin_left: f64,
    pub margin_right: f64,
    pub margin_top: f64,
    pub margin_bottom: f64,
    pub flex: i32,
    pub display: Display,
    pub flex_direction: FlexDirection,
    pub background_color: i32,
    pub flex_wrap: FlexWrap,
    pub content: String,
    pub a: String,
    pub b: String,
    pub c: String,
    pub d: String,
    pub e: String,
    pub f: String,
    pub g: String,
    pub h: String,
    pub i: String,
    pub j: String,
    pub k: String,
    pub l: String,
    pub m: String,
    pub n: String,
    pub o: String,
}
