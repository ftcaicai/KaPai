using UnityEngine;
using System.Collections;

public class GameRoot : MonoBehaviour {

	public static GameRoot Instance;

	private PrefabParamMgr		m_PrefabParamMgr;
	private InputHandlerMgr		m_InputHandlerMgr;
	private ConfigMgr			m_ConfigMgr;
	private NetMessageMgr		m_Net;

	private LoadServers			t_LoadServers;

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
		m_Net.OnUpdate ();
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
		Instance.t_LoadServers.enabled = false;
		Instance.m_Net.Connect(serverip, serverport);
	}
}
