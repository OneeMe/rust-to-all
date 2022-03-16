package com.onee.rustapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
import com.facebook.debug.holder.Printer;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JSIModuleProvider;
import com.facebook.react.bridge.JSIModuleSpec;
import com.facebook.react.bridge.JSIModuleType;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.CoreComponentsRegistry;
import com.facebook.react.fabric.FabricJSIModuleProvider;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.soloader.SoLoader;
import com.onee.rustapp.components.MainComponentsRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost =
      new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

          @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          // Packages that cannot be autolinked yet can be added manually here, for example:
          // packages.add(new MyReactNativePackage());
          return packages;
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }

          @Override
          protected JSIModulePackage getJSIModulePackage() {
              return new JSIModulePackage() {
                  @Override
                  public List<JSIModuleSpec> getJSIModules(
                          final ReactApplicationContext reactApplicationContext,
                          final JavaScriptContextHolder jsContext) {
                      final List<JSIModuleSpec> specs = new ArrayList<>();

                      // Here we provide a new JSIModuleSpec that will be responsible of providing the
                      // custom Fabric Components.
                      specs.add(
                              new JSIModuleSpec() {
                                  @Override
                                  public JSIModuleType getJSIModuleType() {
                                      return JSIModuleType.UIManager;
                                  }

                                  @Override
                                  public JSIModuleProvider<UIManager> getJSIModuleProvider() {
                                      final ComponentFactory componentFactory = new ComponentFactory();
                                      CoreComponentsRegistry.register(componentFactory);

                                      // Here we register a Components Registry.
                                      // The one that is generated with the template contains no components
                                      // and just provides you the one from React Native core.
                                      MainComponentsRegistry.register(componentFactory);

                                      final ReactInstanceManager reactInstanceManager = getReactInstanceManager();

                                      ViewManagerRegistry viewManagerRegistry =
                                              new ViewManagerRegistry(
                                                      reactInstanceManager.getOrCreateViewManagers(reactApplicationContext));

                                      return new FabricJSIModuleProvider(
                                              reactApplicationContext,
                                              componentFactory,
                                              new EmptyReactNativeConfig(),
                                      viewManagerRegistry);
                                  }
                              });
                      return specs;
                  }
              };
          }
      };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
    enablePrinter();
  }

    private void enablePrinter() {
        PrinterHolder.setPrinter(new Printer() {
            @Override
            public void logMessage(DebugOverlayTag tag, String message, Object... args) {
                Log.d(tag.name, message);
            }

            @Override
            public void logMessage(DebugOverlayTag tag, String message) {
                Log.d(tag.name, message);
            }

            @Override
            public boolean shouldDisplayLogMessage(DebugOverlayTag tag) {
                return true;
            }
        });
    }

    /**
   * Loads Flipper in React Native templates. Call this in the onCreate method with something like
   * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
   *
   * @param context
   * @param reactInstanceManager
   */
  private static void initializeFlipper(
      Context context, ReactInstanceManager reactInstanceManager) {
    if (BuildConfig.DEBUG) {
      try {
        /*
         We use reflection here to pick up the class that initializes Flipper,
        since Flipper library is not available in release mode
        */
        Class<?> aClass = Class.forName("com.onee.rustapp.ReactNativeFlipper");
        aClass
            .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
            .invoke(null, context, reactInstanceManager);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
}
