//
//  ViewController.m
//  ios
//
//  Created by onee on 2022/3/3.
//

#import "ViewController.h"

#import <rustlib/rustlib.h>

@interface ViewController ()
@property (weak, nonatomic) IBOutlet UILabel *label;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    char *cString = hello("Onee");
    NSString *string = [NSString stringWithCString:cString encoding:NSUTF8StringEncoding];
    hello_release(cString);
    self.label.text = string;
}


@end
