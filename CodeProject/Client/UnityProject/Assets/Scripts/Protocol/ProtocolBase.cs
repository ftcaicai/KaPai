using UnityEngine;
using System.Collections;

public abstract class ProtocolBase {

	public abstract myth.MessageKey MsgType { get; }

	public abstract bool HandleMessage (MessageData data);
}
