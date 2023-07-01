
import 'package:flutter/services.dart';

class DeviceEqualizer{
  Future<void> open(int sessionId) async {
    const methodChannel = MethodChannel('device_equalizer');
    await methodChannel.invokeMethod('open', sessionId);
  }
}
