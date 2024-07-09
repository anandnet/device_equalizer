import 'package:flutter/services.dart';

class DeviceEqualizer {
  static const methodChannel = MethodChannel('device_equalizer');
  Future<void> open(int sessionId) async {
    await methodChannel.invokeMethod('open', sessionId);
  }

  Future<void> initAudioEffect(int sessionId) async {
    await methodChannel.invokeMapMethod('initAudioEffect', sessionId);
  }

  Future<void> endAudioEffect(int sessionId) async {
    await methodChannel.invokeMapMethod('endAudioEffect', sessionId);
  }
}
