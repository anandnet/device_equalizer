#include "include/device_equalizer/device_equalizer_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "device_equalizer_plugin.h"

void DeviceEqualizerPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  device_equalizer::DeviceEqualizerPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
