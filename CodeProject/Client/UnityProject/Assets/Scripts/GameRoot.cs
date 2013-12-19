using UnityEngine;
using System.Collections;

public class GameRoot : MonoBehaviour {

	public static GameRoot Instance;

	private InputHandlerMgr		m_InputHandlerMgr;
	private ConfigMgr			m_ConfigMgr;

	void Awake (){
		Instance = this;
		gameObject.isStatic = true;
		DontDestroyOnLoad(gameObject);

		_Init ();
	}
	
	void Start () {

	}

	void Update (){
		
	}

	void _Init (){
		m_InputHandlerMgr = new InputHandlerMgr();
		m_ConfigMgr = new ConfigMgr();
	}

	void OnTap (TapGesture gesture){
		Debug.Log("OnTap" + gesture.Position);
	}

	void OnDrag (DragGesture gesture) {
		Debug.Log("OnDrag" + gesture.DeltaMove);
	}
}
