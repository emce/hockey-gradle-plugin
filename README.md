# hockey-gradle-plugin
Plugin for uploading artifacts to https://hockeyapp.net/

# Basic usage
1. Download project
2. Specify path to downloaded project repo folder in your project level build.gradle file.Example: 
```groovy
buildscript {
  repositories {
    maven{
      url "C:\\Users\\mm\\IdeaProjects\\hockey-gradle-plugin\\repo"
    }
  }
  dependencies {
    classpath "com.pvasiliev:hockey-gradle-plugin:1.0"
  }
}
```
3. Apply plugin in app level build.gradle file and configure hockey block. 
You should provide application **variant name** and **token** from hockeyapp for this app and 
also **relative path from build directory** to apk file for this variant
```groovy
apply plugin: 'hockey'
hockey {
    variantToApiToken = ["debug": "2564e2a10ab24f319432b2fdb82fa71a"]
    variantToOutputFile = ["debug": "outputs\\apk\\app-debug.apk"]
}
```
4. In other gradle task group you will find new tasks
![image](https://user-images.githubusercontent.com/36657103/36633451-f5bf7f80-19a6-11e8-9226-b54d1d820883.png)

# TODO
1. Add more customization
2. Upload jar to maven central or bintray
3. Write tests
