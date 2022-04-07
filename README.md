# Quizzy

Simple app for creating your own quiz.

## Run tests with adb

1. Build or rebuild your the application and test package.

2. Install test package and Quizzy application package files (APK files) to the current Android
   device or emulator.

3. find your SDK to use adb. (Android Studio -> Tools -> SDK Manager -> Android SDK Location).

4. Open CMD.

5. Write -> cd AndroidSDKLocationPath "From Step Number 3" -> Enter.

6. Write -> cd platform-tools -> Enter.

7. Write -> adb kill-server -> Enter.

8. Make sure you have connected android device or emulator to the pc.

9.Write -> adb devices -> Enter.

10. Copy the device name or save it. :-)

11. Find the apks paths folder on your pc. 1- Main apk package (
    AndroidStudioProjects\Quizzy\app\build\intermediates\apk\debug). 2- Test apk package (
    AndroidStudioProjects\Quizzy\app\build\intermediates\apk\androidTest\debug).

12. Install Main app apk -> in your opened CMD -> adb install (PathFromStep11Part1)\app-debug.apk
    Install Test package apk -> in your opened CMD -> adb install (PathFromStep11Part2)
    \app-debug-androidTest.apk

13. Quizzy on the device now. :-)

# Run the entire test package

To run all of the test classes in the test package, enter:

Run the entire test package:
Write in the CMD -> adb shell am instrument -w
com.ntk.quizzy.test/androidx.test.runner.AndroidJUnitRunner

Run one class (MainActivityTest):
adb shell am instrument -e class com.ntk.quizzy.Views.Activities.MainActivityTest -w com.ntk.quizzy.test/androidx.test.runner.AndroidJUnitRunner

Run one method in class (QuestionActivityTest):
adb shell am instrument -e class com.ntk.quizzy.Views.Activities.QuestionActivityTest#TestIfTheScoreIncreaseOneOnRightAnswer -w com.ntk.quizzy.test/androidx.test.runner.AndroidJUnitRunner


[Test from the command line | Android Developers](https://developer.android.com/studio/test/command-line)
to learn more.

