use flapigen::{JavaConfig, LanguageConfig};
use std::path::Path;

fn main() {
    let in_src = Path::new("src").join("export.rs.in");
    let out_src = Path::new("src").join("export.rs");
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
                .join("rusty")
                .join("glue"),
            "com.onee.rusty.glue".into(),
        )
        .use_null_annotation_from_package("androidx.annotation".into()),
    ))
    .remove_not_generated_files_from_output_directory(true)
    .rustfmt_bindings(true);
    //ANCHOR_END: config
    swig_gen.expand("android bindings", &in_src, &out_src);
    println!("cargo:rerun-if-changed={}", in_src.display());
}
