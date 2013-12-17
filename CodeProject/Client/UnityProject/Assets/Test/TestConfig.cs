using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class TestConfig : MonoBehaviour {

	List<m.VipConfig> vips;

	void Awake (){
		vips = new List<m.VipConfig>();
	}

	void Start (){
		TextAsset asset = Resources.Load<TextAsset>("ClientProto/VipConfig.protodata");
		ProtoData<m.VipConfig> xx = new ProtoData<m.VipConfig>(asset.bytes);
		for (int i = 0; i < xx.Count; i++) {
			m.VipConfig item = new m.VipConfig();
			item = xx[i];
			vips.Add(item);
		}
	}
}
