use flapigen::{JavaConfig, LanguageConfig};
use std::path::Path;

fn main() {
    let in_src = Path::new("src")
        .join("model")
        .join("android")
        .join("export.d.rs");
    let out_src = Path::new("src")
        .join("model")
        .join("android")
        .join("export.rs");
    //ANCHOR: config
    let swig_gen = flapigen::Generator::new(LanguageConfig::JavaConfig(
        JavaConfig::new(
            Path::new("..")
                .join("android")
                .join("rusty")
                .join("src")
                .join("main")
                .join("java")
                .join("com")
                .join("onee")
                .join("rusty"),
            "com.onee.rusty".into(),
        )
        .use_null_annotation_from_package("androidx.annotation".into()),
    ))
    .rustfmt_bindings(true);
    //ANCHOR_END: config
    swig_gen.expand("android bindings", &in_src, &out_src);
    println!("cargo:rerun-if-changed={}", in_src.display());
}
