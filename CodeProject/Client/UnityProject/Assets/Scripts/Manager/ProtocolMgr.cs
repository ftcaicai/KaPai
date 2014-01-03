using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class ProtocolMgr {

	private Dictionary<int, ProtocolBase> 	m_RegisterProtocol;

	public ProtocolMgr (){
		m_RegisterProtocol = new Dictionary<int, ProtocolBase>();

		_Init();
	}

	private void _Init (){
		RegistHandler(new S2C_LOGINERROR());
	}

	public void RegistHandler (ProtocolBase protocol){
		int msgType = (int)protocol.MsgType;
		if (!m_RegisterProtocol.ContainsKey(msgType)) {
			m_RegisterProtocol.Add(msgType, protocol);
		}
		else {
			Debug.LogError("Error protocol has define, " + protocol.MsgType.ToString());
		}
	}

	public ProtocolBase GetProtocol (int msgType) {
		ProtocolBase protocol = null;
		m_RegisterProtocol.TryGetValue(msgType, out protocol);
		return protocol;
	}
}
