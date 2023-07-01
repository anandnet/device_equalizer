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
          child: ElevatedButton(
            child: const Text("Open device eqalizer"),
            onPressed: () async {
              await _deviceEqualizerPlugin.open(1235);
            },
          ),
        ),
      ),
    );
  }
}
