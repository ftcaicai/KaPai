using UnityEngine;
using System.Collections;

public class S2C_LOGINERROR : ProtocolBase {
	#region implemented abstract members of ProtocolBase

	public override bool HandleMessage (MessageData data)
	{
		return true;
	}

	public override myth.MessageKey MsgType {
		get {
			return myth.MessageKey.S2C_LOGINERROR;
		}
	}

	#endregion



}
