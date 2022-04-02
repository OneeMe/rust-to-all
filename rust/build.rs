use flapigen::{JavaConfig, LanguageConfig};
use std::path::Path;

fn main() {
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
    swig_gen.expand_many(
        "rustylib",
        &vec![
            &Path::new("src/flapigen.rs.in"),
            &Path::new("src/bench/jni/flapigen.rs.in"),
        ],
        &Path::new("src").join("flapigen.rs"),
    );
}
