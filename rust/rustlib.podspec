Pod::Spec.new do |spec|
  spec.name         = 'rustlib'
  spec.version      = '0.0.1'
  spec.license      = { :type => 'BSD' }
  spec.authors      = { 'onee' => 'onee.me@outlook.com' }
  spec.summary      = 'Rust binary'
  spec.homepage     = 'https://github.com/OneeMe/rust-to-all'
  spec.source       = { :git => 'https://github.com/OneeMe/rust-to-all.git', :tag => '0.0.1' }
  # TODO：可以自动化生成一些头森建
  spec.source_files = 'rustlib.h'
  # TODO: 可以合并一下二进制
  spec.vendored_libraries = 'target/x86_64-apple-ios/release/deps/librustlib.a'
end
