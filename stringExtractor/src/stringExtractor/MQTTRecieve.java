package stringExtractor;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class MQTTRecieve implements Runnable {
	public MQTTRecieve() {
		
	}
	@Override
	public void run() {
		String topic = "Dell";
		int qos = 1;
		String broker = "tcp://m12.cloudmqtt.com:16534";

		// MQTT client id to use for the device. "" will generate a client id
		// automatically
		String clientId = "ClientIdAnalytic";

		MemoryPersistence persistence = new MemoryPersistence();
		try {
			MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
			mqttClient.setCallback(new MqttCallback() {
				public void messageArrived(String topic, MqttMessage msg) throws Exception {
					//System.out.println("Recived from MQTT:" + topic);
					String res = new String(msg.getPayload());
					System.out.println("Recived from MQTT: " + res + "\n");
					// DO LONG BIEN
					CommitData cm = new CommitData();
					cm.commitData(res);
					
					//Thread.sleep(20);
				}

				public void deliveryComplete(IMqttDeliveryToken arg0) {
					// System.out.println("Delivary complete");
				}

				public void connectionLost(Throwable arg0) {
					// TODO Auto-generated method stub
				}
			});

			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setUserName("phamvanlinh");
			connOpts.setPassword(new char[] { '1', '4', '3', '1', '9', '9', '7' });
			mqttClient.connect(connOpts);
			// MqttMessage message = new MqttMessage(content.getBytes());
			// message.setQos(qos);
			// System.out.println("Publish message: " + message);
			// mqttClient.publish(topic, message);
			mqttClient.subscribe(topic, qos);
			//mqttClient.disconnect();
			// System.exit(0);
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}
}
