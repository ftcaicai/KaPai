using UnityEngine;
using System.Collections;

public class GameRoot : MonoBehaviour {

	public static GameRoot Instance;

	private EConnectStatus		m_ConnectStatus;

	#region singlon compoment

	private PrefabParamMgr		m_PrefabParamMgr;
	private InputHandlerMgr		m_InputHandlerMgr;
	private ConfigMgr			m_ConfigMgr;
	private NetMessageMgr		m_Net;

	#endregion

	#region temp property

	private LoadServers			t_LoadServers;

	#endregion

	public PrefabParamMgr 		PrefabParamMgr {
		get { return m_PrefabParamMgr; }
	}

	void Awake (){
		Instance = this;
		gameObject.isStatic = true;
		DontDestroyOnLoad(gameObject);

		_Init ();
	}
	
	void Start () {
		t_LoadServers = gameObject.AddComponent<LoadServers>();
	}

	void Update (){
		switch (m_ConnectStatus) {
		case EConnectStatus.BeginConnect:
		{
			m_Net.Connect(PrefabParamMgr.sServerIP, PrefabParamMgr.iServerPort);
			m_ConnectStatus = EConnectStatus.Connecting;
		}
			break;
		case EConnectStatus.Connected:
		{
			m_Net.OnUpdate();
		}
			break;
		case EConnectStatus.Error:
		{
			Debug.LogError("Net Error!");
			m_Net.Reset();
			m_ConnectStatus = EConnectStatus.None;
		}
			break;
		}
	}

	void _Init (){
		m_PrefabParamMgr = gameObject.GetComponentInChildren<PrefabParamMgr>();

		m_InputHandlerMgr = new InputHandlerMgr();
		m_ConfigMgr = new ConfigMgr();
		m_Net = new NetMessageMgr();
	}

	void OnTap (TapGesture gesture){
		Debug.Log("OnTap" + gesture.Position);
	}

	void OnDrag (DragGesture gesture) {
		Debug.Log("OnDrag" + gesture.DeltaMove);
	}

	public static void NotifyGetSession (string sessionid, string serverip, int serverport){
		Instance.PrefabParamMgr.NotifyGetSession(serverip, serverport, sessionid);

		Instance.m_ConnectStatus = EConnectStatus.BeginConnect;
	}

	public static void NotifyNetConnected (bool bresult){
		if (bresult)
			Instance.m_ConnectStatus = EConnectStatus.Connected;
		else {
			Instance.m_ConnectStatus = EConnectStatus.Error;
		}
	}
}
