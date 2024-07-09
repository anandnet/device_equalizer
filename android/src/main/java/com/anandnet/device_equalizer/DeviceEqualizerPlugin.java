package com.anandnet.device_equalizer;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.app.Activity;
import android.content.Context;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;

/** DeviceEqualizerPlugin */
public class DeviceEqualizerPlugin implements FlutterPlugin, MethodCallHandler,ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private Activity activity;
  private Context applicationContext;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    this.applicationContext = flutterPluginBinding.getApplicationContext();
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "device_equalizer");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("open")) {
      openEqualizer((int)call.arguments ,result);
    }else if(call.method.equals("initAudioEffect")){
      initAudioEffect((int)call.arguments);
    }else if(call.method.equals("endAudioEffect")){
      endAudioEffect((int)call.arguments);
    }else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
    applicationContext = null;
    channel = null;
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    this.activity = binding.getActivity();
    System.out.println("Hello from java side");
  }

  @Override
  public void onDetachedFromActivity() {
    activity = null;
  }

  void openEqualizer(int sessionId,Result result){
    Intent intent = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);
    intent.putExtra(AudioEffect.EXTRA_PACKAGE_NAME, applicationContext.getPackageName());
    intent.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, sessionId);
    intent.putExtra(AudioEffect.EXTRA_CONTENT_TYPE, AudioEffect.CONTENT_TYPE_MUSIC);
    if ((intent.resolveActivity(applicationContext.getPackageManager()) != null)) {
      activity.startActivityForResult(intent, 0);
    } else {
      result.error("Unavailable",
              "No equalizer found!",
              "Seems device don't have inbuilt equalizer"
      );
    }
  }

  public void initAudioEffect(int sessionId){
    sendAudioEffectIntent(sessionId, AudioEffect.ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION);
    System.out.println("Sent AudioEffect intent for opening");
  }

  public void endAudioEffect(int sessionId){
    sendAudioEffectIntent(sessionId, AudioEffect.ACTION_CLOSE_AUDIO_EFFECT_CONTROL_SESSION);
    System.out.println("Sent AudioEffect intent for closure");
  }

  private void sendAudioEffectIntent(int sessionId, String action){
    Intent intent = new Intent(action);
    intent.putExtra(AudioEffect.EXTRA_PACKAGE_NAME, applicationContext.getPackageName());
    intent.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, sessionId);
    intent.putExtra(AudioEffect.EXTRA_CONTENT_TYPE, AudioEffect.CONTENT_TYPE_MUSIC);
    applicationContext.sendBroadcast(intent);
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {}

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {}
}
