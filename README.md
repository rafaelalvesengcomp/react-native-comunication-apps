## React Native Android Library Launcher

1. Push it to **GitHub**.
2. Do `yarn add https://github.com/rafaelalvesengcomp/react-native-comunication-apps.git` in your main project.
3. Link the library:
    * Add the following to `android/settings.gradle`:
        ```
        include ':react-native-comunication-apps'
        project(':react-native-comunication-apps').projectDir = new File(settingsDir, '../node_modules/react-native-comunication-apps/android')
        ```

    * Add the following to `android/app/build.gradle`:
        ```xml
        ...

        dependencies {
            ...
            compile project(':react-native-comunication-apps')
        }
        ```
    * Add the following to `android/app/src/main/java/**/MainApplication.java`:
        ```java

        import com.fortalsistemas.comunicationapps.Package;

        public class MainApplication extends Application implements ReactApplication {

            @Override
            protected List<ReactPackage> getPackages() {
                return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new Package()     // add this for react-native-android-library-boilerplate
                );
            }
        }
        ```
4. Change line 85 on file Module, put number of line in table of application.