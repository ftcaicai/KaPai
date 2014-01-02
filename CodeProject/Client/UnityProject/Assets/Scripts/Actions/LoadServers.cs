using UnityEngine;
using System.Collections;

public class LoadServers: MonoBehaviour  {

	private class GetSessionArg {
		public string 	sip;
		public int 		iport;
		public string 	skey;
		public int 		iserverid;
		public int 		auth;
	}

	private ELoadStatus		m_loadStatus;
	private string 			m_sKey;

	void Awake (){
		m_loadStatus = ELoadStatus.None;
		m_sKey = "";
	}

	void OnGUI (){
		m_sKey = GUI.TextField(new Rect(Screen.width / 2 - 80, Screen.height - 140, 80, 40), m_sKey);
		if (GUI.Button(new Rect(Screen.width / 2 - 80, Screen.height - 80, 80, 40), "Login")) {
			m_loadStatus = ELoadStatus.Start;
		}
	}

	void Update (){
		switch (m_loadStatus) {
		case ELoadStatus.Start:
		{
			var param = GameRoot.Instance.PrefabParamMgr;
			GetSessionID(param.sManagerIP, param.iManagerPort, m_sKey);
			m_loadStatus = ELoadStatus.Waiting;
		}
			break;
		}
	}

	public void GetSessionID (string ip, int port, string skey) {
		GetSessionArg arg = new GetSessionArg();
		{
			arg.sip = ip;
			arg.iport = port;
			arg.skey = skey;
			arg.iserverid = 0;
			arg.auth = 0;
		}
		StartCoroutine("_GetSession", arg);
	}

	private IEnumerator _GetSession (GetSessionArg arg){
		WWWForm form = new WWWForm();
		form.AddField("passport", arg.skey);
		form.AddField("serverid", arg.iserverid);
		form.AddField("auth", arg.auth);
		WWW www = new WWW(string.Format("http://{0}:{1}/session/get", arg.sip, arg.iport), form);
		while (!www.isDone) {
			yield return 0;
		}
		if (string.IsNullOrEmpty(www.error)) {
			string sJson = www.text;
			LitJson.JsonData jsondata = LitJson.JsonMapper.ToObject(sJson);
			int result = int.Parse(jsondata["result"].ToString());
			if (result == 0) {
				string sessionid = jsondata["sessionid"].ToString();
				string serverip = jsondata["serverip"].ToString();
				int serverport = int.Parse(jsondata["serverport"].ToString());

				GameRoot.NotifyGetSession(sessionid, serverip, serverport);
			}
			else {
				Debug.LogError("Get SessionID error, result = " + result);	
			}
		}
		else {
			Debug.LogError("Get SessionID error!");
		}
	}
}
