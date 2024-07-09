import 'package:flutter/material.dart';
import 'package:device_equalizer/device_equalizer.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final _deviceEqualizerPlugin = DeviceEqualizer();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              ElevatedButton(
                child: const Text("Open device eqalizer"),
                onPressed: () async {
                  await _deviceEqualizerPlugin.open(1235);
                },
              ),
              ElevatedButton(
                child: const Text("init Audio effect"),
                onPressed: () async {
                  await _deviceEqualizerPlugin.initAudioEffect(1235);
                },
              ),
              ElevatedButton(
                child: const Text("close Audio effect"),
                onPressed: () async {
                  await _deviceEqualizerPlugin.endAudioEffect(1235);
                },
              )
            ],
          ),
        ),
      ),
    );
  }
}
