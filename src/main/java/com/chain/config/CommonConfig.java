package com.chain.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.Valid;

@Configuration
@PropertySource(value = {"classpath:application.yml"}, ignoreResourceNotFound = true)
public class CommonConfig {

	//rocksDB
    public static final Integer DBNUMBER = 0;
    //用户数量
	public static final String ACCOUNTNUMBER = "account";


    public static String NODENO = "ps13452aw";

	public static int SLEEP_TIME = 1000;

	public static String toViewString() {
		return "\r\nNODENO[" + NODENO + "]\r\n";
	}

	//websocket链接信息
	private static String ws_protocol;

	private static String wsUrl;

	//节点数据
	private static String nodeUrl;
	private static String version;
	private static String nodeMessages;
	private static String nodeTransaction;
	private static String localfullnodes;


	public static String getVersion() {
		return version;
	}

	@Value("${node.version}")
	public void setVersion(String version) {
		this.version = version;
	}

	public static String getLocalfullnodes() {
		return localfullnodes;
	}

	@Value("${node.localfullnodes}")
	public void setLocalfullnodes(String localfullnodes) {
		this.localfullnodes = localfullnodes;
	}

	public static String getNodeUrl() {
		return nodeUrl;
	}

	@Value("${node.url}")
	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}

	public static String getNodeMessages() {
		return nodeMessages;
	}

	@Value("${node.messages}")
	public void setNodeMessages(String nodeMessages) {
		this.nodeMessages = nodeMessages;
	}

	public static String getNodeTransaction() {
		return nodeTransaction;
	}

	@Value("${node.transaction}")
	public void setNodeTransaction(String nodeTransaction) {
		this.nodeTransaction = nodeTransaction;
	}

	public static String getWs_protocol() {
		return ws_protocol;
	}

	@Value("${client.ws_protocol}")
	public void setWs_protocol(String ws_protocol) {
		this.ws_protocol = ws_protocol;
	}

	public static String getWsUrl() {
		return wsUrl;
	}

	@Value("${client.wsUrl}")
	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}
}
