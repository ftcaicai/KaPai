subst m: /d
subst m: "%~dp0..\..\CodeProject\Client\UnityProject"
@SET CSC20=c:\Windows\Microsoft.NET\Framework\v2.0.50727\csc.exe
proto_conv.exe test.xlsx
@%CSC20% /out:dll/m-client-proto.dll /target:library /reference:protobuf-net.dll /debug- /optimize+ code\*.cs
DLLToSerializer.exe MySerializer dll/m-client-proto.dll
@xcopy MySerializer.dll m:\Assets\Libs\ /Y /Q
@xcopy dll\m-client-proto.dll m:\Assets\Libs\ /Y /Q
@xcopy MySerializer.dll dll\ /Y /Q
@xcopy protobuf-net.dll dll\ /Y /Q
@del MySerializer.dll  
@FOR %%P IN (protodata\*) DO xcopy %%P m:\Assets\Resources\ClientProto\ /Y /Q
@pause