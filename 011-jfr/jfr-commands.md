# Enabling and Configuring JDK Flight Recorder

```
java -XX:StartFlightRecording=disk=true,filename=filename.jfr,settings=profile,dumponexit=true -jar <your application>
```