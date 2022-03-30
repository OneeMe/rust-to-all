use serde::{Deserialize, Serialize};

#[derive(Clone, Copy, Serialize, Deserialize)]
pub enum Display {
    flex,
    none,
}

#[derive(Clone, Copy, Serialize, Deserialize)]
pub enum FlexDirection {
    column,
    column_reverse,
    row,
    row_reverse,
}

#[derive(Clone, Copy, Serialize, Deserialize)]
pub enum FlexWrap {
    nowrap,
    wrap,
}

#[derive(Clone, Copy, Serialize, Deserialize)]
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
}
