#ifndef FLUTTER_PLUGIN_DEVICE_EQUALIZER_PLUGIN_H_
#define FLUTTER_PLUGIN_DEVICE_EQUALIZER_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace device_equalizer {

class DeviceEqualizerPlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  DeviceEqualizerPlugin();

  virtual ~DeviceEqualizerPlugin();

  // Disallow copy and assign.
  DeviceEqualizerPlugin(const DeviceEqualizerPlugin&) = delete;
  DeviceEqualizerPlugin& operator=(const DeviceEqualizerPlugin&) = delete;

  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace device_equalizer

#endif  // FLUTTER_PLUGIN_DEVICE_EQUALIZER_PLUGIN_H_
