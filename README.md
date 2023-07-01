# device_equalizer

A Flutter plugin to open inbuilt equalizer (Currently implemented only for Android).

## Getting Started
Add `device_equilizer` as a dependency in your pubspec.yaml file.
```
dependencies:
    device_equilizer:
      git:
        url: https://github.com/anandnet/device_equilizer.git
        ref: main
```

Now use this package in your implemetation

```dart
import 'package:device_equalizer/device_equalizer.dart';

final deviceEqualizer = DeviceEqualizer();
deviceEquilizer.open()
```

