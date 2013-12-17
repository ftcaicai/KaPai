@SET CSC20=c:\Windows\Microsoft.NET\Framework\v2.0.50727\csc.exe
@%CSC20% /out:dll/m-client-proto.dll /target:library /reference:protobuf-net.dll /debug- /optimize+ code\*.cs
@pause