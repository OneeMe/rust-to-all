use flapigen::{JavaConfig, LanguageConfig};
use std::io::ErrorKind;
use std::path::Path;
use std::process::Command;

fn main() {
    // build flapigen
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
            &Path::new("src/bench/android/flapigen.rs.in"),
        ],
        &Path::new("src").join("flapigen.rs"),
    );
    // build flatbuffer
    match Command::new("flatc").spawn() {
        Ok(_) => println!("flatc installed ^.^"),
        Err(e) => match e.kind() {
            ErrorKind::NotFound => {
                println!("`flatc` was not found! please run `brew install flatbuffers`")
            }
            _ => println!("error: {}", e),
        },
    }
    // build to rust
    Command::new("flatc")
        .args([
            "--rust",
            "-o",
            "src/bench/android",
            "src/bench/android/buffer_property.fbs",
        ])
        .output()
        .expect("failed to execute flatc");
    // build to java
    Command::new("flatc")
        .args([
            "--java",
            "-o",
            "../android/rusty/src/main/java/com/onee/rusty",
            "src/bench/android/buffer_property.fbs",
        ])
        .output()
        .expect("failed to execute flatc");
}
