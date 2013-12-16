using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class Message_Head {
	public int Type  			{ get; set; }
	public int Length 		  	{ get; set; }	
}

public class Message_Body {
	public byte[] body 	{ get; set; }
}

public class MessageData {
	public Message_Head head { get; set; }
	public Message_Body body { get; set; }
}

public class MessageParse {

	public const int iHL = 6;
	
	public static Message_Head UnParseHead (byte[] buffer) {
		if (buffer.Length >= iHL) {
			Message_Head head = new Message_Head();
			head.Type = (buffer[0] << 8) + buffer[1];
			head.Length = (buffer[2] << 24) + (buffer[3] << 16) + (buffer[4] << 8) + buffer[5];
			return head;
		}
		return null;
	}
	
	public static MessageData UnParse (byte[] buffer) {
		int iHead = iHL;
		{
			Message_Head head = UnParseHead(buffer);
			if (head != null && head.Length <= (buffer.Length - iHead)) {
				Message_Body body = new Message_Body();
				body.body = new byte[head.Length];
				System.Array.Copy(buffer, iHL, body.body, 0, head.Length);
				MessageData data = new MessageData();
				data.head = head;
				data.body = body;
				return data;
			}
		}
		return null;
	}

	public static byte[] Parse (int iType, byte[] bodyBytes){
		byte[] buf = new byte[4];
		// 设置头部
		buf[0] = (byte)((iType >> 8) & 0xFF);
		buf[1] = (byte)(iType & 0xFF);
		buf[2] = (byte)((bodyBytes.Length >> 8) & 0xFF);
		buf[3] = (byte)(bodyBytes.Length & 0xFF);

		byte[] allBytes = new byte[buf.Length + bodyBytes.Length];
		System.Array.Copy(buf,0,allBytes,0, buf.Length);
		System.Array.Copy(bodyBytes,0,allBytes,buf.Length, bodyBytes.Length);
		return allBytes;
	}
}
