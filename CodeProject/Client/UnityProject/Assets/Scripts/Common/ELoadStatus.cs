using UnityEngine;
using System.Collections;

public enum ELoadStatus {
	None = 0,
	Start,
	Waiting,
	End,
	Error
}

public enum EConnectStatus {
	None = 0,
	BeginConnect,
	Connecting,
	Connected,
	Error
}
