# hockey-gradle-plugin
Plugin for uploading artifacts to https://hockeyapp.net/  
[Link to plugin homepage on gradle plugins](https://plugins.gradle.org/plugin/com.pvasiliev.hockey)
# Basic usage
1. Add this to the project level build.gradle 
```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.com.pvasiliev:hockey-gradle-plugin:1.0"
  }
}
```
2. In the app level build.gradle file add hockey block. 
You should provide application **variant name** and **token** from hockeyapp for this app and 
also **relative path from build directory** to apk file for this variant
```groovy
apply plugin: "com.pvasiliev.hockey"
hockey {
    debug{
      apiToken = "2564e2a10ab24f319432b2fdb82fa71a"
      path = "outputs\\apk\\app-debug.apk"
    }
}
```
3. In other gradle task group you will find new tasks
![image](https://user-images.githubusercontent.com/36657103/36633451-f5bf7f80-19a6-11e8-9226-b54d1d820883.png)

# TODO
1. Add more customization
2. Upload jar to maven central or bintray
3. Write tests
