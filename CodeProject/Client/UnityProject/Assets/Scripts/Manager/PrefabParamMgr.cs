using UnityEngine;
using System.Collections;

public class PrefabParamMgr : MonoBehaviour {

	public string 		sManagerIP;
	public int 			iManagerPort;

	[HideInInspector]
	public string		sServerIP;
	[HideInInspector]
	public int 			iServerPort;
	[HideInInspector]
	public string 		sSessionID;

	public void NotifyGetSession (string sip, int iport, string sessionid){
		sServerIP = sip;
		iServerPort = iport;
		sSessionID = sessionid;
	}
}
