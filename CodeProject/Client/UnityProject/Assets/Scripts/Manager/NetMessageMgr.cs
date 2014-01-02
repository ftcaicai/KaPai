using UnityEngine;
using System.Collections;
using System.IO;

public class NetMessageMgr {

	private XTcpClient		m_Client;
	private MessageData		m_LoopData;
	private bool			m_Enable;
	private MySerializer	m_Serializer;

	public NetMessageMgr (){
		m_Client = new XTcpClient();
		m_Client.OnConnected += HandleOnConnected;
		m_Client.OnDisconnected += HandleOnDisconnected;
		m_Client.OnError += HandleOnError;
		m_Enable = false;
		m_Serializer = new MySerializer();
	}

	void HandleOnError (object sender, DSCClientErrorEventArgs e)
	{

	}

	void HandleOnDisconnected (object sender, DSCClientConnectedEventArgs e)
	{

	}

	void HandleOnConnected (object sender, DSCClientConnectedEventArgs e)
	{
		m_Enable = m_Client.Connected;
		Debug.Log("Connect to Server : " + m_Enable);
	}

	public void Connect (string sip, int port) {
		m_Client.Connect(sip, port);
	}

	public void Send (ProtoMessage msg){
		MemoryStream ms = new MemoryStream ();
		Serialize (ms, msg);
		
		int dataLength = (int)ms.Length;
		byte[] buf = new byte[4 + dataLength];

		buf[0] = (byte)((msg.Type >> 8) & 0xFF);
		buf[1] = (byte)(msg.Type & 0xFF);
		buf[2] = (byte)((dataLength >> 8) & 0xFF);
		buf[3] = (byte)(dataLength & 0xFF);
		
		ms.Position = 0;
		int remainLen = dataLength;
		while (remainLen > 0) {
			remainLen -= ms.Read (buf, 4 + dataLength - remainLen, remainLen);
		}
		m_Client.Send(buf);
	}

	public void OnUpdate (){
		if (m_Enable && m_Client.Connected) {
			m_LoopData = m_Client.Loop();
			if (m_LoopData != null) {
				Debug.Log(":::[Loop]-" + m_LoopData.head.Length);
			}
		}
	}

	private void Serialize (MemoryStream ms, ProtoMessage msg)
	{
		m_Serializer.Serialize(ms, msg);
	}
}
