subst m: /d
subst m: "%~dp0..\..\..\..\..\..\..\CodeProject\Client\UnityProject"

@SET PROTOBUF="%~dp0..\..\..\..\..\..\..\CodeProject\Tools\protobuf\protogen.exe"

@%PROTOBUF% -i:ExternalCommonProtocol.proto -o:myth.cs

@xcopy myth.cs m:\Assets\Scripts\	/Y /Q
@del myth.cs

subst m: /d
@pause