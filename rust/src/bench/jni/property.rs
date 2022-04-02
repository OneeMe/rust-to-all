use serde::{Deserialize, Serialize};

#[derive(Clone, Serialize, Deserialize, Debug)]
pub enum Display {
    flex,
    none,
}

#[derive(Clone, Serialize, Deserialize, Debug)]
pub enum FlexDirection {
    column,
    column_reverse,
    row,
    row_reverse,
}

#[derive(Clone, Serialize, Deserialize, Debug)]
pub enum FlexWrap {
    nowrap,
    wrap,
}

#[derive(Clone, Serialize, Deserialize, Debug)]
#[serde(rename_all = "camelCase")]
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

impl ViewProperty {
    pub fn new(
        width: f64,
        height: f64,
        margin_left: f64,
        margin_right: f64,
        margin_top: f64,
        margin_bottom: f64,
        flex: i32,
        display: Display,
        flex_direction: FlexDirection,
        background_color: i32,
        flex_wrap: FlexWrap,
        content: String,
        a: String,
        b: String,
        c: String,
        d: String,
        e: String,
        f: String,
        g: String,
        h: String,
        i: String,
        j: String,
        k: String,
        l: String,
        m: String,
        n: String,
        o: String,
    ) -> Self {
        ViewProperty {
            width,
            height,
            margin_left,
            margin_right,
            margin_top,
            margin_bottom,
            flex,
            display,
            flex_direction,
            background_color,
            flex_wrap,
            content,
            a,
            b,
            c,
            d,
            e,
            f,
            g,
            h,
            i,
            j,
            k,
            l,
            m,
            n,
            o,
        }
    }
}
