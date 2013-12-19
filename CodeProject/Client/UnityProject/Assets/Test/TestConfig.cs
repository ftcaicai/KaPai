using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class TestConfig : MonoBehaviour {

	List<m.Lang> vips;

	void Awake (){
		vips = new List<m.Lang>();
	}

	void Start (){
		TextAsset asset = Resources.Load<TextAsset>("ClientProto/Lang.protodata");
		ProtoData<m.Lang> xx = new ProtoData<m.Lang>(asset.bytes);
		for (int i = 0; i < xx.Count; i++) {
			m.Lang item = new m.Lang();
			item = xx[i];
			vips.Add(item);
		}
	}
}
